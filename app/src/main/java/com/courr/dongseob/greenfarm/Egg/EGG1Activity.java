package com.courr.dongseob.greenfarm.Egg;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.BoothExperience.CanActivity;
import com.courr.dongseob.greenfarm.Class.ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.ListViewItem;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EGG1Activity extends AppCompatActivity {

    private String studentnum;

    private String name;

    private String token;

    final Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg1);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
                View mView = layoutInflaterAndroid.inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                alertDialogBuilderUserInput.setView(mView);
                final EditText InputDialogEditText = (EditText) mView.findViewById(R.id.InputDialog);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                if (InputDialogEditText.getText().toString().contains("0614")){
                                    Glide.with(getApplication()).load(R.drawable.clearegg).into(imageView);
                                }
                                else{
                                    Toast.makeText(EGG1Activity.this, "번호를 정확히 입력하십시오.", Toast.LENGTH_LONG).show();
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
        });

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        ListView listview ;
        eggListViewAdapter adapter;

        // Adapter 생성
        adapter = new eggListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.profilelist);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kds),
                "대표: 김동섭", "- 총괄기획/ 영업\n" +
                        "- 앱(아이폰, 안드로이드)/웹 개발\n") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.lym),
                "전술가: 이영민", "- 앱(안드로이드) 개발\n" +
                        "- HCI 구성/ 디자인\n") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.lo),
                "전략가: 이원", "- 앱(안드로이드) 개발\n" +
                        "- 기획\n") ;

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.lsh),
                "개발자: 이승환", "- 웹 디자인/ HCI\n" +
                        "- 웹 개발\n") ;

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;
            }
        }) ;

    }
}
