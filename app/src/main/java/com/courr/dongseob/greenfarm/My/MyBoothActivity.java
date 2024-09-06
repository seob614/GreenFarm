package com.courr.dongseob.greenfarm.My;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.courr.dongseob.greenfarm.BoothExperience.SikhyeActivity;
import com.courr.dongseob.greenfarm.BoothExperience.CanActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DiffuserActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DrawingActivity;
import com.courr.dongseob.greenfarm.BoothExperience.EarringActivity;
import com.courr.dongseob.greenfarm.BoothExperience.HamburgerActivity;
import com.courr.dongseob.greenfarm.BoothExperience.MaskActivity;
import com.courr.dongseob.greenfarm.BoothExperience.SlimeActivity;
import com.courr.dongseob.greenfarm.BoothExperience.TopiatyActivity;
import com.courr.dongseob.greenfarm.BoothExperience.YogurtActivity;
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
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone3Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone4Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone5Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone6Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone7Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone8Activity;
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone9Activity;
import com.courr.dongseob.greenfarm.Class.Lab_ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.Lab_ListViewItem;
import com.courr.dongseob.greenfarm.LabTool.Lab1Activity;
import com.courr.dongseob.greenfarm.LabTool.Lab2Activity;
import com.courr.dongseob.greenfarm.LabTool.Lab3Activity;
import com.courr.dongseob.greenfarm.LabTool.Lab4Activity;
import com.courr.dongseob.greenfarm.LabTool.Lab5Activity;
import com.courr.dongseob.greenfarm.BoothExperience.DroneActivity;
import com.courr.dongseob.greenfarm.BoothExperience.EnergybarActivity;
import com.courr.dongseob.greenfarm.BoothExperience.FlowerActivity;
import com.courr.dongseob.greenfarm.BoothExperience.TerrariumActivity;
import com.courr.dongseob.greenfarm.BoothExperience.VRActivity;
import com.courr.dongseob.greenfarm.Class.ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.ListViewItem;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class MyBoothActivity extends AppCompatActivity {


    private String studentnum;
    private String name;
    private String token;

    private TextView t_num;

    private ListView unqrlist;

    private Spinner spinner;

    private ArrayAdapter<String> spinnerarrayAdapter;
    private ArrayList<String> spinnerarrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booth);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        t_num = (TextView) findViewById(R.id.t_num);

        unqrlist = (ListView) findViewById(R.id.unqrlist);

        spinner = (Spinner) findViewById(R.id.spinner);

        final ListViewAdapter unqrarrayAdapter = new ListViewAdapter();

        final Lab_ListViewAdapter labarrayAdapter = new Lab_ListViewAdapter();

        final Lab_ListViewAdapter capstonearrayAdapter = new Lab_ListViewAdapter();



        spinnerarrayList.add("문화 전시체험");
        spinnerarrayList.add("연구성과 전시체험");
        spinnerarrayList.add("캡스톤디자인 경진대회");

        spinnerarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spinnerarrayList);

        spinner.setAdapter(spinnerarrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    unqrlist.setAdapter(unqrarrayAdapter);

                    unqrarrayAdapter.clear();

                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.p_it)," VR체험", Color.BLACK);
                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.btdrone)," 드론체험" , Color.BLACK);

                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.yogurt)," 쌀을 이용한 요거트 만들기 체험", Color.BLACK);

                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.flower)," Flower & Fragrance", Color.BLACK);
                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.diffuser)," 디퓨저 만들기", Color.BLACK);
                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.ff)," 리틀 토피어리", Color.BLACK);
                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.humidifier)," 스칸디아모스 가습기", Color.BLACK);
                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.bugflower)," 식충식물 전시", Color.BLACK);

                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.stick)," 취향대로 닭꼬치", Color.BLACK);
                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.cheese)," 치즈만들기 체험", Color.BLACK);

                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.juice2)," 에너지바,무엇이든지 통조림", Color.BLACK);
                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.energybar)," New 에너지Baam", Color.BLACK);
                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.can)," 무엇이든지 통조림", Color.BLACK);

                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.earing)," 귀생충(키링 및 귀걸이 만들기)", Color.BLACK);

                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.mask)," 3D 마스크 만들기", Color.BLACK);
                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.candle)," Soy Candle", Color.BLACK);

                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.slime)," 내 친구 슬라임",Color.BLACK);

                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.greencar)," 친환경 자원과 에너지", Color.BLACK);
                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.tumblr)," 누름꽃 텀블러 만들기", Color.BLACK);
                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.ecobag)," 친환경 가방만들기", Color.BLACK);

                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.terrarium)," 테라리움", Color.BLACK);

                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.globalfood)," 세계의 맛과 멋 한마당", Color.BLACK);


                    //전시체험부스
                    unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.sikhye)," 우리 쌀 소떡소떡 만들기", Color.BLACK);
                    //unqrarrayAdapter.addItem(ContextCompat.getDrawable(MyBoothActivity.this, R.drawable.agricultural)," 부속농장", Color.BLACK);



                    DatabaseReference qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물");

                    qrreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Iterator i = dataSnapshot.getChildren().iterator();

                            while (i.hasNext()) {
                                unqrarrayAdapter.removeItem(((DataSnapshot) i.next()).getKey());
                            }

                            if (unqrarrayAdapter.getCount()==0){
                                t_num.setText("문화전시 체험 ALL CLEAR!!");
                                t_num.setGravity(Gravity.CENTER);

                            }
                            else{
                                t_num.setText("문화전시 체험부스 중 전체: "+unqrarrayAdapter.getCount()+"개 남았음");
                            }


                            unqrarrayAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    unqrlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                            ListViewItem item = (ListViewItem) adapterView.getItemAtPosition(i) ;

                            if (item.getTitle().contains("VR")){
                                Intent p_intent = new Intent(MyBoothActivity.this, VRActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("드론")){
                                Intent p_intent = new Intent(MyBoothActivity.this, DroneActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("쌀을 이용한 요거트 만들기 체험")){
                                Intent p_intent = new Intent(MyBoothActivity.this, YogurtActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("Flower & Fragrance")){
                                Intent p_intent = new Intent(MyBoothActivity.this, FlowerActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("디퓨저 만들기")){
                                Intent p_intent = new Intent(MyBoothActivity.this, DiffuserActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("리틀 토피어리")){
                                Intent p_intent = new Intent(MyBoothActivity.this, TopiatyActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("취향대로 닭꼬치")){
                                Intent p_intent = new Intent(MyBoothActivity.this, HamburgerActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
/*
                            if (item.getTitle().contains("치즈만들기 체험")){
                                Intent p_intent = new Intent(MyBoothActivity.this, CheeseActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
*/
                            //if (item.getTitle().contains("에너지바,무엇이든지 통조림")){
                            //    Intent p_intent = new Intent(MyBoothActivity.this, JuiceActivity.class);
                            //    p_intent.putExtra("studentnum",studentnum);
                            //    p_intent.putExtra("name",name);
                            //    p_intent.putExtra("token",token);
                            //    startActivity(p_intent);
                            //}

                            if (item.getTitle().contains("New 에너지Baam")){
                                Intent p_intent = new Intent(MyBoothActivity.this, EnergybarActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("무엇이든지 통조림")){
                                Intent p_intent = new Intent(MyBoothActivity.this, CanActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("귀생충(키링 및 귀걸이 만들기)")){
                                Intent p_intent = new Intent(MyBoothActivity.this, EarringActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("3D 마스크 만들기")){
                                Intent p_intent = new Intent(MyBoothActivity.this, MaskActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
/*
                            if (item.getTitle().contains("Soy Candle")){
                                Intent p_intent = new Intent(MyBoothActivity.this, CandleActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
*/
                            if (item.getTitle().contains("내 친구 슬라임")){
                                Intent p_intent = new Intent(MyBoothActivity.this, SlimeActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            //if (item.getTitle().contains("친환경 자원과 에너지")){
                            //    Intent p_intent = new Intent(MyBoothActivity.this, GreencarActivity.class);
                            //    p_intent.putExtra("studentnum",studentnum);
                            //    p_intent.putExtra("name",name);
                            //    p_intent.putExtra("token",token);
                            //    startActivity(p_intent);
                            //}

                            if (item.getTitle().contains("누름꽃 텀블러 만들기")){
                                Intent p_intent = new Intent(MyBoothActivity.this, DrawingActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
/*
                            if (item.getTitle().contains("친환경 가방만들기")){
                                Intent p_intent = new Intent(MyBoothActivity.this, EcobagActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
*/
                            if (item.getTitle().contains("테라리움")){
                                Intent p_intent = new Intent(MyBoothActivity.this, TerrariumActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            /*
                            if (item.getTitle().contains("세계의 맛과 멋 한마당")){
                                Intent p_intent = new Intent(MyBoothActivity.this, GlobalfoodActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
                            */

                            //전시체험

                            if (item.getTitle().contains("우리 쌀 소떡소떡 만들기")){
                                Intent p_intent = new Intent(MyBoothActivity.this, SikhyeActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
                            /*

                            if (item.getTitle().contains("부속농장")){
                                Intent p_intent = new Intent(MyBoothActivity.this, FarmActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (item.getTitle().contains("식충식물 전시")){
                                Intent p_intent = new Intent(MyBoothActivity.this, PlantActivity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
                            */
                        }
                    });

                }

                if (i==1){
                    unqrlist.setAdapter(labarrayAdapter);

                    labarrayAdapter.clear();

                    labarrayAdapter.addItem(" 농생명과학기술을 활용한 작물연구");
                    labarrayAdapter.addItem(" 한천 웰빙제품 전시 및 연구");
                    labarrayAdapter.addItem(" 스마트 기능성 바이오소재");
                    labarrayAdapter.addItem(" 다양한 분야의 바이오산업기술");
                    labarrayAdapter.addItem(" 지속가능한 친환경 에너지시스템 예측");
                    //labarrayAdapter.addItem(" 식품자원경제학과 연구성과 전시");
                    //labarrayAdapter.addItem(" 선충연구센터 연구성과 전시체험");

                    DatabaseReference qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("연구");

                    qrreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Iterator i = dataSnapshot.getChildren().iterator();

                            while (i.hasNext()) {
                                labarrayAdapter.removeItem(((DataSnapshot) i.next()).getKey());
                            }

                            if (labarrayAdapter.getCount()==0){
                                t_num.setText("연구성과 전시체험 ALL CLEAR!!");
                                t_num.setGravity(Gravity.CENTER);

                            }
                            else{
                                t_num.setText("연구성과 전시체험 중 전체: "+labarrayAdapter.getCount()+"개 남았음");
                            }


                            labarrayAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    unqrlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                            Lab_ListViewItem lab_item = (Lab_ListViewItem) adapterView.getItemAtPosition(i);

                            //연구실 부스

                            if (lab_item.getTitle().contains("농생명과학기술을 활용한 작물연구")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Lab1Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("한천 웰빙제품 전시 및 연구")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Lab2Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("스마트 기능성 바이오소재")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Lab3Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("다양한 분야의 바이오산업기술")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Lab4Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("지속가능한 친환경 에너지시스템 예측")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Lab5Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
/*
                            if (lab_item.getTitle().contains("쌀 수입보장보험 도입방안 및 재해·재난 예방을 위한 저수지개보수사업에 대한 지불의사금액 추정")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Lab6Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("선충연구센터 연구성과 전시체험")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Lab7Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
                            */
                        }
                    });
                }

                if (i==2){

                    unqrlist.setAdapter(capstonearrayAdapter);

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


                    DatabaseReference qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("캡스톤");

                    qrreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Iterator i = dataSnapshot.getChildren().iterator();

                            while (i.hasNext()) {
                                capstonearrayAdapter.removeItem(((DataSnapshot) i.next()).getKey());
                            }

                            if (capstonearrayAdapter.getCount()==0){
                                t_num.setText("캡스톤디자인 ALL CLEAR!!");
                                t_num.setGravity(Gravity.CENTER);

                            }
                            else{
                                t_num.setText("캡스톤디자인 중 전체: "+capstonearrayAdapter.getCount()+"개 남았음");
                            }


                            capstonearrayAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    unqrlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                            Lab_ListViewItem lab_item = (Lab_ListViewItem) adapterView.getItemAtPosition(i);

                            //캡스톤

                            if (lab_item.getTitle().contains("상부접선유입식 원심력집진기")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone1Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("물필터식 공기청정기")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone2Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("Sun protector")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone3Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("국내산 도라지 가격결정요인분석")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone4Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("지하철 특수 점자 블록")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone5Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("EFVR(Eco Friendly Volume Reduction)")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone6Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("구연산 함유 폐감귤을 활용한 토양 내 카드뮴 부동화 효과의 규명")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone7Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("다래 추출물을 이용한 기능성 천연 화장품 개발")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone8Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("수미청")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone9Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("세균을 이용한 환경친화적 다기능성 biopolymer")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone10Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("Been fiesta")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone11Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("폐공장 재생형 스마트팜 모델링")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone12Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("항산화 및 항염증 활성 검증")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone13Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("All in 쓰레기통")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone14Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("in vitro 접종 방법")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone15Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("시각장애인을 위한 열감지 펠티어텀블러")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone16Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("분리수거 그 동안 많이 힘드셨죠")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone17Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("탈부착이 가능한 휠체어 보조 기구")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone18Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("젖소에서 초유 밀도와 면역능력의 상관관계")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone19Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("특이적 반추위 내에서의 미생물 균총의 변화")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone20Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("미세조류 자원을 활용한 가금 면역증강제 개발 연구")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone21Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("밀양 주요 특산물인 깻잎을 이용한 기능성 화장품 개발")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone22Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("유자즙을 첨가한 양갱의 품질특성")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone23Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("캐모마일 추출물을 이용한 항산화, 미백 화장품")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone24Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("생체고분자 코팅된 리프팅 실 개발")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone25Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("Eeffect of Type of Animal Manure on Nitrous Oxide Emission from South Korea")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone26Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("수용성 키토산 필름의 약물방출")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone27Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("VR로 만나는 AI 탁구 Coach")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone28Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("비누가루기")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone29Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("lysophospholipids")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone30Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }

                            if (lab_item.getTitle().contains("빗물받이용 우수 여과 필터")){
                                Intent p_intent = new Intent(MyBoothActivity.this, Capstone30Activity.class);
                                p_intent.putExtra("studentnum",studentnum);
                                p_intent.putExtra("name",name);
                                p_intent.putExtra("token",token);
                                startActivity(p_intent);
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        //unqrlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
        //    public void onItemClick(AdapterView parent, View v, int position, long id) {
        //        // get item
        //        ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

        //        Lab_ListViewItem lab_item = (Lab_ListViewItem) parent.getItemAtPosition(position);

        //        String titleStr = item.getTitle() ;
        //        String descStr = item.getDesc() ;
        //        Drawable iconDrawable = item.getIcon() ;

        //    }
        //}) ;

    }
}
