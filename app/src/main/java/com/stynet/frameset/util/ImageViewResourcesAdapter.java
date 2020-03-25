package com.stynet.frameset.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

/**
 * Created by shuiDianBing on 18:53.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << mvvm glide 图片绑定adapter
 */
public class ImageViewResourcesAdapter {

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter({"imageUrl", "placeHolder", "error","fillet"})
    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable,int fillet) {
        //添加过渡动画后占位图当背景显示了
        DrawableTransitionOptions drawableTransitionOptions = DrawableTransitionOptions
                .with(new DrawableCrossFadeFactory.Builder(400).setCrossFadeEnabled(true).build());

        RequestOptions options = new RequestOptions().placeholder(holderDrawable).error(errorDrawable)
                .priority(Priority.HIGH)
                .skipMemoryCache(true)//不做内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE);//不做磁盘缓存

        if(0> fillet)//圆形图片
            options.circleCrop();//bitmapTransform(new CircleCrop());
        else if(0< fillet)//圆角图片
            options.transform(new CenterCrop(),new RoundedCorners(fillet));
        else//
            options.centerCrop();

        Glide.with(imageView.getContext()).applyDefaultRequestOptions(options).load(url)
                .transition(drawableTransitionOptions).into(imageView);
    }
}
