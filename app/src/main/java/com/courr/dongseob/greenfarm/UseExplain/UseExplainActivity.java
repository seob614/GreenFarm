package com.courr.dongseob.greenfarm.UseExplain;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.courr.dongseob.greenfarm.Menu;
import com.courr.dongseob.greenfarm.R;
import com.courr.dongseob.greenfarm.StartExplain.SE1;
import com.courr.dongseob.greenfarm.StartExplain.SE2;
import com.courr.dongseob.greenfarm.StartExplain.SE3;
import com.courr.dongseob.greenfarm.StartExplain.SE4;
import com.courr.dongseob.greenfarm.StartExplain.StartEnd;
import com.courr.dongseob.greenfarm.StartExplain.StartExplanActivity;

import java.util.ArrayList;
import java.util.List;

public class UseExplainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private ViewPagerAdapter pagerAdapter;

    private TabLayout tabs;

    private String studentnum;

    private String name;

    private String token;

    private String s_studentnum;

    private String s_name;

    private String s_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_explain);

        SharedPreferences auto = getSharedPreferences("autologin", Activity.MODE_PRIVATE);


        s_studentnum = auto.getString("studentnum", null);

        s_name = auto.getString("name", null);

        s_token = auto.getString("token",null);

        if (s_studentnum != null) {
            Intent go_intent = new Intent(UseExplainActivity.this, Menu.class);
            go_intent.putExtra("studentnum", s_studentnum);
            go_intent.putExtra("name",s_name);
            go_intent.putExtra("token",s_token);
            startActivity(go_intent);
            finish();
        }

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");
        name = intent.getStringExtra("name");
        token = intent.getStringExtra("token");

        tabs = (TabLayout) findViewById(R.id.tabs);

        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager();

        tabs.setupWithViewPager(viewpager);

        Intent useendintent = new Intent(UseExplainActivity.this, UseEnd.class);

        useendintent.putExtra("studentnum",studentnum);
        useendintent.putExtra("name",name);
        useendintent.putExtra("token",token);

    }
    private void setupViewPager() {
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new UE1(), "●");
        pagerAdapter.addFragment(new UE2(), "●");
        pagerAdapter.addFragment(new UE3(), "●");
        pagerAdapter.addFragment(new UseEnd(), "시작하기");

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
