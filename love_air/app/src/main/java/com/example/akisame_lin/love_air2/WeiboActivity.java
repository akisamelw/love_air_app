package com.example.akisame_lin.love_air2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.akisame_lin.love_air2.adapter.WeiboAdapter;
import com.example.akisame_lin.love_air2.bean1.WeiboInfomationReq;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/9/19.
 */

public class WeiboActivity extends Activity implements View.OnClickListener, WeiboAdapter.ItemClickListener {
    private ImageView back;
    private RecyclerView recyclerView;
    private ImageView addBtn;
    private WeiboAdapter lostAndFoundAdapter;
    private long exitTime = 0;
    private final static int REQUEST_CODE = 999;
    private List<WeiboInfomationReq> lostInfomationReqList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weibo_main_activity);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.iv_back);
        recyclerView = (RecyclerView) findViewById(R.id.rl_recyclerview);
        addBtn = (ImageView) findViewById(R.id.iv_add);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lostAndFoundAdapter = new  WeiboAdapter( WeiboActivity.this);
        lostAndFoundAdapter.setLongClickListener(this);
    }

    private void initData() {
        BmobQuery< WeiboInfomationReq> lostInfomationReqBmobQuery = new BmobQuery<>();
        lostInfomationReqBmobQuery.order("-updatedAt");//排序
        lostInfomationReqBmobQuery.findObjects(new FindListener< WeiboInfomationReq>() {
            @Override
            public void done(List< WeiboInfomationReq> list, BmobException e) {
                if (e == null) {
                    lostInfomationReqList = list;
                    lostAndFoundAdapter.setData(list);
                    recyclerView.setAdapter(lostAndFoundAdapter);
                } else {
                    showToast("查询数据失败");
                }
            }
        });
    }

    private void initListener() {
        addBtn.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    /**
     * @param msg 打印信息
     */
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                Intent intent = new Intent( WeiboActivity.this, AddWeiboInformationActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    refreshData();//数据刷新
                }
                break;
            default:
                break;
        }
    }

    /**
     * 查询数据库中最新的数据
     * */
    private void refreshData() {
        BmobQuery< WeiboInfomationReq> lostInfomationReqBmobQuery = new BmobQuery<>();
        lostInfomationReqBmobQuery.order("-updatedAt");//按更新时间排序
        lostInfomationReqBmobQuery.findObjects(new FindListener< WeiboInfomationReq>() {
            @Override
            public void done(List< WeiboInfomationReq> list, BmobException e) {
                if (e == null) {
                    lostInfomationReqList = list;
                    lostAndFoundAdapter.setData(list);
                    lostAndFoundAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        return false;
    }

    @Override
    public void onEditOrDeleteClick(int position, int code) {

        if (code ==  WeiboAdapter.EDIT_CODE) {
            Intent intent = new Intent( WeiboActivity.this, AddWeiboInformationActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("editData", lostInfomationReqList.get(position));
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (code ==  WeiboAdapter.DELETE_CODE) {
            deleteItemData(position);
        }
    }

    private void deleteItemData(final int position) {
        if (lostInfomationReqList.size() != 0) {
            WeiboInfomationReq lostInfomationReq = new  WeiboInfomationReq();
            lostInfomationReq.setObjectId(lostInfomationReqList.get(position).getObjectId());
            lostInfomationReq.delete(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        lostInfomationReqList.remove(position);
                        lostAndFoundAdapter.setData(lostInfomationReqList);
                        lostAndFoundAdapter.notifyDataSetChanged();
                    } else {
                        showToast("删除数据失败");
                    }
                }
            });
        }
    }
}
