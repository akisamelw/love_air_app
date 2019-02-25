package com.example.akisame_lin.love_air2.presenter;

import com.example.akisame_lin.love_air2.Bean.SignView;

import java.util.List;


public class SignAdapter extends CalendarAdapter {
    private List<SignEntity> data;

    public SignAdapter(List<SignEntity> data) {
        this.data = data;
    }


    @Override
    public SignView.DayType getType(int dayOfMonth) {
        return SignView.DayType.valueOf(data.get(dayOfMonth - 1).getDayType());
    }
}
