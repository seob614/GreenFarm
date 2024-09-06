package com.courr.dongseob.greenfarm.My;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.courr.dongseob.greenfarm.Class.Master_ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.Reserve_ListViewAdapter;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MasterActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private String master;

    private Spinner spinner;

    private ArrayAdapter<String> spinnerarrayAdapter;
    private ArrayList<String> spinnerarrayList = new ArrayList<>();


    private ListView boothlist;

    private TextView t_allnum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);


        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        master = intent.getStringExtra("master");

        spinner = (Spinner) findViewById(R.id.spinner);

        boothlist = (ListView) findViewById(R.id.boothlist);

        t_allnum = (TextView) findViewById(R.id.t_allnum);

        if (master.contains("IT응용공학과")){
            spinnerarrayList.add("VR체험");
            spinnerarrayList.add("드론체험");
        }
        if (master.contains("식물생명과학과")){
            spinnerarrayList.add("우리 쌀과 세계 음식의 만남");
        }
        if (master.contains("원예생명과학과")){
            spinnerarrayList.add("미니꽃다발만들기");
            spinnerarrayList.add("디퓨저 만들기");
            spinnerarrayList.add("스칸디아모스 가습기");
        }
        if (master.contains("동물생명자원과학과")){
            spinnerarrayList.add("햄 in The 샌드위치");
            spinnerarrayList.add("치즈만들기 체험");
        }
        if (master.contains("식품공학과")){
            spinnerarrayList.add("New 에너지Baam");
            spinnerarrayList.add("무엇이든지 통조림");

        }
        if (master.contains("생명환경화학과")){
            spinnerarrayList.add("당신을 위해서라면");
        }
        if (master.contains("바이오소재과학과")){
            spinnerarrayList.add("Oatmeal Soap");
            spinnerarrayList.add("Soy Candle");
        }
        if (master.contains("바이오산업기계공학과")){
            spinnerarrayList.add("두부를 먹는 당신은 몸짱");
        }
        if (master.contains("바이오환경에너지학과")){
            spinnerarrayList.add("로즈 미스트");
            spinnerarrayList.add("친환경 가방만들기");
        }
        if (master.contains("조경학과")){
            spinnerarrayList.add("테라리움");
        }
        if (master.contains("식품자원경제학과")){
            spinnerarrayList.add("팝콘관리");
        }
        if (master.contains("일반")){
            spinnerarrayList.add("세계의 맛과 멋 한마당");
        }
        if (master.contains("마스터")){
            spinnerarrayList.add("VR체험");
            spinnerarrayList.add("드론체험");
            spinnerarrayList.add("우리 쌀과 세계 음식의 만남");
            spinnerarrayList.add("미니꽃다발만들기");
            spinnerarrayList.add("디퓨저 만들기");
            spinnerarrayList.add("스칸디아모스 가습기");
            spinnerarrayList.add("햄 in The 샌드위치");
            spinnerarrayList.add("치즈만들기 체험");
            spinnerarrayList.add("New 에너지Baam");
            spinnerarrayList.add("무엇이든지 통조림");
            spinnerarrayList.add("당신을 위해서라면");
            spinnerarrayList.add("Oatmeal Soap");
            spinnerarrayList.add("Soy Candle");
            spinnerarrayList.add("두부를 먹는 당신은 몸짱");
            spinnerarrayList.add("로즈 미스트");
            spinnerarrayList.add("친환경 가방만들기");
            spinnerarrayList.add("테라리움");
            spinnerarrayList.add("세계의 맛과 멋 한마당");
            spinnerarrayList.add("팝콘관리");



        }

        spinnerarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spinnerarrayList);

        spinner.setAdapter(spinnerarrayAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (spinnerarrayList.get(i).contains("VR체험")){

                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("VR체험");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();



                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
                if (spinnerarrayList.get(i).contains("드론체험")){

                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("드론체험");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("우리 쌀과 세계 음식의 만남")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("우리 쌀과 세계 음식의 만남");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("미니꽃다발만들기")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("미니꽃다발만들기");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("디퓨저 만들기")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("디퓨저 만들기");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("스칸디아모스 가습기")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("스칸디아모스 가습기");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("햄 in The 샌드위치")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("햄 in The 샌드위치");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("치즈만들기 체험")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("치즈만들기 체험");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("New 에너지Baam")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("New 에너지Baam");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("무엇이든지 통조림")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("무엇이든지 통조림");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("당신을 위해서라면")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("당신을 위해서라면");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("Oatmeal Soap")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("Oatmeal Soap");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("Soy Candle")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("Soy Candle");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("두부를 먹는 당신은 몸짱")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("두부를 먹는 당신은 몸짱");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("로즈 미스트")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("로즈 미스트");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("친환경 가방만들기")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("친환경 가방만들기");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("테라리움")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("테라리움");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("세계의 맛과 멋 한마당")){
                    final DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("예약").child("세계의 맛과 멋 한마당");

                    reservetimereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();

                            final DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
                            final DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
                            final DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
                            final DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
                            final DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
                            final DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
                            final DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");


                            atimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 1시30분~2시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 1시30분~2시");

                                    }
                                    atimereference.removeEventListener(this);

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            btimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시~2시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시~2시30분");

                                    }
                                    btimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ctimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 2시30분~3시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 2시30분~3시");

                                    }

                                    ctimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            dtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시~3시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시~3시30분");

                                    }
                                    dtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            etimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 3시30분~4시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();

                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 3시30분~4시");

                                    }
                                    etimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            ftimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시~4시30분");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시~4시30분");

                                    }
                                    ftimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            gtimereference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //AllAdapter.removeItem("오후 4시30분~5시");

                                    AllAdapter.notifyDataSetChanged();

                                    Iterator i = dataSnapshot.getChildren().iterator();
                                    Set<String> set = new LinkedHashSet<>();

                                    while (i.hasNext()){
                                        String who = ((DataSnapshot) i.next()).getKey();


                                        if (who.contains("인원")||who.contains("대기")){
                                        }
                                        else {
                                            set.add(who);
                                        }
                                    }
                                    for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                        final String whostudentnum = it.next();

                                        AllAdapter.addItem(whostudentnum,"오후 4시30분~5시");

                                    }
                                    gtimereference.removeEventListener(this);
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (spinnerarrayList.get(i).contains("팝콘관리")){
                    DatabaseReference popcornreference = FirebaseDatabase.getInstance().getReference().child("팝콘");

                    popcornreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Master_ListViewAdapter AllAdapter = new Master_ListViewAdapter();

                            boothlist.setAdapter(AllAdapter);
                            AllAdapter.clear();


                            Iterator i = dataSnapshot.getChildren().iterator();
                            Set<String> set = new LinkedHashSet<>();

                            while (i.hasNext()){
                                String who = ((DataSnapshot) i.next()).getKey();

                                set.add(who);
                            }

                            for(Iterator<String> it = set.iterator() ; it.hasNext() ; ) {
                                final String whostudentnum = it.next();

                                DatabaseReference popcornnumreference = FirebaseDatabase.getInstance().getReference().child("팝콘").child(whostudentnum);

                                popcornnumreference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        AllAdapter.notifyDataSetChanged();

                                        AllAdapter.addItem(whostudentnum,"이용횟수: "+(3-Integer.parseInt(dataSnapshot.getValue().toString().substring(0,1)))+"회");
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


}
