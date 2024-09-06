package com.courr.dongseob.greenfarm.UseExplain;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.courr.dongseob.greenfarm.Menu;
import com.courr.dongseob.greenfarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UseEnd extends Fragment {


    public UseEnd() {
        // Required empty public constructor
    }

    private Button bt_start;

    private String studentnum;

    private String name;

    private String token;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View UEndview = inflater.inflate(R.layout.fragment_use_end, container, false);

        Intent intent = getActivity().getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        bt_start = (Button) UEndview.findViewById(R.id.bt_start);

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_intent = new Intent(getContext(), Menu.class);
                go_intent.putExtra("studentnum", studentnum);
                go_intent.putExtra("name",name);
                go_intent.putExtra("token",token);
                startActivity(go_intent);
                getActivity().finish();
            }
        });


        return UEndview;
    }

}
