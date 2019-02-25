package com.example.akisame_lin.love_air2.Bean;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by WXY on 2017/11/7.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.finishActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        if(isTaskRoot()){
            //DataSupport.deleteAll(WaterQualityDataBean.class);
        }
    }
}
