package com.example.kimberjin.kymusicplayer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.kimberjin.kymusicplayer.application.MusicApplication;

/**
 * Created by ky4910 on 2019/10/30
 */
public class ImageTools {

    // 使用默认比例缩放图片
    public static Bitmap scaleBitmap(Bitmap bmp) {
        return scaleBitmap(bmp, (int)(MusicApplication.mScreenWidth * 0.13));
    }

    // 指定比例，缩放图片
    public static Bitmap scaleBitmap(Bitmap bmp, int size) {
        return Bitmap.createScaledBitmap(bmp, size, size, true);
    }

    // 根据文件uri缩放图片
    public static Bitmap scaleBitmap(String uri) {
        return scaleBitmap(BitmapFactory.decodeFile(uri));
    }

    // 根据文件uri缩放图片,带size参数
    public static Bitmap scaleBitmap(String uri, int size) {
        return scaleBitmap(BitmapFactory.decodeFile(uri), size);
    }

    // 缩放资源图片
    public static Bitmap scaleBitmap(int res) {
        return scaleBitmap(BitmapFactory.decodeResource(MusicApplication.mContext.getResources(), res));
    }
}
