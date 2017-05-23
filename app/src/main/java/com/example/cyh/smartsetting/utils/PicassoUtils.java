package com.example.cyh.smartsetting.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.utils
 * 文件名：  PicassoUtils
 * 创建者：  CYH
 * 创建时间：2017/5/22 16:34
 * 描述：    Picasso封装类
 */

public class PicassoUtils {

    public static void loadImage(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).into(imageView);
    }

    public static void loadImageSize(Context context, String url, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .resize(width, height)
                .centerCrop()
                .into(imageView);
    }

    public static void loadImageHoleer(Context context, String url, int holderImg, int errImg, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .placeholder(holderImg)
                .error(errImg)
                .into(imageView);
    }

    public static void loadImageCrop(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).transform(new CropSquareTransformation()).into(imageView);
    }

    public static class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() { return "cyh"; }
    }
}
