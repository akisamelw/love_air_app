package com.example.akisame_lin.love_air2.presenter;

import com.example.akisame_lin.love_air2.Bean.SignView;

/**
 * 签到日历控件数据适配器
 * Created by E.M on 2016/4/20.
 */
public abstract class CalendarAdapter {
    public abstract SignView.DayType getType(int dayOfMonth);
}
