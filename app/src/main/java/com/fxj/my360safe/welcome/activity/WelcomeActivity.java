package com.fxj.my360safe.welcome.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fxj.my360safe.MainActivity;
import com.fxj.my360safe.R;
import com.fxj.my360safe.welcome.adapter.MyViewPagerAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.LinkedList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    @ViewInject(R.id.vp_id)
    private ViewPager mVp;
    @ViewInject(R.id.ll_container_id)
    private LinearLayout mLlContainer;
    List<View> ds;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //思路
        //①界面控件实例的获取
        ViewUtils.inject(this);
        //②关于ViewPager的操作
        aboutViewPager();
        //③小圆点
        aboutDots();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * 关于ViewPager的操作
     */
    private void aboutViewPager() {
        //数据源

        ds = new LinkedList<>();
        fillDataSource(ds);
        //适配器
        PagerAdapter adapter = new MyViewPagerAdapter(ds, mVp);
        mVp.setAdapter(adapter);
        //监听器
        mVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                for (int i=0;i<mLlContainer.getChildCount();i++)
                {
                   View view=mLlContainer.getChildAt(i);
                    view.setEnabled(true);
                }
                mLlContainer.getChildAt(position).setEnabled(false);
            }
        });
    }

    /**
     * 填充数据源
     */
    private void fillDataSource(List ds) {
        int[] imagesIds = {R.mipmap.welcome1,R.mipmap.welcome2,R.mipmap.welcome3};
        for (int imageId : imagesIds) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(imageId);
            ds.add(iv);
        }
        View view = View.inflate(this, R.layout.last_welcome_page, null);
        CheckBox openAutoMode = (CheckBox) view.findViewById(R.id.cb_open_auto_mode);
        CheckBox provePlane = (CheckBox) view.findViewById(R.id.cb_prove_plane);
        CompoundButton.OnCheckedChangeListener listener = new MyCheckBoxListener();
        ImageView iv= (ImageView) view.findViewById(R.id.start);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
        openAutoMode.setOnCheckedChangeListener(listener);
        provePlane.setOnCheckedChangeListener(listener);
        ds.add(view);

    }

    private final class MyCheckBoxListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.cb_open_auto_mode:

                    break;
                case R.id.cb_prove_plane:

                    break;
            }
        }
    }

    /**
     * //小圆点
     */
    private void aboutDots() {
        MyDotClickListener listener=new MyDotClickListener();
        for (int i = 0; i < ds.size()-1; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.dot_selector);
            iv.setEnabled(true);
            iv.setTag(i);
            iv.setOnClickListener(listener);
            mLlContainer.addView(iv);
        }
        mLlContainer.getChildAt(0).setEnabled(false);
    }
    private  final class MyDotClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            mVp.setCurrentItem((Integer) view.getTag());
        }
    }
}
