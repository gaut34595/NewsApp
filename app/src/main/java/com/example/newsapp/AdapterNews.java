package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {

    private ArrayList<NewsArticle> articleArrayList;
    private Context context;

    public AdapterNews(ArrayList<NewsArticle> articleArrayList, Context context) {
        this.articleArrayList = articleArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterNews.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news,parent,false);
        return new AdapterNews.ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNews.ViewHolder holder, int position) {
        NewsArticle articles = articleArrayList.get(position);
        Picasso.get().load(articles.getUrl()).into(holder.img);
    //    Picasso.get().setLoggingEnabled(true);
        holder.subtitle.setText(articles.getDescription());
        holder.title.setText(articles.getTitle());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                NewsFragment fragment = new NewsFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,fragment)
//                        .addToBackStack(null).commit();
//
//
//            }
//        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context,MainActivity2.class);
//                context.startActivity(i);
//
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsFrag newsFrag = new NewsFrag();
                Bundle bundle = new Bundle();
                bundle.putString("title", articles.getTitle());
                bundle.putString("content", articles.getContent());
                bundle.putString("desc", articles.getDescription());
                bundle.putString("image", articles.getUrlToImage());
                bundle.putString("url", articles.getUrl());
                newsFrag.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newsFrag).addToBackStack(null).commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,subtitle;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.row_tHeadNews);
            subtitle=itemView.findViewById(R.id.row_tNews);
            img=itemView.findViewById(R.id.row_iVNews);

        }
    }
}
