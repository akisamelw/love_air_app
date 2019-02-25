package com.example.akisame_lin.love_air2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.akisame_lin.love_air2.adapter.WeiboAdapter;
import com.example.akisame_lin.love_air2.bean1.WeiboInfomationReq;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Weibo_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private ImageView addBtn;
    private WeiboAdapter lostAndFoundAdapter;
    private long exitTime = 0;
    private final static int REQUEST_CODE = 999;
    private List<WeiboInfomationReq> lostInfomationReqList;
    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_weibo, container, false);
        recyclerView =(RecyclerView)view.findViewById(R.id.rl_recyclerview);
        addBtn = (ImageView)view.findViewById(R.id.iv_add);




        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }


}


