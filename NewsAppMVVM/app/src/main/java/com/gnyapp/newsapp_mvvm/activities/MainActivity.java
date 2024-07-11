package com.gnyapp.newsapp_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.gnyapp.newsapp_mvvm.R;
import com.gnyapp.newsapp_mvvm.adapters.NewsAdapter;
import com.gnyapp.newsapp_mvvm.databinding.ActivityMainBinding;
import com.gnyapp.newsapp_mvvm.listener.NewsListener;
import com.gnyapp.newsapp_mvvm.models.NewsModel;
import com.gnyapp.newsapp_mvvm.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsListener {

    private NewsViewModel newsViewModel;
    private ActivityMainBinding binding;
    private List<NewsModel> newsModelList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recylerView);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        binding.recylerView.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(newsModelList, this::onNewsClicked);
        binding.recylerView.setAdapter(newsAdapter);
        getNews();
    }

    private void getNews() {
        binding.setIsLoading(true);
        newsViewModel.getNews("us", "06729f0bbf334e6887ca50d5a5572a58").observe(this, getNewsApp ->
        {
                if(getNewsApp != null) {
                    if(getNewsApp.getArticles() != null) {
                        binding.setIsLoading(false);
                        newsModelList.addAll(getNewsApp.getArticles());
                        newsAdapter.notifyDataSetChanged();
                    }
                }
        });
    }

    @Override
    public void onNewsClicked(NewsModel newsModel) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(newsModel.getUrl()));
        startActivity(intent);
    }
}