package com.example.kimberjin.kymusicplayer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ky4910 on 2019/12/3 21:06
 */
public class DbHelper extends SQLiteOpenHelper {

    // 无需root权限即可查看数据库的方法 =>
    //      1. debugImplementation 'com.amitshekhar.android:debug-db:1.0.6'
    //      2. adb forward tcp:8080 tcp:8080
    //      3. 浏览器输入: localhost:8080

    private Context mContext;

    private static final String CREATE_PLAY_LIST = "create table PlayHistory ("
            + "id integer primary key autoincrement, "
            + "songId text, " + "title text, " + "albumImgPath text, " + "artist text, "
            + "duration integer, " + "url text, " + "lrcLink text, " + "type integer)";

    public DbHelper(Context context) {
        super(context, "music.db", null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAY_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists PlayHistory");
    }
}
