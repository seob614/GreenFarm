package com.courr.dongseob.greenfarm.ClassActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Reserve1357Activity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;
    private String department;
    private String booth;

    //부스 수용 인원
    private String havenum;

    private TextView t_booth;

    private TextView t_nowreserve;

    private Button bt_reserve;
    private Button bt_cancell;

    private ListView l_reserve;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();

    private ArrayList<String> numarrayList = new ArrayList<>();
    //숫자 -1이 이전

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve1357);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");
        department = intent.getStringExtra("department");
        booth = intent.getStringExtra("booth");

        //부스 수용인원
        havenum = intent.getStringExtra("havenum");

        t_booth = (TextView) findViewById(R.id.t_booth);

        t_nowreserve = (TextView) findViewById(R.id.t_nowreserve);

        bt_reserve = (Button) findViewById(R.id.bt_reserve);
        bt_cancell = (Button) findViewById(R.id.bt_cancell);

        l_reserve = (ListView) findViewById(R.id.l_reserve);

        t_booth.setText(booth);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, arrayList);
        l_reserve.setAdapter(arrayAdapter);
        l_reserve.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        final DatabaseReference reservenumreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth);


        reservenumreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String A_num = dataSnapshot.child("1시30분~2시").child("인원").getValue().toString();
                String C_num = dataSnapshot.child("2시30분~3시").child("인원").getValue().toString();

                String E_num = dataSnapshot.child("3시30분~4시").child("인원").getValue().toString();

                String G_num = dataSnapshot.child("4시30분~5시").child("인원").getValue().toString();

                Set<String> set = new LinkedHashSet<>();

                set.add("오후 1시30분~2시"+"("+A_num+"/"+havenum+")");

                set.add("오후 2시30분~3시"+"("+C_num+"/"+havenum+")");

                set.add("오후 3시30분~4시"+"("+E_num+"/"+havenum+")");

                set.add("오후 4시30분~5시"+"("+G_num+"/"+havenum+")");

                arrayList.clear();
                arrayList.addAll(set);

                arrayAdapter.notifyDataSetChanged();

                final DatabaseReference t_reservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child(department).child(booth);

                t_reservereference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        t_nowreserve.setText("오후 "+dataSnapshot.getValue().toString());

                        for(Iterator<String> it = arrayList.iterator(); it.hasNext() ; )
                        {
                            String value = it.next();

                            if(dataSnapshot.getValue().toString().contains(value.substring(3,11)))
                            {

                                it.remove();
                            }
                        }
                        arrayAdapter.notifyDataSetChanged();

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

        l_reserve.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String item = (((TextView)view).getText().toString());

                bt_reserve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int end = item.indexOf("/");
                        final int start = item.indexOf("(");

                        if (item.isEmpty()){
                            Toast.makeText(Reserve1357Activity.this, "예약할 시간을 고르세요.", Toast.LENGTH_SHORT).show();
                        }
                        else{


                            if (Integer.parseInt(item.substring(start+1,end))>=Integer.parseInt(havenum)){
                                Toast.makeText(Reserve1357Activity.this, "예약인원이 가득 찼습니다.", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                //시간당 부스3개로 제한
                                final DatabaseReference limitreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(item.substring(3, 11)).child("개수");

                                limitreference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (Integer.parseInt(dataSnapshot.getValue().toString())>=3){
                                            Toast.makeText(Reserve1357Activity.this, item.substring(0,11)+"에 예약된 부스가 3개이상 입니다. 내 예약에서 예약을 관리해주세요.", Toast.LENGTH_SHORT).show();

                                            limitreference.removeEventListener(this);
                                        }
                                        else {
                                            //부스 3개 제한
                                            limitreference.removeEventListener(this);

                                            final DatabaseReference myreservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child(department).child(booth);

                                            myreservereference.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.getValue().toString().contains("예약대기중")) {

                                                        DatabaseReference studentnumreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth).child(item.substring(3, 11));

                                                        Map<String, Object> map = new HashMap<>();

                                                        map.put(studentnum, name);

                                                        studentnumreference.updateChildren(map);


                                                        DatabaseReference itemnumreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth).child(item.substring(3, 11)).child("인원");

                                                        itemnumreference.setValue(String.valueOf(Integer.parseInt(item.substring(start + 1, end)) + 1));

                                                        DatabaseReference reservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child(department).child(booth);
                                                        reservereference.setValue(item.substring(3, 11) + "(" + (Integer.parseInt(item.substring(start + 1, end)) + 1) + ")");

                                                        //예약시간데이터올리기
                                                        DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(item.substring(3, 11)).child(booth + "(" + (Integer.parseInt(item.substring(start + 1, end)) + 1) + ")");
                                                        reservetimereference.setValue(department);

                                                        final DatabaseReference reservetimenumreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(item.substring(3, 11)).child("개수");

                                                        reservetimenumreference.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                int timenum = Integer.parseInt(dataSnapshot.getValue().toString());

                                                                int nowtimenum = timenum + 1;

                                                                reservetimenumreference.setValue(nowtimenum);

                                                                reservetimenumreference.removeEventListener(this);
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });


                                                        myreservereference.removeEventListener(this);

                                                        arrayAdapter.notifyDataSetChanged();


                                                        finish();
                                                    } else {
                                                        final DatabaseReference reservenumreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth).child(dataSnapshot.getValue().toString().substring(0, 8));

                                                        DatabaseReference studentnum_cancellreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth).child(dataSnapshot.getValue().toString().substring(0, 8)).child(studentnum);

                                                        studentnum_cancellreference.removeValue();

                                                        reservenumreference.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                Iterator i = dataSnapshot.getChildren().iterator();

                                                                numarrayList.clear();

                                                                while (i.hasNext()) {
                                                                    numarrayList.add(((DataSnapshot) i.next()).getKey());
                                                                }
                                                                DatabaseReference change_numreference = reservenumreference.child("인원");

                                                                change_numreference.setValue(String.valueOf(numarrayList.size() - 2));
                                                                reservenumreference.removeEventListener(this);
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });


                                                        numarrayList.clear();

                                                        arrayAdapter.notifyDataSetChanged();

                                                        reservenumreference.removeEventListener(this);

                                                        DatabaseReference studentnumreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth).child(item.substring(3, 11));

                                                        Map<String, Object> map = new HashMap<>();

                                                        map.put(studentnum, name);

                                                        studentnumreference.updateChildren(map);


                                                        DatabaseReference itemnumreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth).child(item.substring(3, 11)).child("인원");

                                                        itemnumreference.setValue(String.valueOf(Integer.parseInt(item.substring(start + 1, end)) + 1));

                                                        DatabaseReference reservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child(department).child(booth);
                                                        reservereference.setValue(item.substring(3, 11) + "(" + (Integer.parseInt(item.substring(start + 1, end)) + 1) + ")");

                                                        //예약시간데이터올리기
                                                        String timeitem = dataSnapshot.getValue().toString();

                                                        int timestart = timeitem.indexOf("(");
                                                        int timeend = timeitem.indexOf(")");

                                                        String mynumitem = timeitem.substring(timestart, timeend+1);

                                                        DatabaseReference reservetime_cancellreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(dataSnapshot.getValue().toString().substring(0, 8)).child(booth+mynumitem);

                                                        reservetime_cancellreference.removeValue();

                                                        final DatabaseReference yetreservetimenumreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(dataSnapshot.getValue().toString().substring(0, 8)).child("개수");

                                                        yetreservetimenumreference.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                int timenum = Integer.parseInt(dataSnapshot.getValue().toString());

                                                                int nowtimenum = timenum - 1;

                                                                yetreservetimenumreference.setValue(nowtimenum);

                                                                yetreservetimenumreference.removeEventListener(this);
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });

                                                        DatabaseReference reservetimereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(item.substring(3, 11)).child(booth+ "(" + (Integer.parseInt(item.substring(start + 1, end)) + 1) + ")");
                                                        reservetimereference.setValue(department);

                                                        final DatabaseReference reservetimenumreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(item.substring(3, 11)).child("개수");

                                                        reservetimenumreference.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                int timenum = Integer.parseInt(dataSnapshot.getValue().toString());

                                                                int nowtimenum = timenum + 1;

                                                                reservetimenumreference.setValue(nowtimenum);

                                                                reservetimenumreference.removeEventListener(this);
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });


                                                        myreservereference.removeEventListener(this);

                                                        arrayAdapter.notifyDataSetChanged();


                                                        finish();
                                                    }
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


                    }
                });


            }
        });
        bt_cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final DatabaseReference cancell_reservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child(department).child(booth);


                cancell_reservereference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String myreservetime = dataSnapshot.getValue().toString();


                        if (myreservetime.contains("예약대기중")){

                            Toast.makeText(Reserve1357Activity.this, "예약된 시간이 없습니다.", Toast.LENGTH_SHORT).show();
                            cancell_reservereference.removeEventListener(this);

                        }
                        else{
                            final DatabaseReference reservenumreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth).child(myreservetime.substring(0,8));

                            DatabaseReference studentnum_cancellreference = FirebaseDatabase.getInstance().getReference().child("예약").child(booth).child(myreservetime.substring(0,8)).child(studentnum);

                            studentnum_cancellreference.removeValue();

                            reservenumreference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Iterator i = dataSnapshot.getChildren().iterator();

                                    numarrayList.clear();

                                    while (i.hasNext()){
                                        numarrayList.add(((DataSnapshot) i.next()).getKey());
                                    }
                                    DatabaseReference change_numreference = reservenumreference.child("인원");

                                    change_numreference.setValue(String.valueOf(numarrayList.size()-2));
                                    reservenumreference.removeEventListener(this);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                            numarrayList.clear();


                            DatabaseReference c_reservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child(department).child(booth);

                            c_reservereference.setValue("예약대기중");

                            arrayAdapter.notifyDataSetChanged();


                            //예약시간 데이터지우기
                            String timeitem = dataSnapshot.getValue().toString();

                            int timestart = timeitem.indexOf("(");
                            int timeend = timeitem.indexOf(")");

                            String mynumitem = timeitem.substring(timestart, timeend+1);


                            DatabaseReference reservetime_cancellreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(myreservetime.substring(0,8)).child(booth+mynumitem);

                            reservetime_cancellreference.removeValue();

                            final DatabaseReference yetreservetimenumreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약시간").child(myreservetime.substring(0,8)).child("개수");

                            yetreservetimenumreference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    int timenum = Integer.parseInt(dataSnapshot.getValue().toString());

                                    int nowtimenum = timenum-1;

                                    yetreservetimenumreference.setValue(nowtimenum);

                                    yetreservetimenumreference.removeEventListener(this);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                            cancell_reservereference.removeEventListener(this);
                            reservenumreference.removeEventListener(this);
                            yetreservetimenumreference.removeEventListener(this);

                            finish();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });






    }
}