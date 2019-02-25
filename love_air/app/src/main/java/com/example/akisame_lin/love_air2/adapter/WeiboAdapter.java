package com.example.akisame_lin.love_air2.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.akisame_lin.love_air2.R;
import com.example.akisame_lin.love_air2.bean1.WeiboInfomationReq;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */

public class WeiboAdapter extends RecyclerView.Adapter<WeiboAdapter. WeiboHolder> {
    private Context mContext;
    private List<WeiboInfomationReq> weiboInfosData;
    private ItemClickListener mItemClickListener;
    public final static int EDIT_CODE = 998;
    public final static int DELETE_CODE = 997;





    public WeiboAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<WeiboInfomationReq> data) {
        this.weiboInfosData = data;
    }

    @Override
    public WeiboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weibo_item, parent, false);
        WeiboHolder weiboHolder = new  WeiboHolder(view);
        return weiboHolder;
    }

    @Override
    public void onBindViewHolder(final  WeiboHolder holder, final int position) {
        if (weiboInfosData != null) {
            WeiboInfomationReq weiboInfomationReq = weiboInfosData.get(position);
            holder.title.setText(weiboInfomationReq.getTitle());
            holder.desc.setText(weiboInfomationReq.getDesc());
            holder.phoneNum.setText(weiboInfomationReq.getPhoneNum());
            holder.time.setText(weiboInfomationReq.getCreatedAt());

            holder.llItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //mLongClickListener.onLongClick(position);
                    showWindow(holder, position);
                    return false;
                }
            });
        }
    }

    private void showWindow( WeiboHolder holder, final int pos) {
        //加载布局文件
        View contentview = LayoutInflater.from(mContext).inflate(R.layout.pop_window_view,null);
        final PopupWindow popupWindow = new PopupWindow(contentview, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置焦点
        popupWindow.setFocusable(true);
        //触摸框外
        popupWindow.setOutsideTouchable(true);
        //点击空白处的时候让PopupWindow消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置偏移量
        popupWindow.showAsDropDown(holder.time, 300, -100);

        //showAsDropDown(View anchor)：相对某个控件的位置（正左下方），无偏移
       // showAsDropDown(View anchor, int xoff, int yoff)：相对某个控件的位置，有偏移
        //showAtLocation(View parent, int gravity, int x, int y)：相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移

        //点击编辑按钮
        contentview.findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回调给主界面，进行数据操作
                mItemClickListener.onEditOrDeleteClick(pos, EDIT_CODE);
                //销毁弹出框
                popupWindow.dismiss();
            }
        });

        //点击删除按钮
        contentview.findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回调给主界面，进行数据操作
                mItemClickListener.onEditOrDeleteClick(pos, DELETE_CODE);
                //销毁弹出框
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return weiboInfosData.size() == 0 ? 0 : weiboInfosData.size();
    }

    public class  WeiboHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private TextView time;
        private TextView phoneNum;
        private LinearLayout llItem;

        public  WeiboHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            desc = (TextView) itemView.findViewById(R.id.tv_desc);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            phoneNum = (TextView) itemView.findViewById(R.id.tv_num);
            llItem = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    //设置长按事件
    public void setLongClickListener(ItemClickListener clickListener) {
        this.mItemClickListener = clickListener;
    }

    public interface ItemClickListener {
        void onEditOrDeleteClick(int position, int code);
    }
}
