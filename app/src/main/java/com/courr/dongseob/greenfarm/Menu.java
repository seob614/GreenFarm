package com.courr.dongseob.greenfarm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.courr.dongseob.greenfarm.ClassActivity.CouponActivity;
import com.courr.dongseob.greenfarm.ClassActivity.ExplainActivity;
import com.courr.dongseob.greenfarm.Egg.EGG1Activity;
import com.courr.dongseob.greenfarm.StartExplain.StartExplanActivity;
import com.courr.dongseob.greenfarm.WalK.WalkActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class Menu extends AppCompatActivity {

    private String studentnum;

    private String name;

    private String token;

    private Button bt_capstone;
    private Button bt_lab;
    private Button bt_booth;

    private Button bt_walk;

    private Button bt_my;
    private Button bt_explain;

    private Button bt_lol;

    private TextView t_egg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bt_capstone = findViewById(R.id.bt_capstone);
        bt_lab = findViewById(R.id.bt_lab);
        bt_booth = findViewById(R.id.bt_booth);

        bt_walk = findViewById(R.id.bt_walk);

        bt_my = findViewById(R.id.bt_my);
        bt_explain = findViewById(R.id.bt_explain);

        bt_lol = findViewById(R.id.bt_lol);

        t_egg = findViewById(R.id.t_egg);

        bt_booth.getBackground().setAlpha(200);
        bt_lab.getBackground().setAlpha(200);
        bt_capstone.getBackground().setAlpha(200);

        bt_walk.getBackground().setAlpha(200);

        bt_my.getBackground().setAlpha(200);
        bt_explain.getBackground().setAlpha(200);

        bt_lol.getBackground().setAlpha(200);

        String str_booth = "문화부스를 구경해보세요.\n문화 전시체험";
        SpannableStringBuilder ssb_booth = new SpannableStringBuilder(str_booth);
        ssb_booth.setSpan(new ForegroundColorSpan(Color.parseColor("#FF005225")), 0,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_booth.setSpan(new ForegroundColorSpan(Color.parseColor("#FF000000")), 13,str_booth.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_booth.setSpan(new AbsoluteSizeSpan(85), 13, str_booth.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_booth.setSpan(new AbsoluteSizeSpan(50), 0,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_booth.setSpan(new StyleSpan(Typeface.BOLD),13,str_booth.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bt_booth.append(ssb_booth);



        String str_lab = "연구원성과를 구경해보세요.\n연구성과 전시체험";
        SpannableStringBuilder ssb_lab = new SpannableStringBuilder(str_lab);
        ssb_lab.setSpan(new ForegroundColorSpan(Color.parseColor("#FF005225")), 0,13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_lab.setSpan(new ForegroundColorSpan(Color.parseColor("#FF000000")), 14,str_lab.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_lab.setSpan(new AbsoluteSizeSpan(85), 14, str_lab.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_lab.setSpan(new AbsoluteSizeSpan(50), 0,13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_lab.setSpan(new StyleSpan(Typeface.BOLD),14,str_lab.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bt_lab.append(ssb_lab);




        String str_capstone = "캡스톤디자인을 구경해보세요.\n캡스톤디자인 경진대회";
        SpannableStringBuilder ssb_capstone = new SpannableStringBuilder(str_capstone);
        ssb_capstone.setSpan(new ForegroundColorSpan(Color.parseColor("#FF005225")), 0,14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_capstone.setSpan(new ForegroundColorSpan(Color.parseColor("#FF000000")),15,str_capstone.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_capstone.setSpan(new AbsoluteSizeSpan(85), 15, str_capstone.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_capstone.setSpan(new AbsoluteSizeSpan(50), 0, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_capstone.setSpan(new StyleSpan(Typeface.BOLD),15,str_capstone.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bt_capstone.append(ssb_capstone);

        String str_walk = "둘레길을 구경해보세요.\n둘레길";
        SpannableStringBuilder ssb_walk = new SpannableStringBuilder(str_walk);
        ssb_walk.setSpan(new ForegroundColorSpan(Color.parseColor("#FF005225")), 0,11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_walk.setSpan(new ForegroundColorSpan(Color.parseColor("#FF000000")),12,str_walk.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_walk.setSpan(new AbsoluteSizeSpan(85), 12, str_walk.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_walk.setSpan(new AbsoluteSizeSpan(50), 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb_walk.setSpan(new StyleSpan(Typeface.BOLD),12,str_walk.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bt_walk.append(ssb_walk);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        //토큰정보 확인기능 추가해야함
        /*
        final DatabaseReference Autoreference = FirebaseDatabase.getInstance().getReference().child("토큰");

        Autoreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                SharedPreferences auto = getSharedPreferences("autologin", Activity.MODE_PRIVATE);

                SharedPreferences.Editor editor = auto.edit();

                String s_token = auto.getString("token",null);

                Iterator i = dataSnapshot.getChildren().iterator();

                int a=0;

                while (i.hasNext()){
                    String Autotoken = ((DataSnapshot) i.next()).getKey();

                    //2019추가
                    if (Autotoken.contains(s_token)){
                        a=1;
                    }
                    else{
                        a=0;
                    }
                }
                if (a==0){
                    Toast.makeText(Menu.this, "혹시 어플을 삭제후 다시 설치하셨습니까? 기존정보가 사라졌으며, 재로그인부탁드립니다. 만약 재설치가 아닌 의도치않았는 오류였을시엔 관리자문의바랍니다.", Toast.LENGTH_SHORT).show();
                    editor.clear();
                    editor.commit();

                    Autoreference.removeEventListener(this);

                    Intent go_intent = new Intent(Menu.this, StartExplanActivity.class);
                    startActivity(go_intent);
                    finish();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */

        bt_walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent walk_intent = new Intent(Menu.this, WalkActivity.class);
                walk_intent.putExtra("studentnum",studentnum);
                walk_intent.putExtra("name",name);
                walk_intent.putExtra("token",token);
                startActivity(walk_intent);
            }
        });

        bt_capstone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent capstone_intent = new Intent(Menu.this, CapstoneActivity.class);
                capstone_intent.putExtra("studentnum",studentnum);
                capstone_intent.putExtra("name",name);
                capstone_intent.putExtra("token",token);
                startActivity(capstone_intent);
            }
        });

        bt_lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lab_intent = new Intent(Menu.this, Lab.class);
                lab_intent.putExtra("studentnum",studentnum);
                lab_intent.putExtra("name",name);
                lab_intent.putExtra("token",token);
                startActivity(lab_intent);
            }
        });

        bt_booth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent present_intent = new Intent(Menu.this, Present.class);
                present_intent.putExtra("studentnum",studentnum);
                present_intent.putExtra("name",name);
                present_intent.putExtra("token",token);
                startActivity(present_intent);
            }
        });

        bt_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent My_intent = new Intent(Menu.this, MyActivity.class);
                My_intent.putExtra("studentnum",studentnum);
                My_intent.putExtra("name",name);
                My_intent.putExtra("token",token);
                startActivity(My_intent);
            }
        });

        bt_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Explain_intent = new Intent(Menu.this, ExplainActivity.class);
                Explain_intent.putExtra("studentnum",studentnum);
                Explain_intent.putExtra("name",name);
                Explain_intent.putExtra("token",token);
                startActivity(Explain_intent);
            }
        });

        bt_lol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lol_bottomdialogActivity lol_bottomdialogActivity = Lol_bottomdialogActivity.getInstance();

                lol_bottomdialogActivity.show(getSupportFragmentManager(),"bottomSheet");
            }
        });

        t_egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this, "이스터에그 발견!! 처묵처묵주막으로 오세요.", Toast.LENGTH_LONG).show();
                Intent Egg_intent = new Intent(Menu.this, EGG1Activity.class);
                Egg_intent.putExtra("studentnum",studentnum);
                Egg_intent.putExtra("name",name);
                Egg_intent.putExtra("token",token);
                startActivity(Egg_intent);

            }
        });


    }
}
