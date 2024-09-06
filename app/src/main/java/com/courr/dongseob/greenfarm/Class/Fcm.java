package com.courr.dongseob.greenfarm.Class;

import android.provider.Settings;

import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Fcm {

    private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AAAAwmuxn5E:APA91bHJJZUbynWtV1loDMkuOpH62Fc3LUZn4Tq6tNZ1PGv6xqNVDCJzW4PnXqNFGXSFM3YrXv4a5z4ZOEj332aQJo5PpXggvBdCcUBn1p7RvW_FxHkD4-Zgl_pIGb5OzlW4d9JjYKCOiO6Tfdk4vp2I9t1x3LpdDg";

    private void sendPostToFCM(final String Token, final String booth, final String time, final String nownum,final String mynum) {
        FirebaseDatabase.getInstance().getReference().child("토큰").child(Token).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                final String token = dataSnapshot.child("토큰").getValue(String.class);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject root = new JSONObject();
                            JSONObject notification = new JSONObject();
                            notification.put("body", time + "예약- 현재:" + nownum + "번, 나의 순서:  " + mynum + "\n상세한 내용은 <MY - (종모양)버튼>을 클릭하여 확인하세요.");
                            notification.put("title", null);
                            notification.put("sound", Settings.System.DEFAULT_NOTIFICATION_URI);
                            root.put("notification", notification);
                            root.put("to", token);

                            URL Url = new URL(FCM_MESSAGE_URL);
                            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                            conn.setRequestProperty("Accept", "application/json");
                            conn.setRequestProperty("Content-type", "application/json");
                            OutputStream os = conn.getOutputStream();
                            os.write(root.toString().getBytes("utf-8"));
                            os.flush();
                            conn.getResponseCode();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
