package com.courr.dongseob.greenfarm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.courr.dongseob.greenfarm.R;
import com.courr.dongseob.greenfarm.UseExplain.UseExplainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText et_name;

    private EditText et_studentnum;

    private Button bt_logingo;

    private String s_studentnum;

    private String studentnum;

    private String s_name;

    private String name;

    private String s_token;




    DatabaseReference studentnumreference;

    DatabaseReference namereference;

    DatabaseReference L_qrreference;
    DatabaseReference P_qrreference;
    //캡스톤
    DatabaseReference C_qrreference;

    DatabaseReference W_qrreference;

    DatabaseReference REreference;

    DatabaseReference IT_DroneREreference;
    DatabaseReference IT_VrREreference;

    DatabaseReference Sikseng_Yogurtreference;
    //DatabaseReference Sikseng_Ricecakereference;

    DatabaseReference Oneyeah_Flowerreference;
    //DatabaseReference Oneyeah_Miniflowerreference;
    DatabaseReference Oneyeah_Diffuserreference;
    DatabaseReference Oneyeah_Topiary;
    //DatabaseReference Oneyeah_Humidifierreference;
    //DatabaseReference Oneyeah_Plantreference;

    DatabaseReference Dongmul_Hamburgerreference;
    //DatabaseReference Dongmul_Sandwichreference;
    //DatabaseReference Dongmul_Cheesereference;
    //DatabaseReference Dongmul_Doctorfishreference;

    //DatabaseReference Sikgong_Juicereference;
    DatabaseReference Sikgong_Energybarreference;
    DatabaseReference Sikgong_canreference;

    DatabaseReference Senghwan_Earringreference;
    //DatabaseReference Senghwan_Picturereference;

    DatabaseReference Baso_Maskreference;
    //DatabaseReference Baso_Soapreference;
    //DatabaseReference Baso_Candlereference;

    DatabaseReference Sangi_Slimereference;
    //DatabaseReference Sangi_Tofureference;

    //DatabaseReference Bahwan_Greencarreference;
    DatabaseReference Bahwan_Drawingreference;
    //DatabaseReference Bahwan_Rosemistreference;
    //DatabaseReference Bahwan_Ecobagreference;

    DatabaseReference Jogung_Terrariumreference;

    DatabaseReference Sikjagung_Sikhyereference;
    //DatabaseReference Sikjagung_Localfoodreference;

    DatabaseReference Globalfoodreference;

    DatabaseReference Farmreference;

    DatabaseReference COFFEE_Coffeebooth1reference;
    DatabaseReference COFFEE_Coffeebooth2reference;
    DatabaseReference COFFEE_Coffeebooth3reference;


    //예약시간데이터올리기
    DatabaseReference Reservetimereference;


    //1:30-2:00부터 atime
    DatabaseReference atimereference;
    DatabaseReference btimereference;
    DatabaseReference ctimereference;
    DatabaseReference dtimereference;
    DatabaseReference etimereference;
    DatabaseReference ftimereference;
    DatabaseReference gtimereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_studentnum = (EditText) findViewById(R.id.et_studentnum);

        et_name = (EditText) findViewById(R.id.et_name);

        bt_logingo = (Button) findViewById(R.id.bt_logingo);

        SharedPreferences auto = getSharedPreferences("autologin", Activity.MODE_PRIVATE);

        s_studentnum = auto.getString("studentnum", null);

        s_name = auto.getString("name", null);

        s_token = auto.getString("token",null);

        if (s_studentnum != null) {
            Intent go_intent = new Intent(LoginActivity.this, Menu.class);
            go_intent.putExtra("studentnum", s_studentnum);
            go_intent.putExtra("name",s_name);
            go_intent.putExtra("token",s_token);
            startActivity(go_intent);
            finish();
        }

        bt_logingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentnum = et_studentnum.getText().toString();
                name = et_name.getText().toString();

                String token = FirebaseInstanceId.getInstance().getToken();

                if (studentnum.length()==8 && name!= null) {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().getRoot();

                    DatabaseReference tokenreference = reference.child("토큰");

                    Map<String, Object> tokenmap = new HashMap<String, Object>();

                    tokenmap.put("",token);
                    tokenreference.updateChildren(tokenmap);

                    studentnumreference = tokenreference.child(token).child("학번");
                    namereference = tokenreference.child(token).child("이름");
                    L_qrreference = tokenreference.child(token).child("QR코드").child("연구");
                    P_qrreference = tokenreference.child(token).child("QR코드").child("선물");
                    //캡스톤
                    C_qrreference = tokenreference.child(token).child("QR코드").child("캡스톤");

                    W_qrreference = tokenreference.child(token).child("QR코드").child("둘레길");

                    studentnumreference.setValue(studentnum);
                    namereference.setValue(name);
                    L_qrreference.setValue("연구");
                    P_qrreference.setValue("선물");
                    C_qrreference.setValue("캡스톤");

                    W_qrreference.setValue("둘레길");

                    REreference = tokenreference.child(token).child("예약");

                    IT_DroneREreference = REreference.child("IT응용공학과").child("드론체험");
                    IT_VrREreference = REreference.child("IT응용공학과").child("VR체험");

                    Sikseng_Yogurtreference  = REreference.child("식물생명과학과").child("쌀을 이용한 요거트 만들기 체험");
                    //Sikseng_Ricecakereference  = REreference.child("식물생명과학과").child("우리 쌀과 세계 음식의 만남");

                    Oneyeah_Flowerreference = REreference.child("원예생명과학과").child("Flower & Fragrance");
                    //Oneyeah_Miniflowerreference = REreference.child("원예생명과학과").child("미니꽃다발만들기");
                    Oneyeah_Diffuserreference = REreference.child("원예생명과학과").child("디퓨저 만들기");
                    Oneyeah_Topiary = REreference.child("원예생명과학과").child("리틀 토피어리");
                    //Oneyeah_Humidifierreference = REreference.child("원예생명과학과").child("스칸디아모스 가습기");
                    //Oneyeah_Plantreference = REreference.child("원예생명과학과").child("식충식물 전시");

                    Dongmul_Hamburgerreference = REreference.child("동물생명자원과학과").child("취향대로 닭꼬치");
                    //Dongmul_Sandwichreference = REreference.child("동물생명자원과학과").child("햄 in The 샌드위치");
                    //Dongmul_Cheesereference = REreference.child("동물생명자원과학과").child("치즈만들기 체험");
                    //Dongmul_Doctorfishreference = REreference.child("동물생명자원과학과").child("동물자원과 공생");

                    //Sikgong_Juicereference = REreference.child("식품공학과").child("에너지바,무엇이든지 통조림");
                    Sikgong_Energybarreference = REreference.child("식품공학과").child("New 에너지Baam");
                    Sikgong_canreference = REreference.child("식품공학과").child("무엇이든지 통조림");

                        Senghwan_Earringreference = REreference.child("생명환경화학과").child("귀생충(키링 및 귀걸이 만들기)");
                    //Senghwan_Picturereference = REreference.child("생명환경화학과").child("당신을 위해서라면");

                    Baso_Maskreference = REreference.child("바이오소재과학과").child("3D 마스크 만들기");
                    //Baso_Soapreference = REreference.child("바이오소재과학과").child("Oatmeal Soap");
                    //Baso_Candlereference = REreference.child("바이오소재과학과").child("Soy Candle");

                    Sangi_Slimereference = REreference.child("바이오산업기계공학과").child("내 친구 슬라임");
                    //Sangi_Tofureference = REreference.child("바이오산업기계공학과").child("두부를 먹는 당신은 몸짱");

                    //Bahwan_Greencarreference = REreference.child("바이오환경에너지학과").child("친환경 자원과 에너지");
                    Bahwan_Drawingreference = REreference.child("바이오환경에너지학과").child("누름꽃 텀블러 만들기");
                    //Bahwan_Rosemistreference = REreference.child("바이오환경에너지학과").child("로즈 미스트");
                    //Bahwan_Ecobagreference = REreference.child("바이오환경에너지학과").child("친환경 가방만들기");

                    Jogung_Terrariumreference = REreference.child("조경학과").child("테라리움");

                    Sikjagung_Sikhyereference = REreference.child("식품자원경제학과").child("우리 쌀 소떡소떡 만들기");
                    //Sikjagung_Localfoodreference = REreference.child("식품자원경제학과").child("로컬푸드");


                    IT_DroneREreference.setValue("예약대기중");
                    IT_VrREreference.setValue("예약대기중");

                    Sikseng_Yogurtreference.setValue("예약대기중");

                    Oneyeah_Flowerreference.setValue("예약대기중");
                    Oneyeah_Diffuserreference.setValue("예약대기중");
                    Oneyeah_Topiary.setValue("예약대기중");
                    //Oneyeah_Humidifierreference.setValue("예약대기중");
                    //Oneyeah_Plantreference.setValue("예약대기중");

                    Dongmul_Hamburgerreference.setValue("예약대기중");
                    //Dongmul_Cheesereference.setValue("예약대기중");
                    //Dongmul_Doctorfishreference.setValue("예약대기중");

                    //Sikgong_Juicereference.setValue("예약대기중");
                    Sikgong_Energybarreference.setValue("예약대기중");
                    Sikgong_canreference.setValue("예약대기중");

                    Senghwan_Earringreference.setValue("예약대기중");

                    Baso_Maskreference.setValue("예약대기중");
                    //Baso_Candlereference.setValue("예약대기중");

                    Sangi_Slimereference.setValue("예약대기중");

                    //Bahwan_Greencarreference.setValue("예약대기중");
                    Bahwan_Drawingreference.setValue("예약대기중");
                    //Bahwan_Ecobagreference.setValue("예약대기중");

                    Jogung_Terrariumreference.setValue("예약대기중");

                    Sikjagung_Sikhyereference.setValue("예약대기중");



                    //예약시간 데이터올리기

                    Reservetimereference = tokenreference.child(token).child("예약시간");

                    atimereference = Reservetimereference.child("1시30분~2시").child("개수");
                    btimereference = Reservetimereference.child("2시~2시30분").child("개수");
                    ctimereference = Reservetimereference.child("2시30분~3시").child("개수");
                    dtimereference = Reservetimereference.child("3시~3시30분").child("개수");
                    etimereference = Reservetimereference.child("3시30분~4시").child("개수");
                    ftimereference = Reservetimereference.child("4시~4시30분").child("개수");
                    gtimereference = Reservetimereference.child("4시30분~5시").child("개수");

                    atimereference.setValue("0");
                    btimereference.setValue("0");
                    ctimereference.setValue("0");
                    dtimereference.setValue("0");
                    etimereference.setValue("0");
                    ftimereference.setValue("0");
                    gtimereference.setValue("0");

                    SharedPreferences auto = getSharedPreferences("autologin", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = auto.edit();
                    editor.putString("studentnum", studentnum);
                    editor.putString("name", name);
                    editor.putString("token",token);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, UseExplainActivity.class);
                    intent.putExtra("studentnum", studentnum);
                    intent.putExtra("name",name);
                    intent.putExtra("token",token);
                    startActivity(intent);

                    finish();

                }

                else {
                    Toast.makeText(LoginActivity.this, "정보를 제대로 입력해주십시오.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
