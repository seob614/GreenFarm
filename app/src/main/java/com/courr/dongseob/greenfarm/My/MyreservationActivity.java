package com.courr.dongseob.greenfarm.My;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.courr.dongseob.greenfarm.BoothExperience.CanActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DiffuserActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DrawingActivity;
import com.courr.dongseob.greenfarm.BoothExperience.EarringActivity;
import com.courr.dongseob.greenfarm.BoothExperience.HamburgerActivity;
import com.courr.dongseob.greenfarm.BoothExperience.MaskActivity;
import com.courr.dongseob.greenfarm.BoothExperience.EnergybarActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.GlobalfoodActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.RosemistActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.SandwichActivity;
import com.courr.dongseob.greenfarm.BoothExperience.FlowerActivity;
import com.courr.dongseob.greenfarm.BoothExperience.SikhyeActivity;
import com.courr.dongseob.greenfarm.BoothExperience.SlimeActivity;
import com.courr.dongseob.greenfarm.BoothExperience.TerrariumActivity;
import com.courr.dongseob.greenfarm.BoothExperience.TopiatyActivity;
import com.courr.dongseob.greenfarm.BoothExperience.YogurtActivity;
import com.courr.dongseob.greenfarm.Class.Reserve_ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.Reserve_ListViewItem;
import com.courr.dongseob.greenfarm.BoothExperience.DroneActivity;
import com.courr.dongseob.greenfarm.BoothExperience.VRActivity;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MyreservationActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private ListView timelist;

    //private TextView t_nowreserve;

    private Reserve_ListViewAdapter AllAdapter = new Reserve_ListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservation);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간");

        DatabaseReference atimereference = reservetimereference.child("1시30분~2시");
        DatabaseReference btimereference = reservetimereference.child("2시~2시30분");
        DatabaseReference ctimereference = reservetimereference.child("2시30분~3시");
        DatabaseReference dtimereference = reservetimereference.child("3시~3시30분");
        DatabaseReference etimereference = reservetimereference.child("3시30분~4시");
        DatabaseReference ftimereference = reservetimereference.child("4시~4시30분");
        DatabaseReference gtimereference = reservetimereference.child("4시30분~5시");

        timelist = (ListView) findViewById(R.id.timelist);

        //t_nowreserve = (TextView) findViewById(R.id.t_nowreserve);


        timelist.setAdapter(AllAdapter);



        //t_nowreserve.setText("전체: "+AllAdapter.getCount()+"개 부스예약중");

        AllAdapter.clear();


        atimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AllAdapter.notifyDataSetChanged();

                Iterator i = dataSnapshot.getChildren().iterator();
                Set<String> aset = new LinkedHashSet<>();

                while (i.hasNext()){
                    String booth = ((DataSnapshot) i.next()).getKey();

                    if (booth.contains("개수")){
                    }
                    else {
                        aset.add(booth);
                    }
                }
                for(Iterator<String> it = aset.iterator() ; it.hasNext() ; ) {
                    final String value = it.next();

                    int start = value.indexOf("(");
                    int end = value.indexOf(")");
                    String myorder = value.substring(start+1,end);
                    String mybooth = value.substring(0,start);

                    AllAdapter.addItem(MyreservationActivity.this,mybooth,"오후 1시30분~2시",myorder);


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AllAdapter.notifyDataSetChanged();

                Iterator i = dataSnapshot.getChildren().iterator();
                Set<String> bset = new LinkedHashSet<>();

                while (i.hasNext()){
                    String booth = ((DataSnapshot) i.next()).getKey();

                    if (booth.contains("개수")){
                    }
                    else {
                        bset.add(booth);
                    }
                }
                for(Iterator<String> it = bset.iterator() ; it.hasNext() ; ) {

                    final String value = it.next();

                    int start = value.indexOf("(");
                    int end = value.indexOf(")");
                    String myorder = value.substring(start+1,end);
                    String mybooth = value.substring(0,start);

                    AllAdapter.addItem(MyreservationActivity.this,mybooth,"오후 2시~2시30분",myorder);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ctimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AllAdapter.notifyDataSetChanged();

                Iterator i = dataSnapshot.getChildren().iterator();
                Set<String> cset = new LinkedHashSet<>();

                while (i.hasNext()){
                    String booth = ((DataSnapshot) i.next()).getKey();

                    if (booth.contains("개수")){
                    }
                    else {
                        cset.add(booth);
                    }
                }
                for(Iterator<String> it = cset.iterator() ; it.hasNext() ; ) {

                    final String value = it.next();

                    int start = value.indexOf("(");
                    int end = value.indexOf(")");
                    String myorder = value.substring(start+1,end);
                    String mybooth = value.substring(0,start);

                    AllAdapter.addItem(MyreservationActivity.this,mybooth,"오후 2시30분~3시",myorder);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dtimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AllAdapter.notifyDataSetChanged();

                Iterator i = dataSnapshot.getChildren().iterator();
                Set<String> dset = new LinkedHashSet<>();

                while (i.hasNext()){
                    String booth = ((DataSnapshot) i.next()).getKey();

                    if (booth.contains("개수")){
                    }
                    else {
                        dset.add(booth);
                    }
                }
                for(Iterator<String> it = dset.iterator() ; it.hasNext() ; ) {

                    final String value = it.next();

                    int start = value.indexOf("(");
                    int end = value.indexOf(")");
                    String myorder = value.substring(start+1,end);
                    String mybooth = value.substring(0,start);

                    AllAdapter.addItem(MyreservationActivity.this,mybooth,"오후 3시~3시30분",myorder);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        etimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AllAdapter.notifyDataSetChanged();

                Iterator i = dataSnapshot.getChildren().iterator();
                Set<String> eset = new LinkedHashSet<>();

                while (i.hasNext()){
                    String booth = ((DataSnapshot) i.next()).getKey();

                    if (booth.contains("개수")){
                    }
                    else {
                        eset.add(booth);
                    }
                }
                for(Iterator<String> it = eset.iterator() ; it.hasNext() ; ) {

                    final String value = it.next();

                    int start = value.indexOf("(");
                    int end = value.indexOf(")");
                    String myorder = value.substring(start+1,end);
                    String mybooth = value.substring(0,start);

                    AllAdapter.addItem(MyreservationActivity.this,mybooth,"오후 3시30분~4시",myorder);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ftimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AllAdapter.notifyDataSetChanged();

                Iterator i = dataSnapshot.getChildren().iterator();
                Set<String> fset = new LinkedHashSet<>();

                while (i.hasNext()){
                    String booth = ((DataSnapshot) i.next()).getKey();

                    if (booth.contains("개수")){
                    }
                    else {
                        fset.add(booth);
                    }
                }
                for(Iterator<String> it = fset.iterator() ; it.hasNext() ; ) {

                    final String value = it.next();

                    int start = value.indexOf("(");
                    int end = value.indexOf(")");
                    String myorder = value.substring(start+1,end);
                    String mybooth = value.substring(0,start);

                    AllAdapter.addItem(MyreservationActivity.this,mybooth,"오후 4시~4시30분",myorder);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        gtimereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AllAdapter.notifyDataSetChanged();

                Iterator i = dataSnapshot.getChildren().iterator();
                Set<String> gset = new LinkedHashSet<>();

                while (i.hasNext()){
                    String booth = ((DataSnapshot) i.next()).getKey();

                    if (booth.contains("개수")){
                    }
                    else {
                        gset.add(booth);
                    }
                }
                for(Iterator<String> it = gset.iterator() ; it.hasNext() ; ) {

                    final String value = it.next();

                    int start = value.indexOf("(");
                    int end = value.indexOf(")");
                    String myorder = value.substring(start+1,end);
                    String mybooth = value.substring(0,start);

                    AllAdapter.addItem(MyreservationActivity.this,mybooth,"오후 4시30분~5시",myorder);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //nownum 입력
        //for (int i=0;i<AllAdapter.getCount();i++){
        //    String title = AllAdapter.getTile(i);
        //    String desc = AllAdapter.getDesc(i);

        //    String time = desc.substring(3,desc.length());

        //    DatabaseReference nownumreference = FirebaseDatabase.getInstance().getReference().child("예약").child(title).child(time).child("대기");

        //    nownumreference.addValueEventListener(new ValueEventListener() {
        //        @Override
        //        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //            Iterator i = dataSnapshot.getChildren().iterator();
        //            Set<String> set = new LinkedHashSet<>();

        //            while (i.hasNext()) {
        //                set.add(((DataSnapshot) i.next()).getKey());
        //            }

        //            AllAdapter.nownumItem(String.valueOf(set.size()));
        //        }

        //        @Override
        //        public void onCancelled(@NonNull DatabaseError databaseError) {

        //        }
        //    });

        //}

        //clearitem 입력
        DatabaseReference clearreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물");

        clearreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    AllAdapter.clearItem(((DataSnapshot) i.next()).getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        timelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Reserve_ListViewItem item = (Reserve_ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.Reserve_getTitle() ;
                String descStr = item.Reserve_getDesc() ;
                Drawable iconDrawable = item.Reserve_getIcon() ;

                if (item.Reserve_getTitle().contains("VR")){
                    Intent p_intent = new Intent(MyreservationActivity.this, VRActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("드론")){
                    Intent p_intent = new Intent(MyreservationActivity.this, DroneActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("쌀을 이용한 요거트 만들기 체험")){
                    Intent p_intent = new Intent(MyreservationActivity.this, YogurtActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("Flower & Fragrance")){
                    Intent p_intent = new Intent(MyreservationActivity.this, FlowerActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("디퓨저 만들기")){
                    Intent p_intent = new Intent(MyreservationActivity.this, DiffuserActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("리틀 토피어리")){
                    Intent p_intent = new Intent(MyreservationActivity.this, TopiatyActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("취향대로 닭꼬치")){
                    Intent p_intent = new Intent(MyreservationActivity.this, HamburgerActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }
/*
                if (item.Reserve_getTitle().contains("치즈만들기 체험")){
                    Intent p_intent = new Intent(MyreservationActivity.this, CheeseActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }
*/
                //if (item.Reserve_getTitle().contains("에너지바,무엇이든지 통조림")){
                //    Intent p_intent = new Intent(MyreservationActivity.this, JuiceActivity.class);
                //    p_intent.putExtra("studentnum",studentnum);
                //    p_intent.putExtra("name",name);
                //    p_intent.putExtra("token",token);
                //    startActivity(p_intent);
                //    finish();
                //}

                if (item.Reserve_getTitle().contains("New 에너지Baam")){
                    Intent p_intent = new Intent(MyreservationActivity.this, EnergybarActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("무엇이든지 통조림")){
                    Intent p_intent = new Intent(MyreservationActivity.this, CanActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("귀생충")){
                    Intent p_intent = new Intent(MyreservationActivity.this, EarringActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("3D 마스크 만들기")){
                    Intent p_intent = new Intent(MyreservationActivity.this, MaskActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }
/*
                if (item.Reserve_getTitle().contains("Soy Candle")){
                    Intent p_intent = new Intent(MyreservationActivity.this, CandleActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }
*/
                if (item.Reserve_getTitle().contains("내 친구 슬라임")){
                    Intent p_intent = new Intent(MyreservationActivity.this, SlimeActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                //if (item.Reserve_getTitle().contains("친환경 자원과 에너지")){
                //    Intent p_intent = new Intent(MyreservationActivity.this, GreencarActivity.class);
                //    p_intent.putExtra("studentnum",studentnum);
                //    p_intent.putExtra("name",name);
                //    p_intent.putExtra("token",token);
                //    startActivity(p_intent);
                //    finish();
                //}

                if (item.Reserve_getTitle().contains("누름꽃 텀블러 만들기")){
                    Intent p_intent = new Intent(MyreservationActivity.this, DrawingActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }
/*
                if (item.Reserve_getTitle().contains("친환경 가방만들기")){
                    Intent p_intent = new Intent(MyreservationActivity.this, EcobagActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }
*/
                if (item.Reserve_getTitle().contains("테라리움")){
                    Intent p_intent = new Intent(MyreservationActivity.this, TerrariumActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                if (item.Reserve_getTitle().contains("우리 쌀 소떡소떡 만들기")){
                    Intent p_intent = new Intent(MyreservationActivity.this, SikhyeActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }

                /*
                if (item.Reserve_getTitle().contains("세계의 맛과 멋 한마당")){
                    Intent p_intent = new Intent(MyreservationActivity.this, GlobalfoodActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                    finish();
                }
                */


            }
        }) ;




    }
}
