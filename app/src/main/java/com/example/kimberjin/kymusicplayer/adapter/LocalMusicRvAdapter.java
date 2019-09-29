package com.example.kimberjin.kymusicplayer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ky4910 on 2019/9/29
 */
public class LocalMusicRvAdapter extends RecyclerView.Adapter<LocalMusicRvAdapter.LocalSongViewHolder> {



    @NonNull
    @Override
    public LocalSongViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalSongViewHolder localSongViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LocalSongViewHolder extends RecyclerView.ViewHolder {
        public LocalSongViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

