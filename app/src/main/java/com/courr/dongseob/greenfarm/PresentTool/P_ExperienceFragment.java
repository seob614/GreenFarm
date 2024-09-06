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

import com.courr.dongseob.greenfarm.BoothExperience.CanActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DiffuserActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DrawingActivity;
import com.courr.dongseob.greenfarm.BoothExperience.EarringActivity;
import com.courr.dongseob.greenfarm.BoothExperience.EnergybarActivity;
import com.courr.dongseob.greenfarm.BoothExperience.HamburgerActivity;
import com.courr.dongseob.greenfarm.BoothExperience.MaskActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.GlobalfoodActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.RosemistActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.SandwichActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.TofuActivity;
import com.courr.dongseob.greenfarm.BoothExperience.DroneActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.PictureActivity;
import com.courr.dongseob.greenfarm.BoothExperience.SikhyeActivity;
import com.courr.dongseob.greenfarm.BoothExperience.SlimeActivity;
import com.courr.dongseob.greenfarm.BoothExperience.TopiatyActivity;
import com.courr.dongseob.greenfarm.BoothExperience.VRActivity;
import com.courr.dongseob.greenfarm.BoothExperience.FlowerActivity;
import com.courr.dongseob.greenfarm.BoothExperience.Non2019.SoapActivity;
import com.courr.dongseob.greenfarm.BoothExperience.TerrariumActivity;
import com.courr.dongseob.greenfarm.BoothExperience.YogurtActivity;
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
public class P_ExperienceFragment extends Fragment {


    public P_ExperienceFragment() {
        // Required empty public constructor
    }

    private String studentnum;
    private String name;
    private String token;

    private ListView p_experiencelist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View P_Experienceview = inflater.inflate(R.layout.fragment_p__experience, container, false);

        Intent intent = getActivity().getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        p_experiencelist = (ListView) P_Experienceview.findViewById(R.id.p_experiencelist);


        final ListViewAdapter p_experiencearrayAdapter = new ListViewAdapter();

        p_experiencelist.setAdapter(p_experiencearrayAdapter);


        p_experiencearrayAdapter.clear();

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.p_it)," VR체험", Color.BLACK);
        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.btdrone)," 드론체험" , Color.BLACK);

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.yogurt)," 쌀을 이용한 요거트 만들기 체험", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ricecake)," 우리 쌀과 세계 음식의 만남", Color.BLACK);

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.flowermain)," Flower & Fragrance", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.flowermain)," 미니꽃다발만들기", Color.BLACK);
        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.diffuser)," 디퓨저 만들기", Color.BLACK);
        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ff)," 리틀 토피어리", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.humidifier)," 스칸디아모스 가습기", Color.BLACK);

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.stick)," 취향대로 닭꼬치", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.sandwich)," 햄 in The 샌드위치", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.cheese)," 치즈만들기 체험", Color.BLACK);


        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.energybar)," New 에너지Baam", Color.BLACK);
        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.can)," 무엇이든지 통조림", Color.BLACK);

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.earing)," 귀생충(키링 및 귀걸이 만들기)", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.photographe)," 당신을 위해서라면", Color.BLACK);

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.mask)," 3D 마스크 만들기", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.soap)," Oatmeal Soap", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.candle)," Soy Candle", Color.BLACK);

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.slime)," 내 친구 슬라임",Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.tofu)," 두부를 먹는 당신은 몸짱",Color.BLACK);

        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.greencar)," 친환경 자원과 에너지", Color.BLACK);
        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.tumblr)," 누름꽃 텀블러 만들기", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.rosemist)," 로즈 미스트", Color.BLACK);
        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ecobag)," 친환경 가방만들기", Color.BLACK);

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.terrarium)," 테라리움", Color.BLACK);

        p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.sikhye)," 우리 쌀 소떡소떡 만들기", Color.BLACK);

        //p_experiencearrayAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.globalfood)," 세계의 맛과 멋 한마당", Color.BLACK);

        p_experiencelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                if (item.getTitle().contains("VR")){
                    Intent p_intent = new Intent(getActivity(), VRActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("드론")){
                    Intent p_intent = new Intent(getActivity(), DroneActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("쌀을 이용한 요거트 만들기 체험")){
                    Intent p_intent = new Intent(getActivity(), YogurtActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("Flower & Fragrance")){
                    Intent p_intent = new Intent(getActivity(), FlowerActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }


                if (item.getTitle().contains("디퓨저 만들기")){
                    Intent p_intent = new Intent(getActivity(), DiffuserActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("리틀 토피어리")){
                    Intent p_intent = new Intent(getActivity(), TopiatyActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }


                if (item.getTitle().contains("취향대로 닭꼬치")){
                    Intent p_intent = new Intent(getActivity(), HamburgerActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                /*
                if (item.getTitle().contains("치즈만들기 체험")){
                    Intent p_intent = new Intent(getActivity(), CheeseActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }
                */

                //if (item.getTitle().contains("에너지바,무엇이든지 통조림")){
                //    Intent p_intent = new Intent(getActivity(), JuiceActivity.class);
                //    p_intent.putExtra("studentnum",studentnum);
                //    p_intent.putExtra("name",name);
                //    p_intent.putExtra("token",token);
                //    startActivity(p_intent);
                //}

                if (item.getTitle().contains("New 에너지Baam")){
                    Intent p_intent = new Intent(getActivity(), EnergybarActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }


                if (item.getTitle().contains("무엇이든지 통조림")){
                    Intent p_intent = new Intent(getActivity(), CanActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }


                if (item.getTitle().contains("귀생충(키링 및 귀걸이 만들기)")){
                    Intent p_intent = new Intent(getActivity(), EarringActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("3D 마스크 만들기")){
                    Intent p_intent = new Intent(getActivity(), MaskActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                /*
                if (item.getTitle().contains("Soy Candle")){
                    Intent p_intent = new Intent(getActivity(), CandleActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }
                */

                if (item.getTitle().contains("내 친구 슬라임")){
                    Intent p_intent = new Intent(getActivity(), SlimeActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                //if (item.getTitle().contains("친환경 자원과 에너지")){
                //    Intent p_intent = new Intent(getActivity(), GreencarActivity.class);
                //    p_intent.putExtra("studentnum",studentnum);
                //    p_intent.putExtra("name",name);
                //    p_intent.putExtra("token",token);
                //    startActivity(p_intent);
                //}

                if (item.getTitle().contains("누름꽃 텀블러 만들기")){
                    Intent p_intent = new Intent(getActivity(), DrawingActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                /*
                if (item.getTitle().contains("친환경 가방만들기")){
                    Intent p_intent = new Intent(getActivity(), EcobagActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }
                */

                if (item.getTitle().contains("테라리움")){
                    Intent p_intent = new Intent(getActivity(), TerrariumActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }

                if (item.getTitle().contains("우리 쌀 소떡소떡 만들기")){
                    Intent p_intent = new Intent(getActivity(), SikhyeActivity.class);
                    p_intent.putExtra("studentnum",studentnum);
                    p_intent.putExtra("name",name);
                    p_intent.putExtra("token",token);
                    startActivity(p_intent);
                }
                /*

                if (item.getTitle().contains("세계의 맛과 멋 한마당")){
                    Intent p_intent = new Intent(getActivity(), GlobalfoodActivity.class);
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
                    p_experiencearrayAdapter.clearItem(((DataSnapshot) i.next()).getKey());
                }

                p_experiencearrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return P_Experienceview;
    }

}
