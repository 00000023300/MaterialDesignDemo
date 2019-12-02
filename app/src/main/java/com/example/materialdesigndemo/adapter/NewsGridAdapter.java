package com.example.materialdesigndemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.materialdesigndemo.R;
import com.example.materialdesigndemo.model.News;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;



public class NewsGridAdapter extends RecyclerView.Adapter<NewsGridAdapter.NewsViewHolder> {
    private List<News> newsList;
    private Context context;


    public NewsGridAdapter(List<News> newsList) {
        this.newsList = newsList;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_grid, parent, false);
        return new NewsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
//        final News news = newsList.get(position);
//        holder.itemView.setTag(position);
//        holder.tvTitle.setText(news.getTitle());
//        Glide.with(context).load(news.getPic()).into(holder.ivPic);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onItemClick(v, (Integer) v.getTag());
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
