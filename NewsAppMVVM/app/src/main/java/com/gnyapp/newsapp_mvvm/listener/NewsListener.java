package com.gnyapp.newsapp_mvvm.listener;

import com.gnyapp.newsapp_mvvm.models.NewsModel;

public interface NewsListener {
    void onNewsClicked(NewsModel newsModel);
}
