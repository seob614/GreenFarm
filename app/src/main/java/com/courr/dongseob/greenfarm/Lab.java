package com.courr.dongseob.greenfarm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.Class.Lab_ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.Lab_ListViewItem;
import com.courr.dongseob.greenfarm.LabTool.Lab1Activity;
import com.courr.dongseob.greenfarm.LabTool.Lab2Activity;
import com.courr.dongseob.greenfarm.LabTool.Lab3Activity;
import com.courr.dongseob.greenfarm.LabTool.Lab4Activity;
import com.courr.dongseob.greenfarm.LabTool.Lab5Activity;
import com.courr.dongseob.greenfarm.LabTool.NON2019.Lab6Activity;
import com.courr.dongseob.greenfarm.LabTool.NON2019.Lab7Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class Lab extends AppCompatActivity {

    //private String token;

    private String studentnum;
    private String name;
    private String token;

    private ListView lablist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        ImageView imageView = (ImageView)findViewById(R.id.imageView9);
        Glide.with(this).load(R.drawable.labmain).into(imageView);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        lablist = (ListView) findViewById(R.id.lablist);

        final Lab_ListViewAdapter labarrayAdapter = new Lab_ListViewAdapter();

        lablist.setAdapter(labarrayAdapter);

        labarrayAdapter.clear();

        labarrayAdapter.addItem(" 농생명과학기술을 활용한 작물연구");
        labarrayAdapter.addItem(" 스마트 시대의 원예과학기술");
        labarrayAdapter.addItem(" 스마트 기능성 바이오소재");
        labarrayAdapter.addItem(" 다양한 분야의 바이오산업기술");
        labarrayAdapter.addItem(" 지속가능한 친환경 에너지시스템 예측");
        //labarrayAdapter.addItem(" 식품자원경제학과 연구성과 전시");
        //labarrayAdapter.addItem(" 선충연구센터 연구성과 전시체험");

        lablist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Lab_ListViewItem item = (Lab_ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                if (item.getTitle().contains("농생명과학기술을 활용한 작물연구")){
                    Intent p_intent = new Intent(Lab.this, Lab1Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("스마트 시대의 원예과학기술")){
                    Intent p_intent = new Intent(Lab.this, Lab2Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("스마트 기능성 바이오소재")){
                    Intent p_intent = new Intent(Lab.this, Lab3Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("다양한 분야의 바이오산업기술")){
                    Intent p_intent = new Intent(Lab.this, Lab4Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("지속가능한 친환경 에너지시스템 예측")){
                    Intent p_intent = new Intent(Lab.this, Lab5Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }
/*
                if (item.getTitle().contains("식품자원경제학과 연구성과 전시")){
                    Intent p_intent = new Intent(Lab.this, Lab6Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("선충연구센터 연구성과 전시체험")){
                    Intent p_intent = new Intent(Lab.this, Lab7Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }
*/

            }
        }) ;

        final DatabaseReference qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("연구");

        qrreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    labarrayAdapter.clearItem(((DataSnapshot) i.next()).getKey());
                }

                labarrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

