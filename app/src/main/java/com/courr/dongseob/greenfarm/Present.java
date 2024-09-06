package com.courr.dongseob.greenfarm;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.PresentTool.P_ExperienceFragment;
import com.courr.dongseob.greenfarm.PresentTool.P_DisplayFragment;
import com.courr.dongseob.greenfarm.R;

import java.util.ArrayList;
import java.util.List;

public class Present extends AppCompatActivity {



    private ViewPager viewpager;
    private ViewPagerAdapter pagerAdapter;

    private TabLayout tabs;

    private String studentnum;
    private String name;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);

        ImageView imageView = (ImageView)findViewById(R.id.imageView9);
        Glide.with(this).load(R.drawable.boothmain).into(imageView);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");


        Intent ExperienceFra_intent = new Intent(Present.this, P_ExperienceFragment.class);
        Intent DisplayFra_intent = new Intent(Present.this, P_DisplayFragment.class);

        ExperienceFra_intent.putExtra("studentnum", studentnum);
        ExperienceFra_intent.putExtra("name", name);
        ExperienceFra_intent.putExtra("token", token);

        DisplayFra_intent.putExtra("studentnum", studentnum);
        DisplayFra_intent.putExtra("name", name);
        DisplayFra_intent.putExtra("token", token);

        tabs = (TabLayout) findViewById(R.id.tabs);

        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager();

        tabs.setupWithViewPager(viewpager);

    }

    private void setupViewPager() {
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new P_ExperienceFragment(), "체험");
        pagerAdapter.addFragment(new P_DisplayFragment(), "전시");

        viewpager.setAdapter(pagerAdapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);

        }
    }
}
