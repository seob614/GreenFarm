package com.courr.dongseob.greenfarm.My;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.courr.dongseob.greenfarm.Class.Chat_ListViewAdapter;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MychatActivity extends AppCompatActivity {

    private EditText et_chat;
    private Button bt_chat;

    private ListView chatlist;

    private String key;
    private String message;
    private String time;

    private String studentnum;
    private String name;
    private String token;

    private String masterstudentnum;
    private String mastername;

    private String deer;

    private Chat_ListViewAdapter chat_listViewAdapter = new Chat_ListViewAdapter();

    private String chatstudentnum;
    private String chatname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mychat);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        masterstudentnum = intent.getStringExtra("masterstudentnum");

        mastername = intent.getStringExtra("mastername");

        et_chat = (EditText) findViewById(R.id.et_chat);
        bt_chat = (Button) findViewById(R.id.bt_chat);

        chatlist = (ListView) findViewById(R.id.chatlist);



        chatlist.setAdapter(chat_listViewAdapter);
        chatlist.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        final DatabaseReference chatreference = FirebaseDatabase.getInstance().getReference().child("채팅").child(studentnum+"("+name+")"+token);

        //if (masterstudentnum.contains("201545805")&&mastername.contains("김동섭")){
        //    deer = token;
        //}
        //else{
        //    deer = "dGkmxlg4SWY:APA91bFPQfTMxA8qvZ2LQ6sm55Tvnoif4yxu2tsMi1G0wNMPG0ECkefd2HGP8fBmANEXw2kTzLc7gcl7w_mrTI8j1ZkS2ZONoqNAY5NtxvgMERUVr5wWv134B1jE6ZS8jdV_8lZsBLqS";
        //}

        bt_chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();

                key = chatreference.push().getKey();
                chatreference.updateChildren(map);

                DatabaseReference dbRef = chatreference.child(key);

                Map<String, Object> objectMap = new HashMap<String, Object>();

                if (masterstudentnum.contains("201545805")&&mastername.contains("김동섭")){
                    objectMap.put("message", masterstudentnum+"("+mastername+")"+et_chat.getText().toString());
                    objectMap.put("time", getDate());
                }
                else{
                    objectMap.put("message", studentnum+"("+name+")"+et_chat.getText().toString());
                    objectMap.put("time", getDate());
                }


                dbRef.updateChildren(objectMap);

                //sendPostToFCM(deer,et_chat.getText().toString());


                et_chat.setText("");
            }
        });

        chatreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chatConversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                chatConversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void chatConversation(DataSnapshot dataSnapshot){
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){
            message = (String) ((DataSnapshot) i.next()).getValue();
            time = (String) ((DataSnapshot) i.next()).getValue();

            int start = message.indexOf("(");
            int end = message.indexOf(")");

            chatstudentnum = message.substring(0,start);
            chatname = message.substring(start+1,end);

            if (chatstudentnum.contains("201545805")&&chatname.contains("김동섭")){
                chat_listViewAdapter.addItem("관리자 답변",message.substring(end+1,message.length()),time+"  ");
            }
            else{
                chat_listViewAdapter.addItem("질문",message.substring(end+1,message.length()),time+"  ");
            }


        }
        chat_listViewAdapter.notifyDataSetChanged();
    }

    public static String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
        Date date = new Date();
        String strDate = dateFormat.format(date);
        return strDate;
    }

    //private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    //private static final String SERVER_KEY = "AAAAwmuxn5E:APA91bHJJZUbynWtV1loDMkuOpH62Fc3LUZn4Tq6tNZ1PGv6xqNVDCJzW4PnXqNFGXSFM3YrXv4a5z4ZOEj332aQJo5PpXggvBdCcUBn1p7RvW_FxHkD4-Zgl_pIGb5OzlW4d9JjYKCOiO6Tfdk4vp2I9t1x3LpdDg";

    //private void sendPostToFCM(final String sn_token, final String sn_message) {
    //    new Thread(new Runnable() {
    //        @Override
    //        public void run() {
    //            try {
    //                JSONObject root = new JSONObject();
    //                JSONObject notification = new JSONObject();
    //                notification.put("body", sn_message);
    //                notification.put("title",getString(R.string.app_name));
    //                notification.put("sound", Settings.System.DEFAULT_NOTIFICATION_URI);
    //                root.put("notification",notification);
    //                root.put("to", sn_token);

    //                URL Url = new URL(FCM_MESSAGE_URL);
    //                HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
    //                conn.setRequestMethod("POST");
    //                conn.setDoOutput(true);
    //                conn.setDoInput(true);
    //                conn.addRequestProperty("Authorization","key="+ SERVER_KEY);
    //                conn.setRequestProperty("Accept", "application/json");
    //                conn.setRequestProperty("Content-type", "application/json");
    //                OutputStream os = conn.getOutputStream();
    //                os.write(root.toString().getBytes("utf-8"));
    //                os.flush();
    //                conn.getResponseCode();

    //            } catch (JSONException e) {
    //                e.printStackTrace();
    //            } catch (MalformedURLException e) {
    //                e.printStackTrace();
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    }).start();
    //}
}
