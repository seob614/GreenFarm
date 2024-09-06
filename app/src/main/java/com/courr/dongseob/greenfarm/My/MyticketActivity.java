package com.courr.dongseob.greenfarm.My;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.courr.dongseob.greenfarm.ClassActivity.CoffeeActivity;
import com.courr.dongseob.greenfarm.ClassActivity.CouponActivity;
import com.courr.dongseob.greenfarm.ClassActivity.GetPresentActivity;
import com.courr.dongseob.greenfarm.ClassActivity.PopcornActivity;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MyticketActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private String point;

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    private TextView t_gift1;
    private TextView t_gift2;

    private TextView t_coffee1;
    private TextView t_coffee2;

    private TextView t_popcorn1;
    private TextView t_popcorn2;

    private ArrayList<String> CouponnumList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myticket);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        point = intent.getStringExtra("point");

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);

        t_gift1 = (TextView) findViewById(R.id.t_gift1);
        t_gift2 = (TextView) findViewById(R.id.t_gift2);

        t_coffee1 = (TextView) findViewById(R.id.t_coffee1);
        t_coffee2 = (TextView) findViewById(R.id.t_coffee2);

        t_popcorn1 = (TextView) findViewById(R.id.t_popcorn1);
        t_popcorn2 = (TextView) findViewById(R.id.t_popcorn2);

        imageView1.setClickable(true);
        imageView2.setClickable(true);
        imageView3.setClickable(true);

        t_gift1.setClickable(true);
        t_gift2.setClickable(true);

        t_coffee1.setClickable(true);
        t_coffee2.setClickable(true);

        t_popcorn1.setClickable(true);
        t_popcorn2.setClickable(true);

        DatabaseReference qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드");

        final DatabaseReference presentreference = qrreference.child("선물");
        final DatabaseReference labreference   =   qrreference.child("연구");
        final DatabaseReference capstonereference = qrreference.child("캡스톤");

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyticketActivity.this, CouponActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });

        t_gift1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyticketActivity.this, CouponActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });

        t_gift2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyticketActivity.this, CouponActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyticketActivity.this, CoffeeActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });

        t_coffee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MyticketActivity.this, CoffeeActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });

        t_coffee2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MyticketActivity.this, CoffeeActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyticketActivity.this, PopcornActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });

        t_popcorn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyticketActivity.this, PopcornActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });

        t_popcorn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyticketActivity.this, PopcornActivity.class);
                intent.putExtra("studentnum", studentnum);
                intent.putExtra("name",name);
                intent.putExtra("token",token);

                intent.putExtra("point",point);
                startActivity(intent);
            }
        });
    }
}
