package com.courr.dongseob.greenfarm.ClassActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.Class.Lab_ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.ZxingActivity;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import uk.co.senab.photoview.PhotoView;

public class CouponActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private String point;

    private TextView t_clear;

    private Toolbar toolbar;

    //private TextView t_couponnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        Glide.with(this).load(R.drawable.couponnum).into(imageView1);

        PhotoView imageView2 = (PhotoView) findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.map).into(imageView2);


        Intent intent = getIntent();
        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        point = intent.getStringExtra("point");

        //t_couponnum = (TextView) findViewById(R.id.t_couponnum);

        t_clear = (TextView) findViewById(R.id.textView2);

        //t_time = (TextView) findViewById(R.id.t_time);

        //t_nownum = (TextView) findViewById(R.id.t_nownum);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("응모권 티켓");

        DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("응모권");

        t_PQRreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {
                    t_clear.setText(dataSnapshot.getValue().toString());
                } else {
                    t_clear.setText("미지급");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        int point_value = Integer.parseInt(point);

        if (point_value>=5){
            final DatabaseReference t_getPQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("응모권");

            t_getPQRreference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    t_getPQRreference.removeEventListener(this);

                    if (dataSnapshot.getValue()!=null) {
                        t_clear.setText(dataSnapshot.getValue().toString());
                    }
                    else{
                        if (studentnum.length()==8){
                            Toast.makeText(CouponActivity.this, "학생 외엔 응모권을 받을수 없습니다.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            final DatabaseReference getcoupon = FirebaseDatabase.getInstance().getReference().child("응모권");

                            Map<String,Object> map = new HashMap<>();
                            map.put(studentnum,name);

                            getcoupon.updateChildren(map);

                            getcoupon.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new HashSet<>();


                                    while (i.hasNext()) {
                                        set.add(((DataSnapshot) i.next()).getKey());
                                    }

                                    DatabaseReference couponreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("응모권");
                                    couponreference.setValue(set.size());

                                    getcoupon.removeEventListener(this);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{
            Toast.makeText(CouponActivity.this, "5포인트이상 획득가능 합니다.", Toast.LENGTH_SHORT).show();
        }

    }
}



