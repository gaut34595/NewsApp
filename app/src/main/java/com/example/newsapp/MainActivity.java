 package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 public class MainActivity extends AppCompatActivity{
     ActivityMainBinding binding;
     private ArrayList<NewsArticle> articleArrayList;
     private AdapterNews adapterNews;
     private ArrayList<NewsArticle> articles;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding= ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());
         articleArrayList = new ArrayList<>();
         getNews();
         adapterNews = new AdapterNews(articleArrayList,this);
         binding.recyclerNews.setLayoutManager(new LinearLayoutManager(this));
         binding.recyclerNews.setAdapter(adapterNews);


    }
    public void getNews(){
         binding.pbar.setVisibility(View.VISIBLE);
         //String url = "https://newsapi.org/v2/everything?q=tesla&from=2023-03-15&sortBy=publishedAt&apiKey=6d33e527d5704e8b81a7e1b87379b00e";
         String url2 = "https://newsapi.org/v2/top-headlines?country=us&apiKey=6d33e527d5704e8b81a7e1b87379b00e";

         String base_url = "https://newsapi.org/";
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<NewsModal> calling;
        calling = retrofitApi.getAllNews(url2);
        
        calling.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal= response.body();
                binding.pbar.setVisibility(View.GONE);
                articles = newsModal.getArticles();
                for(int i =0;i<articles.size();i++){
                    articleArrayList.add(new NewsArticle(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage()
                    ,articles.get(i).getUrl(),articles.get(i).getContent()));
                }
                adapterNews.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
        
        

    }
}