package com.example.kimberjin.kymusicplayer.db;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.kimberjin.kymusicplayer.bean.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ky4910 on 2019/9/30
 */
public class DbClient {

    Context mContext;
    private List<Music> localSongsList = new ArrayList<>();

    private static final String TAG = "DbClient";

    public DbClient() {
    }

    public DbClient(Context mContext) {
        this.mContext = mContext;
    }

    public List<Music> getLocalSongs(Context context){
        Music music;
        try {
            if (context != null) {
                ContentResolver resolver = context.getContentResolver();
                Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
                        null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                        String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                        String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                        long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                        String composer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.COMPOSER));
                        String date_added = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_ADDED));
                        Log.e(TAG, title + " " + album + " " + artist + " " + duration + " " + composer + " " + date_added);
                        music = new Music(0, title, album, null, artist, duration,
                                null, null, composer, date_added, 0);
                        localSongsList.add(music);
                    }
                    cursor.close();
                }
            } else {
                Log.e(TAG, "mContext is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localSongsList;
    }
}
