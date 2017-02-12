package com.wangxw.fragmentlazyloaddemo;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangxw.fragmentlazyloaddemo.fragment.AFragment;
import com.wangxw.fragmentlazyloaddemo.fragment.BFragment;
import com.wangxw.fragmentlazyloaddemo.fragment.CFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout mllLogs;
    private static int sOffScreenLimit = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mllLogs = (LinearLayout) findViewById(R.id.ll_log_container);
    }

    private void initData() {
        //填充数据
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new AFragment());
        fragmentList.add(new BFragment());
        fragmentList.add(new CFragment());

        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            titleList.add("Page_"+i);
        }

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), fragmentList, titleList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        if(sOffScreenLimit >1){
            mViewPager.setOffscreenPageLimit(sOffScreenLimit);
            Log.i("wxw","偏移量>1...:"+ sOffScreenLimit);
        }else {
            Log.i("wxw","偏移量...:"+ sOffScreenLimit);
        }
    }

    private void initListener() {
        findViewById(R.id.btn_viewpager_mode_default).setOnClickListener(this);
        findViewById(R.id.btn_viewpager_mode_load2page).setOnClickListener(this);
        findViewById(R.id.btn_clear_log).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_viewpager_mode_default:       //默认模式
                sOffScreenLimit = 1;
                finish();
                startActivity(new Intent(this,MainActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.btn_viewpager_mode_load2page:     //预加载两页
                sOffScreenLimit = 2;
                finish();
                startActivity(new Intent(this,MainActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.btn_clear_log:                    //清空Log
                mllLogs.removeAllViews();
                break;
            default:
                break;
        }
    }

    public void addLog(TextView logView){
        mllLogs.addView(logView);
    }


}
