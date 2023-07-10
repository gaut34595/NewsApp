package com.example.newsapp;

import java.util.ArrayList;

public class NewsModal {

    private int totalResults;
    private String status;
    private ArrayList<NewsArticle> articles;

    public NewsModal(int totalResults, String status, ArrayList<NewsArticle> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsArticle> articles) {
        this.articles = articles;
    }
}
