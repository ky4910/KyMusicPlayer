package com.example.kimberjin.kymusicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.bean.Music;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ky4910 on 2019/11/9 21:15
 */
public class OnlineMusicRvAdapter extends RecyclerView.Adapter<OnlineMusicRvAdapter.OnlineViewHolder>{

    private Context mContext;
    private List<Music> musicList;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.mOnItemClickListener = clickListener;
    }

    public OnlineMusicRvAdapter(Context mContext, List<Music> musicList) {
        this.mContext = mContext;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public OnlineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.song_item, viewGroup, false);
        OnlineViewHolder onlineViewHolder = new OnlineViewHolder(view, mOnItemClickListener);
        return onlineViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineViewHolder onlineViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class OnlineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.music_img)
        ImageView imageView;
        @BindView(R.id.tv_music_title)
        TextView textTitle;
        @BindView(R.id.tv_music_artist)
        TextView textArtist;
        @BindView(R.id.tv_music_duration)
        TextView textDuration;

        public OnlineViewHolder(@NonNull View itemView, final OnItemClickListener onClickListener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
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
        }
    }
}
