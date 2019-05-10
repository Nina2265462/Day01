package com.baway.monthtest;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baway.monthtest.base.BaseActivity;
import com.baway.monthtest.tab.Tab01;
import com.baway.monthtest.tab.Tab02;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class MainActivity extends BaseActivity {
    private TabLayout tab;
    private ViewPager pager;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
        //找控件
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
    }

    @Override
    public void bindData() {
        //设置数据
        final ArrayList<Fragment> flist = new ArrayList<>();
        final ArrayList<String> tlist = new ArrayList<>();
        tlist.add("首页");
        tlist.add("我的");
        tab.addTab(tab.newTab().setText(tlist.get(0)));
        tab.addTab(tab.newTab().setText(tlist.get(1)));
        flist.add(new Tab01());
        flist.add(new Tab02());
        //设置适配器
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return flist.get(i);
            }

            @Override
            public int getCount() {
                return flist.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tlist.get(position);
            }
        });
        tab.setupWithViewPager(pager);
    }

    @Override
    public void bindEvent() {

    }
}
