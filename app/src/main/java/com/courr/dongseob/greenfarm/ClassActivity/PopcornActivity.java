package com.courr.dongseob.greenfarm.ClassActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.BoothExperience.DroneActivity;
import com.courr.dongseob.greenfarm.Class.Lab_ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.ZxingActivity;
import com.courr.dongseob.greenfarm.My.MyBoothActivity;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PopcornActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private String point;


    //private Button bt_reserve;


    private Button bt_qring;

    //추가
    private TextView t_clear;
    //private TextView t_time;
    //private TextView t_nownum;


    private IntentIntegrator qr;

    private ArrayList<String> numarrayList = new ArrayList<>();

    private Toolbar toolbar;

    final Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popcorn);

        ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
        Glide.with(this).load(R.drawable.sik).into(imageView1);

        PhotoView imageView2 = (PhotoView) findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.map_localfood).into(imageView2);

        //PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView2);
        //photoViewAttacher.setScaleType(ImageView.ScaleType.FIT_XY);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        point = intent.getStringExtra("point");

        //bt_reserve = (Button) findViewById(R.id.bt_reserve);

        bt_qring = (Button) findViewById(R.id.bt_qring);

        //수정
        t_clear = (TextView) findViewById(R.id.textView2);

        //t_time = (TextView) findViewById(R.id.t_time);

        //t_nownum = (TextView) findViewById(R.id.t_nownum);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("식혜 쿠폰");

        DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("식혜");

        t_PQRreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {
                    t_clear.setText("지급완료");
                } else {
                    t_clear.setText(point+"포인트");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        bt_qring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int point_value = Integer.parseInt(point);
                if (point_value>=5){

                    builder.setTitle("식혜쿠폰")        // 제목 설정
                            .setMessage("한번 받으면 더이상 받을수 없습니다.*이 버튼은 부스운영자가 클릭합니다. ")        // 메세지 설정
                            .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                            .setNeutralButton("확인", new DialogInterface.OnClickListener(){
                                // 확인 버튼 클릭시 설정
                                public void onClick(final DialogInterface dialog, int whichButton) {
                                    final DatabaseReference t_getPQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("식혜");

                                    t_getPQRreference.setValue("지급완료");

                                }
                            })
                            .setPositiveButton("취소", new DialogInterface.OnClickListener(){
                                // 취소 버튼 클릭시 설정
                                public void onClick(DialogInterface dialog, int whichButton){
                                    dialog.cancel();
                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();

                }else{
                    Toast.makeText(PopcornActivity.this, "5포인트이상 획득가능 합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}



