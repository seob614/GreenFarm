package com.courr.dongseob.greenfarm.My;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.courr.dongseob.greenfarm.Class.Fcm;
import com.courr.dongseob.greenfarm.R;
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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class AlramActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private ListView alramlist;

    private TextView t_time;
    private Button bt_alram;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        alramlist = (ListView) findViewById(R.id.alramlist);

        t_time = (TextView) findViewById(R.id.t_time);
        bt_alram = (Button) findViewById(R.id.bt_alram);

        date = getDate();

        t_time.setText(date);

        bt_alram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendPostToFCM(token,"vr","1시","5","4");
            }
        });

        DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간");

        DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
        DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
        DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
        DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
        DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
        DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");


    }

    public static String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        Date date = new Date();
        String strDate = dateFormat.format(date);
        return strDate;
    }



}
