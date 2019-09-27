package com.example.kimberjin.kymusicplayer.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.FragmentAdapter;
import com.example.kimberjin.kymusicplayer.adapter.MyViewPagerAdapter;
import com.example.kimberjin.kymusicplayer.fragment.HistoryFragment;
import com.example.kimberjin.kymusicplayer.fragment.LocalFragment;
import com.example.kimberjin.kymusicplayer.fragment.OnlineFragment;

/**
 *  Created by ky4910 on 2019/09/27
 */

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter pagerAdapter;

    private String mTitles[] = {"本地音乐", "网络音乐", "播放历史"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        LocalFragment localFragment = new LocalFragment();
        OnlineFragment onlineFragment = new OnlineFragment();
        HistoryFragment historyFragment = new HistoryFragment();
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(localFragment);
        fragmentAdapter.addFragment(onlineFragment);
        fragmentAdapter.addFragment(historyFragment);

        pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
                fragmentAdapter.getFragmentList(), mTitles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}
