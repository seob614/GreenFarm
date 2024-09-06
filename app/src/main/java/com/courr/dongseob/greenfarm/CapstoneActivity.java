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
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone10Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone11Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone12Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone13Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone14Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone15Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone16Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone17Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone18Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone19Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone1Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone20Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone21Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone22Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone23Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone24Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone25Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone26Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone27Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone28Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone29Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone2Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone30Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone31Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone3Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone4Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone5Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone6Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone7Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone8Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone9Activity;
import com.courr.dongseob.greenfarm.Class.Lab_ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.Lab_ListViewItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class CapstoneActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private ListView capstonelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capstone);

        ImageView imageView = (ImageView)findViewById(R.id.imageView9);
        Glide.with(this).load(R.drawable.capstonemain).into(imageView);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        capstonelist = (ListView) findViewById(R.id.capstonelist);

        final Lab_ListViewAdapter capstonearrayAdapter = new Lab_ListViewAdapter();

        capstonelist.setAdapter(capstonearrayAdapter);

        capstonearrayAdapter.clear();

        capstonearrayAdapter.addItem(" 상부접선유입식 원심력집진기");
        capstonearrayAdapter.addItem(" 물필터식 공기청정기");
        capstonearrayAdapter.addItem(" Sun protector");
        capstonearrayAdapter.addItem(" 국내산 도라지 가격결정요인분석");
        capstonearrayAdapter.addItem(" 지하철 특수 점자 블록");
        capstonearrayAdapter.addItem(" EFVR(Eco Friendly Volume Reduction)");
        capstonearrayAdapter.addItem(" 구연산 함유 폐감귤을 활용한 토양 내 카드뮴 부동화 효과의 규명");
        capstonearrayAdapter.addItem(" 다래 추출물을 이용한 기능성 천연 화장품 개발");
        capstonearrayAdapter.addItem(" 수미청");
        capstonearrayAdapter.addItem(" 세균을 이용한 환경친화적 다기능성 biopolymer");
        capstonearrayAdapter.addItem(" Been fiesta");
        capstonearrayAdapter.addItem(" 폐공장 재생형 스마트팜 모델링");
        capstonearrayAdapter.addItem(" 항산화 및 항염증 활성 검증");
        capstonearrayAdapter.addItem(" All in 쓰레기통");
        capstonearrayAdapter.addItem(" in vitro 접종 방법");
        capstonearrayAdapter.addItem(" 시각장애인을 위한 열감지 펠티어텀블러");
        capstonearrayAdapter.addItem(" 분리수거 그 동안 많이 힘드셨죠");
        capstonearrayAdapter.addItem(" 탈부착이 가능한 휠체어 보조 기구");
        capstonearrayAdapter.addItem(" 젖소에서 초유 밀도와 면역능력의 상관관계");
        capstonearrayAdapter.addItem(" 특이적 반추위 내에서의 미생물 균총의 변화");
        capstonearrayAdapter.addItem(" 미세조류 자원을 활용한 가금 면역증강제 개발 연구");
        capstonearrayAdapter.addItem(" 밀양 주요 특산물인 깻잎을 이용한 기능성 화장품 개발");
        capstonearrayAdapter.addItem(" 유자즙을 첨가한 양갱의 품질특성");
        capstonearrayAdapter.addItem(" 캐모마일 추출물을 이용한 항산화, 미백 화장품");
        capstonearrayAdapter.addItem(" 생체고분자 코팅된 리프팅 실 개발");
        capstonearrayAdapter.addItem(" Eeffect of Type of Animal Manure on Nitrous Oxide Emission from South Korea");
        capstonearrayAdapter.addItem(" 수용성 키토산 필름의 약물방출");
        capstonearrayAdapter.addItem(" VR로 만나는 AI 탁구 Coach");
        capstonearrayAdapter.addItem(" 비누가루기");
        capstonearrayAdapter.addItem(" lysophospholipids");
        capstonearrayAdapter.addItem(" 빗물받이용 우수 여과 필터");


        capstonelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Lab_ListViewItem item = (Lab_ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                if (item.getTitle().contains("상부접선유입식 원심력집진기")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone1Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("물필터식 공기청정기")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone2Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("Sun protector")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone3Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("국내산 도라지 가격결정요인분석")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone4Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("지하철 특수 점자 블록")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone5Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("EFVR(Eco Friendly Volume Reduction)")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone6Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("구연산 함유 폐감귤을 활용한 토양 내 카드뮴 부동화 효과의 규명")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone7Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("다래 추출물을 이용한 기능성 천연 화장품 개발")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone8Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("수미청")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone9Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("세균을 이용한 환경친화적 다기능성 biopolymer")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone10Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("Been fiesta")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone11Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("폐공장 재생형 스마트팜 모델링")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone12Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("항산화 및 항염증 활성 검증")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone13Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("All in 쓰레기통")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone14Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("in vitro 접종 방법")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone15Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("시각장애인을 위한 열감지 펠티어텀블러")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone16Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("분리수거 그 동안 많이 힘드셨죠")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone17Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("탈부착이 가능한 휠체어 보조 기구")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone18Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("젖소에서 초유 밀도와 면역능력의 상관관계")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone19Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("특이적 반추위 내에서의 미생물 균총의 변화")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone20Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("미세조류 자원을 활용한 가금 면역증강제 개발 연구")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone21Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("밀양 주요 특산물인 깻잎을 이용한 기능성 화장품 개발")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone22Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("유자즙을 첨가한 양갱의 품질특성")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone23Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("캐모마일 추출물을 이용한 항산화, 미백 화장품")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone24Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("생체고분자 코팅된 리프팅 실 개발")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone25Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("Eeffect of Type of Animal Manure on Nitrous Oxide Emission from South Korea")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone26Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("수용성 키토산 필름의 약물방출")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone27Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("VR로 만나는 AI 탁구 Coach")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone28Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("비누가루기")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone29Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("lysophospholipids")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone30Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("빗물받이용 우수 여과 필터")){
                    Intent p_intent = new Intent(CapstoneActivity.this, Capstone31Activity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

            }
        }) ;

        final DatabaseReference qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("캡스톤");

        qrreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    capstonearrayAdapter.clearItem(((DataSnapshot) i.next()).getKey());
                }

                capstonearrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

