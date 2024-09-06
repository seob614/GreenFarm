package com.courr.dongseob.greenfarm;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.courr.dongseob.greenfarm.Class.MasterchatlistActivity;
import com.courr.dongseob.greenfarm.My.AlramActivity;
import com.courr.dongseob.greenfarm.My.MasterActivity;
import com.courr.dongseob.greenfarm.My.MyBoothActivity;
import com.courr.dongseob.greenfarm.My.MychatActivity;
import com.courr.dongseob.greenfarm.My.MyticketActivity;
import com.courr.dongseob.greenfarm.My.MyreservationActivity;
import com.courr.dongseob.greenfarm.My.PointerActivity;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MyActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private TextView t_allbooth;
    private TextView t_clearbooth;

    private ImageView i_alram;

    private TextView t_capstone;
    private TextView t_booth;
    private TextView t_coffee;

    private TextView t_point;

    private ImageView i_myticket;
    private ImageView i_myreservation;
    private ImageView i_mybooth;

    private Button bt_chat;

    private Button bt_master;

    private Button bt_pointer;

    private Button bt_fcm;

    private Toolbar mytoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        t_allbooth = (TextView) findViewById(R.id.t_allbooth);
        t_clearbooth = (TextView) findViewById(R.id.t_clearbooth);

        i_alram = (ImageView) findViewById(R.id.i_alram);

        t_capstone = (TextView) findViewById(R.id.t_capstone);
        t_booth = (TextView) findViewById(R.id.t_booth);
        t_coffee = (TextView) findViewById(R.id.t_coffee);

        t_point = (TextView) findViewById(R.id.t_point);

        i_myticket = (ImageView) findViewById(R.id.i_myticket);
        i_myreservation = (ImageView) findViewById(R.id.i_myreservation);
        i_mybooth = (ImageView) findViewById(R.id.i_mybooth);

        bt_chat = (Button) findViewById(R.id.bt_chat);

        bt_master = (Button) findViewById(R.id.bt_master);

        bt_pointer = (Button) findViewById(R.id.bt_pointer);

        bt_fcm = (Button) findViewById(R.id.bt_fcm);

        i_alram.setClickable(true);

        i_myticket.setClickable(true);
        i_myreservation.setClickable(true);
        i_mybooth.setClickable(true);

        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        mytoolbar.setTitle("MY 페이지");

        t_point.setText("0");

        final DatabaseReference pointreference = FirebaseDatabase.getInstance().getReference().child("포인트");

        //나중에 바꿀거:  DatabaseReference booth_pointreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드")로 들어가서 벨류주기

        pointreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                t_point.setText("0");

                final Set<String> pointset = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();

                pointset.clear();

                while (i.hasNext()) {
                    pointset.add(((DataSnapshot) i.next()).getKey());
                }

                final DatabaseReference booth_pointreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물");

                booth_pointreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Set<String> set = new HashSet<>();
                        Iterator i = dataSnapshot.getChildren().iterator();

                        set.clear();

                        while (i.hasNext()) {
                            Iterator<String> point_i = pointset.iterator();

                            String i_value = ((DataSnapshot) i.next()).getKey();

                            while (point_i.hasNext()){
                                String value = point_i.next();

                                if (value.contains(i_value)){
                                    final DatabaseReference getpointreference = FirebaseDatabase.getInstance().getReference().child("포인트").child(value);

                                    getpointreference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            int value = Integer.parseInt(dataSnapshot.getValue().toString());

                                            int t_value = Integer.parseInt(t_point.getText().toString());

                                            t_point.setText(String.valueOf(t_value+value));

                                            getpointreference.removeEventListener(this);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                final DatabaseReference lab_pointreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("연구");

                lab_pointreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Set<String> set = new HashSet<>();
                        Iterator i = dataSnapshot.getChildren().iterator();

                        set.clear();

                        while (i.hasNext()) {
                            Iterator<String> point_i = pointset.iterator();

                            String i_value = ((DataSnapshot) i.next()).getKey();

                            while (point_i.hasNext()){
                                String value = point_i.next();

                                if (value.contains(i_value)){
                                    final DatabaseReference getpointreference = FirebaseDatabase.getInstance().getReference().child("포인트").child(value);

                                    getpointreference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            int value = Integer.parseInt(dataSnapshot.getValue().toString());

                                            int t_value = Integer.parseInt(t_point.getText().toString());

                                            t_point.setText(String.valueOf(t_value+value));

                                            getpointreference.removeEventListener(this);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                final DatabaseReference capstone_pointreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("캡스톤");

                capstone_pointreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Set<String> set = new HashSet<>();
                        Iterator i = dataSnapshot.getChildren().iterator();

                        set.clear();

                        while (i.hasNext()) {
                            Iterator<String> point_i = pointset.iterator();

                            String i_value = ((DataSnapshot) i.next()).getKey();

                            while (point_i.hasNext()){
                                String value = point_i.next();

                                if (value.contains(i_value)){
                                    final DatabaseReference getpointreference = FirebaseDatabase.getInstance().getReference().child("포인트").child(value);

                                    getpointreference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            int value = Integer.parseInt(dataSnapshot.getValue().toString());

                                            int t_value = Integer.parseInt(t_point.getText().toString());

                                            t_point.setText(String.valueOf(t_value+value));

                                            getpointreference.removeEventListener(this);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                final DatabaseReference walk_pointreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("둘레길");

                walk_pointreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Set<String> set = new HashSet<>();
                        Iterator i = dataSnapshot.getChildren().iterator();

                        set.clear();

                        while (i.hasNext()) {
                            Iterator<String> point_i = pointset.iterator();

                            String i_value = ((DataSnapshot) i.next()).getKey();

                            while (point_i.hasNext()){
                                String value = point_i.next();

                                if (value.contains(i_value)){
                                    final DatabaseReference getpointreference = FirebaseDatabase.getInstance().getReference().child("포인트").child(value);

                                    getpointreference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            int value = Integer.parseInt(dataSnapshot.getValue().toString());

                                            int t_value = Integer.parseInt(t_point.getText().toString());

                                            t_point.setText(String.valueOf(t_value+value));

                                            getpointreference.removeEventListener(this);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            }
                        }

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

        //문화체험 부스 체험개수
        DatabaseReference booth_qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물");

        booth_qrreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();

                set.clear();

                while (i.hasNext()) {
                    set.add(((DataSnapshot) i.next()).getKey());
                }

                String str_booth = "  총 21개 부스 중 "+set.size()+"개 체험완료";
                SpannableStringBuilder ssb_booth = new SpannableStringBuilder(str_booth);
                ssb_booth.setSpan(new ForegroundColorSpan(Color.parseColor("#FFCC0000")), 13,str_booth.length()-5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb_booth.setSpan(new StyleSpan(Typeface.BOLD),13,str_booth.length()-5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                t_booth.setText(ssb_booth);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //연구성과 체험개수
        DatabaseReference lab_qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("연구");

        lab_qrreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();

                set.clear();

                while (i.hasNext()) {
                    set.add(((DataSnapshot) i.next()).getKey());
                }

                String str_booth = "  총 7개 부스 중 "+set.size()+"개 체험완료";
                SpannableStringBuilder ssb_booth = new SpannableStringBuilder(str_booth);
                ssb_booth.setSpan(new ForegroundColorSpan(Color.parseColor("#FFCC0000")), 12,str_booth.length()-5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb_booth.setSpan(new StyleSpan(Typeface.BOLD),12,str_booth.length()-5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                t_coffee.setText(ssb_booth);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //캡스톤 체험개수
        DatabaseReference capstone_qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("캡스톤");

        capstone_qrreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();

                set.clear();

                while (i.hasNext()) {
                    set.add(((DataSnapshot) i.next()).getKey());
                }

                String str_booth = "  총 30개 부스 중 "+set.size()+"개 체험완료";
                SpannableStringBuilder ssb_booth = new SpannableStringBuilder(str_booth);
                ssb_booth.setSpan(new ForegroundColorSpan(Color.parseColor("#FFCC0000")), 13,str_booth.length()-5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb_booth.setSpan(new StyleSpan(Typeface.BOLD),13,str_booth.length()-5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                t_capstone.setText(ssb_booth);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        i_alram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alram_intent = new Intent(MyActivity.this, AlramActivity.class);
                alram_intent.putExtra("studentnum",studentnum);
                alram_intent.putExtra("name",name);
                alram_intent.putExtra("token",token);
                startActivity(alram_intent);
            }
        });

        i_myticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myticket_intent = new Intent(MyActivity.this, MyticketActivity.class);
                myticket_intent.putExtra("studentnum",studentnum);
                myticket_intent.putExtra("name",name);
                myticket_intent.putExtra("token",token);
                myticket_intent.putExtra("point",t_point.getText().toString());
                startActivity(myticket_intent);
            }
        });

        i_myreservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myreservation_intent = new Intent(MyActivity.this, MyreservationActivity.class);
                myreservation_intent.putExtra("studentnum",studentnum);
                myreservation_intent.putExtra("name",name);
                myreservation_intent.putExtra("token",token);
                startActivity(myreservation_intent);
                finish();
            }
        });

        i_mybooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mybooth_intent = new Intent(MyActivity.this, MyBoothActivity.class);
                mybooth_intent.putExtra("studentnum",studentnum);
                mybooth_intent.putExtra("name",name);
                mybooth_intent.putExtra("token",token);
                startActivity(mybooth_intent);
                finish();
            }
        });

        bt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (studentnum.contains("201545805")&&name.contains("김동섭")){
                    Intent master_intent = new Intent(MyActivity.this, MasterchatlistActivity.class);
                    master_intent.putExtra("studentnum",studentnum);
                    master_intent.putExtra("name",name);
                    master_intent.putExtra("token",token);
                    startActivity(master_intent);
                }
                else{
                    Intent master_intent = new Intent(MyActivity.this, MychatActivity.class);
                    master_intent.putExtra("studentnum",studentnum);
                    master_intent.putExtra("name",name);
                    master_intent.putExtra("token",token);
                    master_intent.putExtra("masterstudentnum","null");
                    master_intent.putExtra("mastername","null");
                    startActivity(master_intent);
                }
            }
        });

        bt_master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference masterreference = FirebaseDatabase.getInstance().getReference().child("관리자");

                masterreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterator i = dataSnapshot.getChildren().iterator();
                        int a = 0;

                        while (i.hasNext()){
                            String item = ((DataSnapshot) i.next()).getKey();

                            if (item.contains(studentnum)){
                                a++;
                            }
                            else {
                            }
                        }
                        if (a==1){
                            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("관리자").child(studentnum);

                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Intent master_intent = new Intent(MyActivity.this, MasterActivity.class);
                                    master_intent.putExtra("studentnum",studentnum);
                                    master_intent.putExtra("name",name);
                                    master_intent.putExtra("token",token);
                                    master_intent.putExtra("master",dataSnapshot.getValue().toString());
                                    startActivity(master_intent);

                                    reference.removeEventListener(this);
                                    masterreference.removeEventListener(this);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                        else{
                            Toast.makeText(MyActivity.this, "* * * * * * *", Toast.LENGTH_SHORT).show();
                            masterreference.removeEventListener(this);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        bt_pointer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference masterreference = FirebaseDatabase.getInstance().getReference().child("관리자");

                masterreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterator i = dataSnapshot.getChildren().iterator();
                        int a = 0;

                        while (i.hasNext()){
                            String item = ((DataSnapshot) i.next()).getKey();

                            if (item.contains(studentnum)){
                                a++;
                            }
                            else {
                            }
                        }
                        if (a==1){
                            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("관리자").child(studentnum);

                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Intent master_intent = new Intent(MyActivity.this, PointerActivity.class);
                                    startActivity(master_intent);

                                    reference.removeEventListener(this);
                                    masterreference.removeEventListener(this);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                        else{
                            Toast.makeText(MyActivity.this, "$$$$$$", Toast.LENGTH_SHORT).show();
                            masterreference.removeEventListener(this);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        bt_fcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
