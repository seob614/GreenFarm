package com.courr.dongseob.greenfarm.PresentTool;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.courr.dongseob.greenfarm.BoothExperience.SikhyeActivity;
import com.courr.dongseob.greenfarm.Class.ListViewAdapter;
import com.courr.dongseob.greenfarm.Class.ListViewItem;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class P_DisplayFragment extends Fragment {


    public P_DisplayFragment() {
        // Required empty public constructor
    }

    private String studentnum;
    private String name;
    private String token;

    private ListView p_displaylist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View P_Displayview = inflater.inflate(R.layout.fragment_p__display, container, false);

        Intent intent = getActivity().getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        p_displaylist = (ListView) P_Displayview.findViewById(R.id.p_displaylist);

        final ListViewAdapter p_displayarrayAdapter = new ListViewAdapter();

        p_displaylist.setAdapter(p_displayarrayAdapter);

        p_displayarrayAdapter.clear();

        //p_displayarrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.market)," 우리 쌀 소떡소떡 만들기", Color.BLACK);
        //p_displayarrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.farmmain)," 부속농장", Color.BLACK);

        //p_displayarrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.bugflower)," 식충식물 전시", Color.BLACK);

        p_displaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;
/*
                if (item.getTitle().contains("우리 쌀 소떡소떡 만들기")){
                    Intent p_intent = new Intent(getActivity(), SikhyeActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("부속농장")){
                    Intent p_intent = new Intent(getActivity(), FarmActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("식충식물 전시")){
                    Intent p_intent = new Intent(getActivity(), PlantActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }
*/
            }
        }) ;

        final DatabaseReference qrreference = FirebaseDatabase.getInstance().getReference().child("토큰").child(token).child("QR코드").child("선물");

        qrreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    p_displayarrayAdapter.clearItem(((DataSnapshot) i.next()).getKey());
                }

                p_displayarrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return P_Displayview;
    }

}
