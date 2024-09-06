package com.courr.dongseob.greenfarm.NonActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.Class.ZxingActivity;
import com.courr.dongseob.greenfarm.ClassActivity.ReserveActivity;
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
import java.util.Iterator;
import java.util.Map;

public class GreencarActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;


    private Button bt_reserve;


    private Button bt_qring;

    //추가
    private TextView t_clear;
    private TextView t_time;
    private TextView t_nownum;


    private IntentIntegrator qr;

    private ArrayList<String> numarrayList = new ArrayList<>();

    private Toolbar toolbar;

    final Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greencar);


        ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
        Glide.with(this).load(R.drawable.greencar).into(imageView1);

        ImageView imageView2 = (ImageView)findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.vrtravel).into(imageView2);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        qr = new IntentIntegrator(this);


        bt_reserve = (Button) findViewById(R.id.bt_reserve);

        bt_qring = (Button) findViewById(R.id.bt_qring);

        //수정
        t_clear = (TextView) findViewById(R.id.textView2);

        t_time = (TextView) findViewById(R.id.t_time);

        t_nownum = (TextView) findViewById(R.id.t_nownum);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("친환경 자원과 에너지");


        final DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물");

        t_PQRreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    if (((DataSnapshot) i.next()).getKey().contains("친환경 자원과 에너지")){
                        //수정
                        t_clear.setText("체험 완료    ");

                        t_time.setText("체험 완료    ");
                        t_nownum.setText("체험 완료    ");

                        bt_qring.setEnabled(false);

                        bt_reserve.setEnabled(false);

                        //t_PQRreference.removeEventListener(this);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference reservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child("바이오환경에너지학과").child("친환경 자원과 에너지");

        reservereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String item = dataSnapshot.getValue().toString();

                //추가부분
                t_time.setText("오후 "+item+"    ");

                if (item.contains("시")) {

                    //추가부분
                    int start = item.indexOf("(");
                    int end = item.indexOf(")");
                    final String myorder = item.substring(start+1,end);

                    DatabaseReference nownumreference = FirebaseDatabase.getInstance().getReference().child("예약").child("친환경 자원과 에너지").child(item.substring(0, 8)).child("대기");

                    nownumreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Iterator i = dataSnapshot.getChildren().iterator();

                            numarrayList.clear();

                            while (i.hasNext()) {
                                numarrayList.add(((DataSnapshot) i.next()).getKey());
                            }

                            //추가부분
                            t_time.setText("오후 "+item.substring(0, 8)+"    ");
                            t_nownum.setText("현재:"+numarrayList.size()+" 나의순서:"+myorder+"    ");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    //추가부분
                    t_nownum.setText("예약대기중    ");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        bt_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reserve_intent = new Intent(GreencarActivity.this , ReserveActivity.class);
                reserve_intent.putExtra("studentnum", studentnum);
                reserve_intent.putExtra("name", name);
                reserve_intent.putExtra("token", token);
                reserve_intent.putExtra("department", "바이오환경에너지학과");
                reserve_intent.putExtra("booth", "친환경 자원과 에너지");
                startActivity(reserve_intent);

            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        bt_qring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setTitle("QR코드")        // 제목 설정
                        .setMessage("QR코드입력 방식을 선택하세요. ")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setNeutralButton("QR번호", new DialogInterface.OnClickListener(){
                            // 확인 버튼 클릭시 설정
                            public void onClick(final DialogInterface dialog, int whichButton) {


                                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
                                View mView = layoutInflaterAndroid.inflate(R.layout.custom_dialog, null);
                                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                                alertDialogBuilderUserInput.setView(mView);
                                final EditText InputDialogEditText = (EditText) mView.findViewById(R.id.InputDialog);
                                alertDialogBuilderUserInput
                                        .setCancelable(false)
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {
                                                dialog.cancel();

                                                if (InputDialogEditText.getText().toString().contains("212333356")){
                                                    DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물").child("친환경 자원과 에너지");

                                                    t_PQRreference.setValue("친환경 자원과 에너지");

                                                    final DatabaseReference myreservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child("바이오환경에너지학과").child("친환경 자원과 에너지");

                                                    myreservereference.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            if (dataSnapshot.getValue().toString().contains("예약대기중")){
                                                                myreservereference.removeEventListener(this);
                                                            }
                                                            else{
                                                                DatabaseReference numPQRreference = FirebaseDatabase.getInstance().getReference().child("예약").child("친환경 자원과 에너지").child(dataSnapshot.getValue().toString().substring(0,8)).child("대기");

                                                                Map<String,Object> map = new HashMap<>();

                                                                map.put(studentnum,name);

                                                                numPQRreference.updateChildren(map);

                                                                myreservereference.removeEventListener(this);
                                                            }

                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });
                                                }
                                                else{
                                                    Toast.makeText(GreencarActivity.this, "번호를 정확히 입력하십시오.", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        })
                                        .setNegativeButton("취소",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogBox, int id) {
                                                        dialogBox.cancel();
                                                    }
                                                });

                                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                                alertDialogAndroid.show();
                            }
                        })
                        .setNegativeButton("QR이미지", new DialogInterface.OnClickListener(){
                            // 확인 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.cancel();
                                qr.setCaptureActivity(ZxingActivity.class);
                                qr.setOrientationLocked(false);
                                qr.initiateScan();
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
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result!=null) {
            if (result.getContents() == null) {
                Toast.makeText(GreencarActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {

                String QR = result.getContents();

                if (QR.contains("친환경 자원과 에너지")){

                    Toast.makeText(GreencarActivity.this, "스캔완료!", Toast.LENGTH_LONG).show();

                    DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물").child("친환경 자원과 에너지");

                    t_PQRreference.setValue(QR);

                    final DatabaseReference myreservereference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("예약").child("바이오환경에너지학과").child("친환경 자원과 에너지");

                    myreservereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getValue().toString().contains("예약대기중")){
                                myreservereference.removeEventListener(this);
                            }

                            else{
                                DatabaseReference numPQRreference = FirebaseDatabase.getInstance().getReference().child("예약").child("친환경 자원과 에너지").child(dataSnapshot.getValue().toString().substring(0,8)).child("대기");

                                Map<String,Object> map = new HashMap<>();

                                map.put(studentnum,name);

                                numPQRreference.updateChildren(map);

                                myreservereference.removeEventListener(this);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else{
                    Toast.makeText(GreencarActivity.this, "해당부스의 QR코드가 아닙니다!.", Toast.LENGTH_SHORT).show();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
