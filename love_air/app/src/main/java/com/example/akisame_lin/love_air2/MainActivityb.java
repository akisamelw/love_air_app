package com.example.akisame_lin.love_air2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;



import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.akisame_lin.love_air2.Bean.BaseActivity;
import com.example.akisame_lin.love_air2.R;

public class MainActivityb extends BaseActivity implements View.OnClickListener{

    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainb);
        back = (ImageView)this.findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            default:
        }
    }
}