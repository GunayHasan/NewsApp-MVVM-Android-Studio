package com.gnyapp.newsapp_mvvm.network;

import com.gnyapp.newsapp_mvvm.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("top-headlines")
    Call<NewsResponse> getTopHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);

}
