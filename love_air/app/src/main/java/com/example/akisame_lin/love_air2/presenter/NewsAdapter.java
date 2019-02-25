package com.example.akisame_lin.love_air2.presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akisame_lin.love_air2.Bean.News;
import com.example.akisame_lin.love_air2.R;
import com.example.akisame_lin.love_air2.NewsDisplayActivity;
import com.example.akisame_lin.love_air2.Bean.News;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> newsList;

    public NewsAdapter() {
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View newsView;
        TextView newsTitle;
        TextView newsDesc;
        TextView newsTime;

        public ViewHolder(View view) {
            super(view);
            newsView = view;
            newsTitle = (TextView) view.findViewById(R.id.news_title);
            newsDesc = (TextView) view.findViewById(R.id.news_desc);
            newsTime = (TextView) view.findViewById(R.id.news_time);
        }
    }

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                News news = newsList.get(position);
                Intent intent = new Intent(parent.getContext(), NewsDisplayActivity.class);
                intent.putExtra("news_url",news.getNewsUrl());
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.newsTitle.setText(news.getNewsTitle());
        holder.newsDesc.setText(news.getDesc());
        holder.newsTime.setText("发布时间： "+news.getNewsTime());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
