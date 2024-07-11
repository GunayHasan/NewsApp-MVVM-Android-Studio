package com.gnyapp.newsapp_mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gnyapp.newsapp_mvvm.repositories.NewsRepository;
import com.gnyapp.newsapp_mvvm.response.NewsResponse;

public class NewsViewModel extends ViewModel {

    private NewsRepository newsRepository;

    public NewsViewModel() {
        newsRepository = new NewsRepository();
    }

    public LiveData<NewsResponse> getNews(String country, String apiKey) {
        return newsRepository.getNews(country, apiKey);
    }

}
