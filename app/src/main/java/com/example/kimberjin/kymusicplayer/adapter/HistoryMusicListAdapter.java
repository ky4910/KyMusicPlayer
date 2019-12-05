package com.example.kimberjin.kymusicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.util.GeneralUtil;

import java.util.List;

/**
 * Created by ky4910 on 2019/12/4 23:33
 */
public class HistoryMusicListAdapter extends BaseAdapter {

    private List<Music> mData;
    private Context mContext;

    public HistoryMusicListAdapter(List<Music> mData, Context context) {
        this.mData = mData;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Music music = mData.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.song_item, parent, false);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.music_img);
            holder.tvTitle = convertView.findViewById(R.id.tv_music_title);
            holder.tvArtist = convertView.findViewById(R.id.tv_music_artist);
            holder.tvDuration = convertView.findViewById(R.id.tv_music_duration);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.default_music)
                .priority(Priority.HIGH);

        Glide.with(mContext)
                .load(music.getAlbumImgPath())
                .apply(options)
                .into(holder.img);

        holder.tvTitle.setText(music.getTitle());
        holder.tvArtist.setText(music.getArtist());
        holder.tvDuration.setText(GeneralUtil.formatLocalSongTime(music.getDuration()));

        return convertView;
    }

    public static class ViewHolder {
        ImageView img;
        TextView tvTitle;
        TextView tvArtist;
        TextView tvDuration;
    }
}
