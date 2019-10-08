package com.example.kimberjin.kymusicplayer.db;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.kimberjin.kymusicplayer.activity.MainActivity;
import com.example.kimberjin.kymusicplayer.bean.LocalSong;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ky4910 on 2019/9/30
 */
public class DbClient {

    Context mContext;
    private List<LocalSong> localSongsList = new ArrayList<>();

    private static final String TAG = "DbClient";

    public DbClient() {
    }

    public DbClient(Context mContext) {
        this.mContext = mContext;
    }

    public List<LocalSong> getLocalSongs(Context context){
        LocalSong localSongs;
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
                        localSongs = new LocalSong(title, album, artist, duration, composer, date_added);
                        localSongsList.add(localSongs);
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
