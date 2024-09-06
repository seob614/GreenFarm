package com.courr.dongseob.greenfarm.WalK;

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
import com.courr.dongseob.greenfarm.CapstoneTool.Capstone1Activity;
import com.courr.dongseob.greenfarm.Class.ZxingActivity;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Iterator;

import uk.co.senab.photoview.PhotoView;

public class WalkActivity extends AppCompatActivity {

    private String token;


    //private Button bt_reserve;


    private Button bt_qring;
    private Button bt_qring2;

    //추가
    private TextView t_clear;
    private TextView t_clear2;
    //private TextView t_time;
    //private TextView t_nownum;


    private IntentIntegrator qr;

    private ArrayList<String> numarrayList = new ArrayList<>();

    private Toolbar toolbar;

    final Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
        Glide.with(this).load(R.drawable.map_walk).into(imageView1);

        PhotoView imageView2 = (PhotoView) findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.map_walk2).into(imageView2);

        //PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView2);
        //photoViewAttacher.setScaleType(ImageView.ScaleType.FIT_XY);

        Intent intent = getIntent();

        token = intent.getStringExtra("token");

        qr = new IntentIntegrator(this);


        //bt_reserve = (Button) findViewById(R.id.bt_reserve);

        bt_qring = (Button) findViewById(R.id.bt_qring);
        bt_qring2 = (Button) findViewById(R.id.bt_qring2);

        //수정
        t_clear = (TextView) findViewById(R.id.textView2);
        t_clear2 = (TextView) findViewById(R.id.textView5);

        //t_time = (TextView) findViewById(R.id.t_time);

        //t_nownum = (TextView) findViewById(R.id.t_nownum);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("둘레길");

        final DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("둘레길");

        t_PQRreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    String item = ((DataSnapshot) i.next()).getKey();
                    if (item.contains("입구")){
                        //수정
                        t_clear.setText("입구체험 완료    ");

                        //t_time.setText("체험 완료    ");
                        //t_nownum.setText("체험 완료    ");

                        bt_qring.setEnabled(false);

                        //bt_reserve.setEnabled(false);

                        //t_PQRreference.removeEventListener(this);
                    }
                    if (item.contains("출구")){
                        //수정
                        t_clear2.setText("출구체험 완료    ");

                        //t_time.setText("체험 완료    ");
                        //t_nownum.setText("체험 완료    ");

                        bt_qring2.setEnabled(false);

                        //bt_reserve.setEnabled(false);

                        //t_PQRreference.removeEventListener(this);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

                                                if (InputDialogEditText.getText().toString().contains("32400112")){
                                                    DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("둘레길").child("입구");

                                                    t_PQRreference.setValue("입구");

                                                }
                                                else{
                                                    Toast.makeText(WalkActivity.this, "번호를 정확히 입력하십시오.", Toast.LENGTH_LONG).show();
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

        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

        bt_qring2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder2.setTitle("QR코드")        // 제목 설정
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

                                                if (InputDialogEditText.getText().toString().contains("457565322224")){
                                                    DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("둘레길").child("출구");

                                                    t_PQRreference.setValue("출구");

                                                }
                                                else{
                                                    Toast.makeText(WalkActivity.this, "번호를 정확히 입력하십시오.", Toast.LENGTH_LONG).show();
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

                AlertDialog dialog = builder2.create();    // 알림창 객체 생성
                dialog.show();
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result!=null) {
            if (result.getContents() == null) {
                Toast.makeText(WalkActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {

                String QR = result.getContents();

                if (QR.contains("입구")){

                    Toast.makeText(WalkActivity.this, "스캔완료!", Toast.LENGTH_LONG).show();

                    DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("둘레길").child("입구");

                    t_PQRreference.setValue(QR);

                }
                else{
                    Toast.makeText(WalkActivity.this, "해당부스의 QR코드가 아닙니다!.", Toast.LENGTH_SHORT).show();
                }

                if (QR.contains("출구")){

                    Toast.makeText(WalkActivity.this, "스캔완료!", Toast.LENGTH_LONG).show();

                    DatabaseReference t_PQRreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("둘레길").child("출구");

                    t_PQRreference.setValue(QR);

                }
                else{
                    Toast.makeText(WalkActivity.this, "해당부스의 QR코드가 아닙니다!.", Toast.LENGTH_SHORT).show();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}

