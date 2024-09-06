package com.courr.dongseob.greenfarm.ClassActivity;

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
import com.courr.dongseob.greenfarm.R;
import com.courr.dongseob.greenfarm.UseExplain.UE1;
import com.courr.dongseob.greenfarm.UseExplain.UE2;
import com.courr.dongseob.greenfarm.UseExplain.UE3;
import com.courr.dongseob.greenfarm.UseExplain.UseEnd;
import com.courr.dongseob.greenfarm.UseExplain.UseExplainActivity;

import java.util.ArrayList;
import java.util.List;

public class ExplainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private ViewPagerAdapter pagerAdapter;

    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        tabs = (TabLayout) findViewById(R.id.tabs);

        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager();

        tabs.setupWithViewPager(viewpager);

    }
    private void setupViewPager() {
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new UE1(), "●");
        pagerAdapter.addFragment(new UE2(), "●");
        pagerAdapter.addFragment(new UE3(), "●");

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
