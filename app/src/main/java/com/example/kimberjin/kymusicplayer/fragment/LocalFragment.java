package com.example.kimberjin.kymusicplayer.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.Image;
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
import android.widget.Toast;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.LocalMusicRvAdapter;
import com.example.kimberjin.kymusicplayer.bean.LocalSong;
import com.example.kimberjin.kymusicplayer.db.DbClient;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ky4910 on 2019/09/27
 */

public class LocalFragment extends Fragment {

    public static final String TAG = "LOCALSONG";

    private RecyclerView recyclerView;
    LocalMusicRvAdapter rvAdapter;
    DbClient dbClient = new DbClient(getContext());

    private List<LocalSong> localSongsList = new ArrayList<>();

    public LocalFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_music, container, false);
        localSongsList = dbClient.getLocalSongs();
        recyclerView = view.findViewById(R.id.rv_local_music);
        rvAdapter = new LocalMusicRvAdapter(getContext(), localSongsList);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setOnItemClickListener(new LocalMusicRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(getContext(), localSongsList.get(position).getTitle()
                        + "selected!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

