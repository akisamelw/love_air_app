package com.example.akisame_lin.love_air2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akisame_lin.love_air2.bean1.ResultBean;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    private TextView city;
    private TextView AQI;
    private TextView quality;
    private TextView time;

    private Button send_request;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);



        city = (TextView)view.findViewById(R.id.city);
        AQI = (TextView)view.findViewById(R.id.AQI);
        quality = (TextView)view.findViewById(R.id.quality);
        time = (TextView)view.findViewById(R.id.time);
        send_request=(Button)view.findViewById(R.id.send_request);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     // final String inputText = editText.getText().toString();
        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId()==R.id.send_request)

                    sendRequestWithOkHttp();
            }
        });


    }

//使用okhttp获取数据
private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://web.juhe.cn:8080/environment/air/pm?city=linan&key=9cc4850858d059f6c20fcacf5c8ad3d8")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private void parseJSONWithGSON(String jsonData) {
        //GSON直接解析成对象
        ResultBean resultBeanlist = new Gson().fromJson(jsonData, ResultBean.class);
        List<ResultBean.dataBean> dataBeanslist = resultBeanlist.getResult();

        for (ResultBean.dataBean resultBean : dataBeanslist) {
            city.setText(resultBean.getCity());
            AQI.setText(resultBean.getAQI());
            quality.setText(resultBean.getQuality());
            time.setText(resultBean.getTime());


        }
    }
}
