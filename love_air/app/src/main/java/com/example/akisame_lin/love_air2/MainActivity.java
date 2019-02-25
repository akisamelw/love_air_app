package com.example.akisame_lin.love_air2;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout drawerLayout;
    ImageView home_btn;
    ImageView map_btn;
    ImageView news_btn;
    ImageView weibo_btn;
    FragmentManager fragmentManager;
    NavigationView navigationView; //侧拉中的导航
    View nav_header_main;
    int navisclick = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        initView();
        initListener();
        replaceFragment(new Home_Fragment());


        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //设置侧拉导航栏
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_air);//默认选中
        nav_header_main =navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){


                    case R.id.nav_city:
                        Intent intent2 = new Intent(MainActivity.this,WeiboActivity.class);
                        startActivity(intent2);
                        break;


                    case R.id.nav_map:
                        Intent intent3 = new Intent(MainActivity.this, com.example.akisame_lin.love_air2.weather.MainActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_about:
                        Intent intent4 = new Intent(MainActivity.this, com.example.akisame_lin.love_air2.MainActivityb.class);
                        startActivity(intent4);
                        break;
                    default:
                }
                return true;
            }
        });




        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu1);
        }



    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
//分享按钮
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.share:
                Toast.makeText(this,"you click Backup",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    private  void initView(){
        home_btn = (ImageView)findViewById(R.id.home_button);
        map_btn = (ImageView)findViewById(R.id.map_button);
        news_btn = (ImageView)findViewById(R.id.news_button);
        weibo_btn = (ImageView)findViewById(R.id.weibo_button);

    }

    private void initListener(){
        home_btn.setOnClickListener(this);
        map_btn.setOnClickListener(this);
        news_btn.setOnClickListener(this);
        weibo_btn.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
//底部导航栏（当点中某图标时变为蓝色 其余为灰色）

            case R.id.home_button:
                if(navisclick!=0){
                    home_btn.setColorFilter(Color.rgb(10,137,255));
                    map_btn.setColorFilter(Color.GRAY);
                    news_btn.setColorFilter(Color.GRAY);
                    weibo_btn.setColorFilter(Color.GRAY);
                    replaceFragment(new Home_Fragment());
                    navisclick=0;
                }
                break;
            case R.id.map_button:
                if(navisclick!=1){
                    map_btn.setColorFilter(Color.rgb(10,137,255));
                    home_btn.setColorFilter(Color.GRAY);
                    news_btn.setColorFilter(Color.GRAY);
                    weibo_btn.setColorFilter(Color.GRAY);
                    replaceFragment(new Map_Fragment());

                    navisclick=1;
                }
                break;
            case R.id.news_button:
                if(navisclick!=2){
                    news_btn.setColorFilter(Color.rgb(10,137,255));
                    home_btn.setColorFilter(Color.GRAY);
                    map_btn.setColorFilter(Color.GRAY);
                    weibo_btn.setColorFilter(Color.GRAY);

                    Intent intent2 = new Intent(MainActivity.this,NewsActivity.class);
                    startActivity(intent2);
                    //replaceFragment(new News_Fragment());
                    navisclick=2;
                }
                break;

            case R.id.weibo_button:
                if(navisclick!=3){
                   weibo_btn.setColorFilter(Color.rgb(10,137,255));
                    home_btn.setColorFilter(Color.GRAY);
                    map_btn.setColorFilter(Color.GRAY);
                    news_btn.setColorFilter(Color.GRAY);

                    Intent intent = new Intent(MainActivity.this,WeiboActivity.class);
                    startActivity(intent);
                    //replaceFragment(new Weibo_Fragment());
                    navisclick=3;
                }
                break;

            default:

        }
    }
//构造切换fragment函数
    private void replaceFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Top_fragment,fragment);
        transaction.commit();
    }
}

