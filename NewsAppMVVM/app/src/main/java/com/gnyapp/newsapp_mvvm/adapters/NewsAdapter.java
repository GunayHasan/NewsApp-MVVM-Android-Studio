package com.gnyapp.newsapp_mvvm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gnyapp.newsapp_mvvm.R;
import com.gnyapp.newsapp_mvvm.databinding.ItemContainerBinding;
import com.gnyapp.newsapp_mvvm.listener.NewsListener;
import com.gnyapp.newsapp_mvvm.models.NewsModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.newsViewHolder>{

    private List<NewsModel> newsModels;
    private LayoutInflater layoutInflater;
    private static NewsListener newsListener;

    public NewsAdapter(List<NewsModel> newsModels, NewsListener newsListener) {
        this.newsModels = newsModels;
        this.newsListener = newsListener;
    }

    @NonNull
    @Override
    public newsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerBinding itemContainerBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container,parent,false
        );

        return new newsViewHolder(itemContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull newsViewHolder holder, int position) {
        holder.bindNews(newsModels.get(position));
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    static class newsViewHolder extends RecyclerView.ViewHolder{

        private ItemContainerBinding itemContainerBinding;

        public newsViewHolder(ItemContainerBinding itemContainerBinding){
            super(itemContainerBinding.getRoot());
            this.itemContainerBinding = itemContainerBinding;
        }

        public void bindNews(NewsModel newsModel){
            itemContainerBinding.setNews(newsModel);
            itemContainerBinding.executePendingBindings();
            itemContainerBinding.getRoot().setOnClickListener(view -> newsListener.onNewsClicked(newsModel));
        }
    }
}
