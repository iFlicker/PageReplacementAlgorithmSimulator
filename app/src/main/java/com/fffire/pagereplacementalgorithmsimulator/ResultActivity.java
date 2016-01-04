package com.fffire.pagereplacementalgorithmsimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class ResultActivity extends AppCompatActivity {

    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private String ropt;
    private String rfifo;
    private String rlru;
    private String rclock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        /*
        //接受数据包
        Intent itr = getIntent();
        Bundle dbr = itr.getExtras();
        ropt = dbr.getString("opt");
        rfifo = dbr.getString("fifo");
        rlru = dbr.getString("lru");
        rclock = dbr.getString("clock");
        //封包发送给Fragment
        Bundle end = new Bundle();
        end.putString("eopt",ropt);
        end.putString("efifo",rfifo);
        end.putString("elru",rlru);
        end.putString("eclock", rclock);
        */


    }
}