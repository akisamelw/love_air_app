package com.example.akisame_lin.love_air2.bean1;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/9/19.
 */

public class WeiboInfomationReq extends BmobObject {
    private String title;  //标题
    private String phoneNum;//手机号码
    private String desc;//描述

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
