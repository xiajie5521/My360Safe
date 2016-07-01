package com.fxj.my360safe.welcome.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> ds;
    private ViewPager mVp;

    public MyViewPagerAdapter(List<View> ds,ViewPager mVp) {
        this.ds = ds;
        this.mVp=mVp;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View iv=ds.get(position);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(ds.get(position));
    }
}
