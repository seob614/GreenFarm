package com.courr.dongseob.greenfarm.NonActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.CandleActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DiffuserActivity;
import com.courr.dongseob.greenfarm.BoothExperience.FlowerActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.HumidifierActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.RosemistActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.SoapActivity;
import com.courr.dongseob.greenfarm.BoothExperience.TerrariumActivity;
import com.courr.dongseob.greenfarm.ClassActivity.GetPresentActivity;
import com.courr.dongseob.greenfarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class P_MakingFragment extends Fragment {


    public P_MakingFragment() {
        // Required empty public constructor
    }

    private String studentnum;
    private String name;
    private String token;



    private Button bt_tp1;
    private Button bt_tp2;
    private Button bt_tp3;
    private Button bt_tp4;
    private Button bt_tp5;
    private Button bt_tp6;
    private Button bt_tp7;
    private Button bt_tp8;
    private Button bt_tp9;


    private Button bt_pget;
    private Button bt_pin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View P_Makingview = inflater.inflate(R.layout.fragment_p__making, container, false);

        ImageView imageView = (ImageView) P_Makingview.findViewById(R.id.bt_p1);
        Glide.with(this).load(R.drawable.flower).into(imageView);

        ImageView imageView2 = (ImageView) P_Makingview.findViewById(R.id.bt_p2);
        Glide.with(this).load(R.drawable.diffuser).into(imageView2);

        ImageView imageView3 = (ImageView) P_Makingview.findViewById(R.id.bt_p3);
        Glide.with(this).load(R.drawable.humidifier).into(imageView3);

        //ImageView imageView4 = (ImageView) P_Makingview.findViewById(R.id.bt_p4);
        //Glide.with(this).load(R.drawable.tofu).into(imageView4);

        ImageView imageView5 = (ImageView) P_Makingview.findViewById(R.id.bt_p5);
        Glide.with(this).load(R.drawable.greencar).into(imageView5);

        ImageView imageView6 = (ImageView) P_Makingview.findViewById(R.id.bt_p6);
        Glide.with(this).load(R.drawable.soap).into(imageView6);

        ImageView imageView7 = (ImageView) P_Makingview.findViewById(R.id.bt_p7);
        Glide.with(this).load(R.drawable.candle).into(imageView7);

        ImageView imageView8 = (ImageView) P_Makingview.findViewById(R.id.bt_p8);
        Glide.with(this).load(R.drawable.buddelschiff).into(imageView8);

        Intent intent = getActivity().getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        bt_pget = (Button) P_Makingview.findViewById(R.id.bt_pget);
        bt_pin = (Button) P_Makingview.findViewById(R.id.bt_pin);

        ImageView bt_p1 = (ImageView) P_Makingview.findViewById(R.id.bt_p1);
        ImageView bt_p2 = (ImageView) P_Makingview.findViewById(R.id.bt_p2);
        ImageView bt_p3 = (ImageView) P_Makingview.findViewById(R.id.bt_p3);
        ImageView bt_p4 = (ImageView) P_Makingview.findViewById(R.id.bt_p4);
        ImageView bt_p5 = (ImageView) P_Makingview.findViewById(R.id.bt_p5);
        ImageView bt_p6 = (ImageView) P_Makingview.findViewById(R.id.bt_p6);
        ImageView bt_p7 = (ImageView) P_Makingview.findViewById(R.id.bt_p7);
        ImageView bt_p8 = (ImageView) P_Makingview.findViewById(R.id.bt_p8);

        bt_p1.setClickable(true);
        bt_p2.setClickable(true);
        bt_p3.setClickable(true);
        bt_p4.setClickable(true);
        bt_p5.setClickable(true);
        bt_p6.setClickable(true);
        bt_p7.setClickable(true);
        bt_p8.setClickable(true);

        bt_tp1 = (Button) P_Makingview.findViewById(R.id.bt_tp1);
        bt_tp2 = (Button) P_Makingview.findViewById(R.id.bt_tp2);
        bt_tp3 = (Button) P_Makingview.findViewById(R.id.bt_tp3);
        bt_tp4 = (Button) P_Makingview.findViewById(R.id.bt_tp4);
        bt_tp5 = (Button) P_Makingview.findViewById(R.id.bt_tp5);
        bt_tp6 = (Button) P_Makingview.findViewById(R.id.bt_tp6);
        bt_tp7 = (Button) P_Makingview.findViewById(R.id.bt_tp7);
        bt_tp8 = (Button) P_Makingview.findViewById(R.id.bt_tp8);
        bt_tp9 = (Button) P_Makingview.findViewById(R.id.bt_tp9);

        bt_pget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //수정부분

                Intent get_intent = new Intent(getActivity(), GetPresentActivity.class);
                get_intent.putExtra("studentnum",studentnum);
                get_intent.putExtra("name",name);
                get_intent.putExtra("token",token);
                startActivity(get_intent);
            }
        });

        //꽃다발
        bt_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p1_intent = new Intent(getActivity(), FlowerActivity.class);
                p1_intent.putExtra("studentnum",studentnum);
                p1_intent.putExtra("name",name);
                p1_intent.putExtra("token",token);
                startActivity(p1_intent);
            }
        });

        //꽃다발
        bt_tp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp1_intent = new Intent(getActivity(), FlowerActivity.class);
                tp1_intent.putExtra("studentnum",studentnum);
                tp1_intent.putExtra("name",name);
                tp1_intent.putExtra("token",token);
                startActivity(tp1_intent);
            }
        });

        //디퓨저
        bt_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p2_intent = new Intent(getActivity(), DiffuserActivity.class);
                p2_intent.putExtra("studentnum",studentnum);
                p2_intent.putExtra("name",name);
                p2_intent.putExtra("token",token);
                startActivity(p2_intent);
            }
        });

        //디퓨저
        bt_tp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp2_intent = new Intent(getActivity(), DiffuserActivity.class);
                tp2_intent.putExtra("studentnum",studentnum);
                tp2_intent.putExtra("name",name);
                tp2_intent.putExtra("token",token);
                startActivity(tp2_intent);
            }
        });

        //가습기
        bt_p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p3_intent = new Intent(getActivity(), HumidifierActivity.class);
                p3_intent.putExtra("studentnum",studentnum);
                p3_intent.putExtra("name",name);
                p3_intent.putExtra("token",token);
                startActivity(p3_intent);
            }
        });

        //가습기
        bt_tp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp3_intent = new Intent(getActivity(), HumidifierActivity.class);
                tp3_intent.putExtra("studentnum",studentnum);
                tp3_intent.putExtra("name",name);
                tp3_intent.putExtra("token",token);
                startActivity(tp3_intent);
            }
        });

        //미스트
        bt_p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p4_intent = new Intent(getActivity(), RosemistActivity.class);
                p4_intent.putExtra("studentnum",studentnum);
                p4_intent.putExtra("name",name);
                p4_intent.putExtra("token",token);
                startActivity(p4_intent);
            }
        });

        //미스트
        bt_tp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp4_intent = new Intent(getActivity(), RosemistActivity.class);
                tp4_intent.putExtra("studentnum",studentnum);
                tp4_intent.putExtra("name",name);
                tp4_intent.putExtra("token",token);
                startActivity(tp4_intent);
            }
        });

        //자동차
        bt_p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p5_intent = new Intent(getActivity(), GreencarActivity.class);
                p5_intent.putExtra("studentnum",studentnum);
                p5_intent.putExtra("name",name);
                p5_intent.putExtra("token",token);
                startActivity(p5_intent);
            }
        });

        //자동차
        bt_tp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp5_intent = new Intent(getActivity(), GreencarActivity.class);
                tp5_intent.putExtra("studentnum",studentnum);
                tp5_intent.putExtra("name",name);
                tp5_intent.putExtra("token",token);
                startActivity(tp5_intent);
            }
        });

        //비누
        bt_p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p6_intent = new Intent(getActivity(), SoapActivity.class);
                p6_intent.putExtra("studentnum",studentnum);
                p6_intent.putExtra("name",name);
                p6_intent.putExtra("token",token);
                startActivity(p6_intent);
            }
        });

        //비누
        bt_tp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp6_intent = new Intent(getActivity(), SoapActivity.class);
                tp6_intent.putExtra("studentnum",studentnum);
                tp6_intent.putExtra("name",name);
                tp6_intent.putExtra("token",token);
                startActivity(tp6_intent);
            }
        });

        //향초
        bt_p7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p7_intent = new Intent(getActivity(), CandleActivity.class);
                p7_intent.putExtra("studentnum",studentnum);
                p7_intent.putExtra("name",name);
                p7_intent.putExtra("token",token);
                startActivity(p7_intent);
            }
        });

        //향초
        bt_tp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp7_intent = new Intent(getActivity(), CandleActivity.class);
                tp7_intent.putExtra("studentnum",studentnum);
                tp7_intent.putExtra("name",name);
                tp7_intent.putExtra("token",token);
                startActivity(tp7_intent);
            }
        });

        //테라리움
        bt_p8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p8_intent = new Intent(getActivity(), TerrariumActivity.class);
                p8_intent.putExtra("studentnum",studentnum);
                p8_intent.putExtra("name",name);
                p8_intent.putExtra("token",token);
                startActivity(p8_intent);
            }
        });

        //테라리움
        bt_tp8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp8_intent = new Intent(getActivity(), TerrariumActivity.class);
                tp8_intent.putExtra("studentnum",studentnum);
                tp8_intent.putExtra("name",name);
                tp8_intent.putExtra("token",token);
                startActivity(tp8_intent);
            }
        });

        return P_Makingview;
    }

}
