package com.example.akisame_lin.love_air2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2018/9/20.
 */

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        //第一：默认初始化
        Bmob.initialize(this, "777caf1d199850c8b0d691c26ce0ba1d");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              /*  //获取缓存中的用户对象
                BmobUser currentUser = BmobUser.getCurrentUser();
                if (currentUser != null) {
                    //允许用户使用应用，进入程序
                    Intent intent = new Intent(WelcomeActivity.this, LostAndFoundActivity.class);
                    startActivity(intent);
                } else {
                    //进入登录界面
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }*/

                //进入登录界面
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);
    }
}
