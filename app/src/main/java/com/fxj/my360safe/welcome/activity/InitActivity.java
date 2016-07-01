package com.fxj.my360safe.welcome.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.fxj.my360safe.MainActivity;
import com.fxj.my360safe.R;

public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences config=getSharedPreferences("config",MODE_PRIVATE);
        if (config.getBoolean("isFirst",true))
        {
            setContentView(R.layout.activity_init);
            config.edit().putBoolean("isFirst",false).commit();
            new Thread(){
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    InitActivity.this.startActivity(new Intent(InitActivity.this,WelcomeActivity.class));
                     InitActivity.this.finish();
                }
            };
        }
        else {
            startActivity(new Intent(InitActivity.this,MainActivity.class));
            this.finish();
        }

        //开启子线程，休眠几秒


    }
}
