package com.example.kimberjin.kymusicplayer.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.kimberjin.kymusicplayer.bean.Music;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 * Created by ky4910 on 2019/10/10
 */
public class GeneralUtil {

    public static final String TAG = "Gerneral_Util";

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
        if (mContext == null) {
            Log.e(TAG, "General Util init fail!");
        }
    }

    public static List<Music> getLocalMusics() {

        List<Music> musicList = new ArrayList<>();

        Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        if (cursor == null) {
            Log.e(TAG, "The cursor is null!");
            return null;
        }

        while (cursor.moveToNext()) {
            long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            // the path of the music
            String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            // get album image by albumId
            String albumId = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
            String imgPath = convertAlbumId2Path(albumId);
            String composer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.COMPOSER));
            String data_added = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_ADDED));
            Music music = new Music(id, title, albumId, imgPath, artist, duration, url, null, composer, data_added);
            musicList.add(music);
        }

        cursor.close();

        return musicList;
    }

    private static String convertAlbumId2Path(String albumId) {

        String mUriAlums = "content://media/external/audio/albums";

        // projection: tell the Provider the column that need to be returned
        String[] projection = new String[]{"album_art"};
        Cursor cur = mContext.getContentResolver().query(Uri.parse(mUriAlums + "/" + albumId),
                projection, null, null, null);

        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }

        cur.close();

        return album_art;
    }

    /**
     * 将毫秒数格式化为"##:##"的时间
     *
     * @param milliseconds 毫秒数
     * @return ##:##
     */
    public static String formatTime(long milliseconds) {

        Log.i(TAG, "unformatted time is " + milliseconds);

        if (milliseconds <= 0 || milliseconds >= 24 * 60 * 60 * 1000) {
            return "00:00";
        }

        long totalSeconds = milliseconds / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;

        StringBuilder stringBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(stringBuilder, Locale.getDefault());

        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }
}
