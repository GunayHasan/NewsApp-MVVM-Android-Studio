package com.gnyapp.newsapp_mvvm.utilities;

import android.widget.ImageView;

import com.gnyapp.newsapp_mvvm.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter("android:imageURL")
    public static void setImageURL(ImageView imageView, String URL) {

        try {
            imageView.setAlpha(0f);
            Picasso.get().load(URL).noFade().into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start();
                }

                @Override
                public void onError(Exception e) {
                   // imageView.setImageResource(R.drawable.news_img);
                }
            });
        }catch (Exception ignore) {
        }

    }

}
