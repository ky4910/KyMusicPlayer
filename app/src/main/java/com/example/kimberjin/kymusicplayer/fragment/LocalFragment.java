package com.example.kimberjin.kymusicplayer.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.LocalMusicRvAdapter;

/**
 *  Created by ky4910 on 2019/09/27
 */

public class LocalFragment extends Fragment {

    public static final String TAG = "LOCALSONG";

    private RecyclerView recyclerView;
    LocalMusicRvAdapter rvAdapter;

    public LocalFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_song_item, container, false);
        return view;
    }

    public void initData() {
        
    }

    public void getSongs() {
        try {
            ContentResolver resolver = getContext().getContentResolver();
            Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
                    null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);
            if (cursor != null) {
                while(cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                    String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    String composer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.COMPOSER));
                    Log.e(TAG, title + " " + album + " " + artist + " " + duration + " " + composer);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
