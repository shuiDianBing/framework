package com.stynet.framework.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.MultiTransformation;
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
public class ImageResourcesAdapter {

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    /**
     *
     * @param imageView
     * @param url
     * @param holderDrawable
     * @param errorDrawable
     * @param filletLeftTop
     * @param filletRightTop
     * @param filletRightBottom
     * @param filletLeftBottom
     */
    @BindingAdapter({"imageUrl", "placeHolder", "error","filletLeftTop","filletRightTop","filletRightBottom","filletLeftBottom"})
    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable
            , int filletLeftTop,int filletRightTop,int filletRightBottom,int filletLeftBottom) {
        //添加过渡动画后占位图当背景显示了
        DrawableTransitionOptions drawableTransitionOptions = DrawableTransitionOptions
                .with(new DrawableCrossFadeFactory.Builder(100).setCrossFadeEnabled(true).build());

        if(0< filletLeftTop || 0< filletRightTop || 0< filletRightBottom || 0< filletLeftBottom) {//角度有一个大于0
            MultiTransformation multiTransformation = variation(filletLeftTop, filletRightTop, filletRightBottom, filletLeftBottom);
            Glide.with(imageView.getContext()).load(url)
                    .apply(RequestOptions.bitmapTransform(multiTransformation)
                            .placeholder(holderDrawable).error(errorDrawable)
                            .priority(Priority.HIGH)
                            .skipMemoryCache(true)//不做内存缓存
                            .diskCacheStrategy(DiskCacheStrategy.NONE))//不做磁盘缓存
                    .transition(drawableTransitionOptions).into(imageView);
        }else {//角度都小于0则显示⚪圆图
            /*GlideCornerTransform transformation = new GlideCornerTransform(imageView.getContext());
            //只是绘制左上角和右上角圆角
            transformation.setExceptCorner(0< filletLeftTop, 0< filletRightTop, 0< filletLeftBottom, 0< filletRightBottom);
            Glide.with(imageView.getContext()).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(transformation).transition(drawableTransitionOptions).into(imageView);*/

            Glide.with(imageView.getContext())
                    .applyDefaultRequestOptions(variation(holderDrawable, errorDrawable, -1)).load(url)
                    .transition(drawableTransitionOptions).into(imageView);
        }
    }

    /**
     *
     * @param imageView
     * @param url
     * @param holderDrawable
     * @param errorDrawable
     * @param filletLeftTop
     * @param filletRightTop
     * @param filletRightBottom
     * @param filletLeftBottom
     */
    @BindingAdapter({"imageUrl", "placeHolder", "error","filletLeftTop","filletRightTop","filletRightBottom","filletLeftBottom"})
    public static void loadImage(ImageView imageView, String url, int holderDrawable, int errorDrawable
            , int filletLeftTop,int filletRightTop,int filletRightBottom,int filletLeftBottom) {
        //添加过渡动画后占位图当背景显示了
        DrawableTransitionOptions drawableTransitionOptions = DrawableTransitionOptions
                .with(new DrawableCrossFadeFactory.Builder(100).setCrossFadeEnabled(true).build());

        if(0< filletLeftTop || 0< filletRightTop || 0< filletRightBottom || 0< filletLeftBottom) {//角度有一个大于0
            MultiTransformation multiTransformation = variation(filletLeftTop, filletRightTop, filletRightBottom, filletLeftBottom);
            Glide.with(imageView.getContext()).load(url)
                    .apply(RequestOptions.bitmapTransform(multiTransformation)
                            .placeholder(holderDrawable).error(errorDrawable)
                            .priority(Priority.HIGH)
                            .skipMemoryCache(true)//不做内存缓存
                            .diskCacheStrategy(DiskCacheStrategy.NONE))//不做磁盘缓存
                    .transition(drawableTransitionOptions).into(imageView);
        }else {//角度都小于0则显示⚪圆图
            /*GlideCornerTransform transformation = new GlideCornerTransform(imageView.getContext());
            //只是绘制左上角和右上角圆角
            transformation.setExceptCorner(0< filletLeftTop, 0< filletRightTop, 0< filletLeftBottom, 0< filletRightBottom);
            Glide.with(imageView.getContext()).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(transformation).transition(drawableTransitionOptions).into(imageView);*/

            Glide.with(imageView.getContext())
                    .applyDefaultRequestOptions(variation(holderDrawable, errorDrawable, -1)).load(url)
                    .transition(drawableTransitionOptions).into(imageView);
        }
    }
    /**
     *
     * @param imageView
     * @param uri
     * @param holderDrawable
     * @param errorDrawable
     * @param filletLeftTop
     * @param filletRightTop
     * @param filletRightBottom
     * @param filletLeftBottom
     */
    @BindingAdapter({"imageUrl", "placeHolder", "error","filletLeftTop","filletRightTop","filletRightBottom","filletLeftBottom"})
    public static void loadImage(ImageView imageView, Uri uri, int holderDrawable, int errorDrawable
            , int filletLeftTop, int filletRightTop, int filletRightBottom, int filletLeftBottom) {
        //添加过渡动画后占位图当背景显示了
        DrawableTransitionOptions drawableTransitionOptions = DrawableTransitionOptions
                .with(new DrawableCrossFadeFactory.Builder(100).setCrossFadeEnabled(true).build());

        if(0< filletLeftTop || 0< filletRightTop || 0< filletRightBottom || 0< filletLeftBottom) {//角度有一个大于0
            MultiTransformation multiTransformation = variation(filletLeftTop, filletRightTop, filletRightBottom, filletLeftBottom);
            Glide.with(imageView.getContext()).load(uri)
                    .apply(RequestOptions.bitmapTransform(multiTransformation)
                            .placeholder(holderDrawable).error(errorDrawable)
                            .priority(Priority.HIGH)
                            .skipMemoryCache(true)//不做内存缓存
                            .diskCacheStrategy(DiskCacheStrategy.NONE))//不做磁盘缓存
                    .transition(drawableTransitionOptions).into(imageView);
        }else {//角度都小于0则显示⚪圆图
            /*GlideCornerTransform transformation = new GlideCornerTransform(imageView.getContext());
            //只是绘制左上角和右上角圆角
            transformation.setExceptCorner(0< filletLeftTop, 0< filletRightTop, 0< filletLeftBottom, 0< filletRightBottom);
            Glide.with(imageView.getContext()).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(transformation).transition(drawableTransitionOptions).into(imageView);*/

            Glide.with(imageView.getContext())
                    .applyDefaultRequestOptions(variation(holderDrawable, errorDrawable, 0)).load(uri)
                    .transition(drawableTransitionOptions).into(imageView);
        }
    }

    /**
     * ↖ ↗
     * ↙ ↘
     * 不规则圆角 | 圆角 | 非圆角
     * Glide输出指定位置圆角,解决ImageView设置scanType="centerCrop"无效(完美解决) https://www.jianshu.com/p/b749a56eff32
     * @param filletLeftTop ↖左上
     * @param filletRightTop ↗右上
     * @param filletRightBottom ↘右下
     * @param filletLeftBottom ↙左下
     * @return
     */
    private static MultiTransformation variation(int filletLeftTop, int filletRightTop, int filletRightBottom, int filletLeftBottom){
        //↖左上
        GlideRoundedCornersTransformation leftTop = new GlideRoundedCornersTransformation
                (filletLeftTop, 0, GlideRoundedCornersTransformation.CornerType.LEFT_TOP);
        //↗右上
        GlideRoundedCornersTransformation rightTop = new GlideRoundedCornersTransformation
                (filletRightTop, 0, GlideRoundedCornersTransformation.CornerType.RIGHT_TOP);
        //↘右下
        GlideRoundedCornersTransformation rightBottom = new GlideRoundedCornersTransformation
                (filletRightBottom, 0, GlideRoundedCornersTransformation.CornerType.RIGHT_BOTTOM);
        //↙左下
        GlideRoundedCornersTransformation leftBottom = new GlideRoundedCornersTransformation
                (filletLeftBottom, 0, GlideRoundedCornersTransformation.CornerType.LEFT_BOTTOM);

        //组合各种Transformation,
        MultiTransformation<Bitmap> mation = new MultiTransformation<>
                //Glide设置圆角图片后设置ImageVIew的scanType="centerCrop"无效解决办法,将new CenterCrop()添加至此
                (new CenterCrop(), leftTop, rightTop,rightBottom,leftBottom);
        return mation;
    }

    /**
     * 圆形 | 圆角 | 非圆角
     * 弃用理由：本方法不能提供不规则的圆角
     * @param holderDrawable
     * @param errorDrawable
     * @param fillet
     * @return
     */
    @Deprecated
    private static RequestOptions variation(Drawable holderDrawable, Drawable errorDrawable, int fillet){
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
        return options;
    }

    /**
     * 圆形 | 圆角 | 非圆角
     * 弃用理由：本方法不能提供不规则的圆角
     * @param holderDrawable
     * @param errorDrawable
     * @param fillet
     * @return
     */
    @Deprecated
    private static RequestOptions variation(int holderDrawable, int errorDrawable, int fillet){
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
        return options;
    }
}
