package com.gnyapp.newsapp_mvvm.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gnyapp.newsapp_mvvm.network.ApiClient;
import com.gnyapp.newsapp_mvvm.network.ApiService;
import com.gnyapp.newsapp_mvvm.response.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private ApiService apiService;

    public NewsRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<NewsResponse> getNews(String country, String apiKey) {
        MutableLiveData<NewsResponse> data  = new MutableLiveData<>();
        apiService.getTopHeadlines(country, apiKey).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
