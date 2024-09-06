package com.courr.dongseob.greenfarm.ClassActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.courr.dongseob.greenfarm.BoothExperience.Non2019.RicecakeActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DroneActivity;
import com.courr.dongseob.greenfarm.BoothExperience.VRActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DiffuserActivity;
import com.courr.dongseob.greenfarm.BoothExperience.FlowerActivity;
import com.courr.dongseob.greenfarm.Class.ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.ListViewItem;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GetPresentActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private TextView t_num;
    private TextView t_clear;

    private ListView unqrlist;

    private Button bt_get;

    private ArrayList<String> CouponnumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_present);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        t_num = (TextView) findViewById(R.id.t_num);
        t_clear = (TextView) findViewById(R.id.textView2);

        unqrlist = (ListView) findViewById(R.id.unqrlist);

        bt_get = (Button) findViewById(R.id.bt_get);

        final ListViewAdapter unqrarrayAdapter = new ListViewAdapter();

        unqrlist.setAdapter(unqrarrayAdapter);

        unqrarrayAdapter.clear();

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.p_it),"VR체험", Color.BLACK);
        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.btdrone),"드론체험", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.ricecake),"떡메치기", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.flower),"미니 꽃다발", Color.BLACK);
        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.diffuser),"디퓨저", Color.BLACK);
        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.flower),"미니 꽃다발", Color.BLACK);
        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.diffuser),"디퓨저", Color.BLACK);
        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.humidifier),"가습기",Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cheese),"치즈만들기", Color.BLACK);
        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.bg),"닥터피쉬", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.juice),"음료만들기", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.photographe),"사진촬영", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.soap),"천연비누", Color.BLACK);
        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.candle),"천연향초", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.tofu),"두부만들기", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.greencar),"친환경 자동차", Color.BLACK);
        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.bg),"로즈 미스트", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.buddelschiff),"테라리움", Color.BLACK);

        unqrarrayAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.market),"로컬푸드", Color.BLACK);



        //수정부분

        DatabaseReference qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물");

        qrreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    unqrarrayAdapter.removeItem(((DataSnapshot) i.next()).getKey());
                }

                if (unqrarrayAdapter.getCount()==0){
                    t_num.setText(" 부스 ALL CLEAR!!");
                    t_num.setGravity(Gravity.CENTER);
                    t_clear.setText("쿠폰버튼 클릭하여 응모권을 확인하세요.");

                }
                else{
                    t_num.setText(" 전체: "+unqrarrayAdapter.getCount()+"개");
                }


                unqrarrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        unqrlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                if (item.getTitle().contains("VR")){
                    Intent p_intent = new Intent(GetPresentActivity.this, VRActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("드론")){
                    Intent p_intent = new Intent(GetPresentActivity.this, DroneActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("떡메치기")){
                    Intent p_intent = new Intent(GetPresentActivity.this, RicecakeActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("미니 꽃다발")){
                    Intent p_intent = new Intent(GetPresentActivity.this, FlowerActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("디퓨저")){
                    Intent p_intent = new Intent(GetPresentActivity.this, DiffuserActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }



            }
        }) ;



        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unqrarrayAdapter.getCount()==0){

                    DatabaseReference Howcouponreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("쿠폰");

                    Howcouponreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue()!=null){
                                Intent intent = new Intent(GetPresentActivity.this, CouponActivity.class);
                                intent.putExtra("studentnum", studentnum);
                                intent.putExtra("name",name);
                                intent.putExtra("token",token);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                if (studentnum.length()==8){
                                    Toast.makeText(GetPresentActivity.this, "학생 외엔 응모권을 받을수 없습니다.", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    final DatabaseReference getcoupon = FirebaseDatabase.getInstance().getReference().child("쿠폰");

                                    Map<String,Object> map = new HashMap<>();
                                    map.put(studentnum,name);

                                    getcoupon.updateChildren(map);

                                    final DatabaseReference getcouponnum = FirebaseDatabase.getInstance().getReference().child("쿠폰");

                                    getcouponnum.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            Iterator i = dataSnapshot.getChildren().iterator();

                                            while (i.hasNext()) {
                                                CouponnumList.add(((DataSnapshot) i.next()).getKey());
                                            }

                                            DatabaseReference couponreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("쿠폰");
                                            couponreference.setValue(CouponnumList.size());

                                            getcouponnum.removeEventListener(this);

                                            Intent intent = new Intent(GetPresentActivity.this, CouponActivity.class);
                                            intent.putExtra("studentnum", studentnum);
                                            intent.putExtra("name",name);
                                            intent.putExtra("token",token);
                                            startActivity(intent);
                                            finish();
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



                }
                else{
                    Toast.makeText(GetPresentActivity.this, "아직 QR코드를 받지 못한 부스가 있습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
