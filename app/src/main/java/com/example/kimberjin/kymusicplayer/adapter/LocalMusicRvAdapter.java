package com.example.kimberjin.kymusicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.bean.LocalSong;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ky4910 on 2019/9/29
 */
public class LocalMusicRvAdapter extends RecyclerView.Adapter<LocalMusicRvAdapter.LocalSongViewHolder> {

    private Context mContext;
    private List<LocalSong> localSongList;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.mOnItemClickListener = clickListener;
    }

    public LocalMusicRvAdapter(Context mContext, List<LocalSong> localSongList) {
        this.mContext = mContext;
        this.localSongList = localSongList;
    }

    @NonNull
    @Override
    public LocalSongViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.local_song_item, viewGroup, false);
        LocalSongViewHolder localViewHolder = new LocalSongViewHolder(view, mOnItemClickListener);
        return localViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalSongViewHolder localSongViewHolder, int i) {
        LocalSong localSong = localSongList.get(i);
        localSongViewHolder.imageView.setImageResource(R.drawable.default_music);
        localSongViewHolder.textTitle.setText(localSong.getTitle());
        localSongViewHolder.textArtist.setText(localSong.getArtist());
        localSongViewHolder.textDuration.setText("03:10");
        // localSongViewHolder.textDuration.setText(String.format());
    }

    @Override
    public int getItemCount() {
        return localSongList.size();
    }

    public class LocalSongViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.local_iView)
        ImageView imageView;
        @BindView(R.id.local_tv_title)
        TextView textTitle;
        @BindView(R.id.local_tv_artist)
        TextView textArtist;
        @BindView(R.id.local_tv_duration)
        TextView textDuration;

        public LocalSongViewHolder(@NonNull View itemView, final OnItemClickListener onClickListener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onClickListener.onItemClicked(view, position);
                        }
                    }
                }
            });
            ButterKnife.bind(this, itemView);
        }
    }
}
