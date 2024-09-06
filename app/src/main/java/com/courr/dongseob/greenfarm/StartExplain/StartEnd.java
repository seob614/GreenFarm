package com.courr.dongseob.greenfarm.StartExplain;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.courr.dongseob.greenfarm.LoginActivity;
import com.courr.dongseob.greenfarm.MainActivity;
import com.courr.dongseob.greenfarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartEnd extends Fragment {


    public StartEnd() {
        // Required empty public constructor
    }

    private Button bt_student;
    private Button bt_people;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View StartEndview = inflater.inflate(R.layout.fragment_start_end, container, false);

        bt_student = StartEndview.findViewById(R.id.bt_student);
        bt_people = StartEndview.findViewById(R.id.bt_people);


        bt_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        bt_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return StartEndview;
    }

}
