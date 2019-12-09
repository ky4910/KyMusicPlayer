package com.example.kimberjin.kymusicplayer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bumptech.glide.load.model.DataUrlLoader;
import com.example.kimberjin.kymusicplayer.bean.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ky4910 on 2019/12/3 21:32
 */
public class DbClient {

    public static final String TAG = "DB_CLIENT";

    private static Context mContext;
    private static DbHelper database;
    private static SQLiteDatabase db;

    private static final String TABLE_PLAY_HISTORY = "PlayHistory";
    private static final String MUSIC_ID = "songId";
    private static final String MUSIC_TITLE = "title";
    private static final String MUSIC_IMG_PATH = "albumImgPath";
    private static final String MUSIC_ARTIST = "artist";
    private static final String MUSIC_DURATION = "duration";
    private static final String MUSIC_URL = "url";
    private static final String MUSIC_LRC_LINK = "lrcLink";
    private static final String MUSIC_TYPE = "type";

    public static void init(Context context) {
        mContext = context;
        database = new DbHelper(mContext);
    }

    public static void addMusic(Music music) {
        List<Music> checkList = getMusic();

        Log.e(TAG, "kimber => add item to database! " + music.getTitle());

        // 最多保存50条音乐数据
        if (checkList.size() == 30) {
            return;
        }

        // 检查是否为重复数据，如果数据存在，则不添加
        for (int i = 0; i < checkList.size(); i++) {
            if (checkList.get(i).getId() == music.getId()) {
                return ;
            }
        }
        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MUSIC_ID, music.getId());
        values.put(MUSIC_TITLE, music.getTitle());
        values.put(MUSIC_IMG_PATH, music.getAlbumImgPath());
        values.put(MUSIC_ARTIST, music.getArtist());
        values.put(MUSIC_DURATION, music.getDuration());
        values.put(MUSIC_URL, music.getUrl());
        values.put(MUSIC_LRC_LINK, music.getLrcLink());
        values.put(MUSIC_TYPE, music.getType());

        db.insert(TABLE_PLAY_HISTORY, null, values);
    }

    public static List<Music> getMusic() {
        List<Music> list = new ArrayList<>();
        if (database == null)
            return list;
        db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAY_HISTORY, null, null, null,
                null, null, null, null);

        while (cursor.moveToNext()) {

            Music music = new Music();

            long id = Long.parseLong(cursor.getString(cursor.getColumnIndex(MUSIC_ID)));
            String title = cursor.getString(cursor.getColumnIndex(MUSIC_TITLE));
            String albumImgPath = cursor.getString(cursor.getColumnIndex(MUSIC_IMG_PATH));
            String artist = cursor.getString(cursor.getColumnIndex(MUSIC_ARTIST));
            int duration = cursor.getInt(cursor.getColumnIndex(MUSIC_DURATION));
            String url = cursor.getString(cursor.getColumnIndex(MUSIC_URL));
            String lrcLink = cursor.getString(cursor.getColumnIndex(MUSIC_LRC_LINK));
            int type = cursor.getInt(cursor.getColumnIndex(MUSIC_TYPE));

            music.setId(id);
            music.setTitle(title);
            music.setAlbumImgPath(albumImgPath);
            music.setArtist(artist);
            music.setDuration(duration);
            music.setUrl(url);
            music.setLrcLink(lrcLink);
            music.setType(type);

            list.add(music);
        }

        return list;
    }
}
