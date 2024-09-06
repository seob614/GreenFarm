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
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.CheeseActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.RicecakeActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.TofuActivity;
import com.courr.dongseob.greenfarm.ClassActivity.GetPresentActivity;
import com.courr.dongseob.greenfarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class P_EatingFragment extends Fragment {


    public P_EatingFragment() {
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
        View P_Eatingview = inflater.inflate(R.layout.fragment_p__eating, container, false);

        ImageView imageView = (ImageView) P_Eatingview.findViewById(R.id.bt_p1);
        Glide.with(this).load(R.drawable.ricecake).into(imageView);

        ImageView imageView2 = (ImageView) P_Eatingview.findViewById(R.id.bt_p2);
        Glide.with(this).load(R.drawable.cheese).into(imageView2);

        ImageView imageView3 = (ImageView) P_Eatingview.findViewById(R.id.bt_p3);
        Glide.with(this).load(R.drawable.juice).into(imageView3);

        ImageView imageView4 = (ImageView) P_Eatingview.findViewById(R.id.bt_p4);
        Glide.with(this).load(R.drawable.tofu).into(imageView4);

        Intent intent = getActivity().getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        bt_pget = (Button) P_Eatingview.findViewById(R.id.bt_pget);
        bt_pin = (Button) P_Eatingview.findViewById(R.id.bt_pin);

        ImageView bt_p1 = (ImageView) P_Eatingview.findViewById(R.id.bt_p1);
        ImageView bt_p2 = (ImageView) P_Eatingview.findViewById(R.id.bt_p2);
        ImageView bt_p3 = (ImageView) P_Eatingview.findViewById(R.id.bt_p3);
        ImageView bt_p4 = (ImageView) P_Eatingview.findViewById(R.id.bt_p4);

        bt_p1.setClickable(true);
        bt_p2.setClickable(true);
        bt_p3.setClickable(true);
        bt_p4.setClickable(true);

        bt_tp1 = (Button) P_Eatingview.findViewById(R.id.bt_tp1);
        bt_tp2 = (Button) P_Eatingview.findViewById(R.id.bt_tp2);
        bt_tp3 = (Button) P_Eatingview.findViewById(R.id.bt_tp3);
        bt_tp4 = (Button) P_Eatingview.findViewById(R.id.bt_tp4);

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

        //떡
        bt_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p1_intent = new Intent(getActivity(), RicecakeActivity.class);
                p1_intent.putExtra("studentnum",studentnum);
                p1_intent.putExtra("name",name);
                p1_intent.putExtra("token",token);
                startActivity(p1_intent);
            }
        });

        //떡
        bt_tp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp1_intent = new Intent(getActivity(), RicecakeActivity.class);
                tp1_intent.putExtra("studentnum",studentnum);
                tp1_intent.putExtra("name",name);
                tp1_intent.putExtra("token",token);
                startActivity(tp1_intent);
            }
        });

        //치즈
        bt_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p2_intent = new Intent(getActivity(), CheeseActivity.class);
                p2_intent.putExtra("studentnum",studentnum);
                p2_intent.putExtra("name",name);
                p2_intent.putExtra("token",token);
                startActivity(p2_intent);
            }
        });

        //치즈
        bt_tp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp2_intent = new Intent(getActivity(), CheeseActivity.class);
                tp2_intent.putExtra("studentnum",studentnum);
                tp2_intent.putExtra("name",name);
                tp2_intent.putExtra("token",token);
                startActivity(tp2_intent);
            }
        });

        //주스
        bt_p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p3_intent = new Intent(getActivity(), JuiceActivity.class);
                p3_intent.putExtra("studentnum",studentnum);
                p3_intent.putExtra("name",name);
                p3_intent.putExtra("token",token);
                startActivity(p3_intent);
            }
        });

        //주스
        bt_tp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp3_intent = new Intent(getActivity(), JuiceActivity.class);
                tp3_intent.putExtra("studentnum",studentnum);
                tp3_intent.putExtra("name",name);
                tp3_intent.putExtra("token",token);
                startActivity(tp3_intent);
            }
        });

        //두부
        bt_p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p4_intent = new Intent(getActivity(), TofuActivity.class);
                p4_intent.putExtra("studentnum",studentnum);
                p4_intent.putExtra("name",name);
                p4_intent.putExtra("token",token);
                startActivity(p4_intent);
            }
        });

        //두부
        bt_tp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp4_intent = new Intent(getActivity(), TofuActivity.class);
                tp4_intent.putExtra("studentnum",studentnum);
                tp4_intent.putExtra("name",name);
                tp4_intent.putExtra("token",token);
                startActivity(tp4_intent);
            }
        });

        return P_Eatingview;
    }

}
