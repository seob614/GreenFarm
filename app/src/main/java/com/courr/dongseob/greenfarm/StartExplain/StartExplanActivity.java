package com.courr.dongseob.greenfarm.StartExplain;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.courr.dongseob.greenfarm.MainActivity;
import com.courr.dongseob.greenfarm.Menu;
import com.courr.dongseob.greenfarm.Present;
import com.courr.dongseob.greenfarm.PresentTool.P_DisplayFragment;
import com.courr.dongseob.greenfarm.PresentTool.P_ExperienceFragment;
import com.courr.dongseob.greenfarm.R;

import java.util.ArrayList;
import java.util.List;

public class StartExplanActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private ViewPagerAdapter pagerAdapter;

    private TabLayout tabs;

    private String s_studentnum;

    private String s_name;

    private String s_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_explan);


        SharedPreferences auto = getSharedPreferences("autologin", Activity.MODE_PRIVATE);


        s_studentnum = auto.getString("studentnum", null);

        s_name = auto.getString("name", null);

        s_token = auto.getString("token",null);

        if (s_studentnum != null) {
            Intent go_intent = new Intent(StartExplanActivity.this, Menu.class);
            go_intent.putExtra("studentnum", s_studentnum);
            go_intent.putExtra("name",s_name);
            go_intent.putExtra("token",s_token);
            startActivity(go_intent);
            finish();
        }


        tabs = (TabLayout) findViewById(R.id.tabs);

        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager();

        tabs.setupWithViewPager(viewpager);

    }

    private void setupViewPager() {
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new SE1(), "●");
        pagerAdapter.addFragment(new SE2(), "●");
        pagerAdapter.addFragment(new SE3(), "●");
        pagerAdapter.addFragment(new SE4(), "●");
        pagerAdapter.addFragment(new StartEnd(), "로그인");

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
