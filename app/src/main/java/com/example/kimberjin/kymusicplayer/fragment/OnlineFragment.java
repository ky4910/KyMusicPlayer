package com.example.kimberjin.kymusicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.OnlineMusicRvAdapter;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusic;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusicList;
import com.example.kimberjin.kymusicplayer.bean.OnlineSong;
import com.example.kimberjin.kymusicplayer.http.HttpClient;
import com.example.kimberjin.kymusicplayer.http.OnlineCallBack;
import com.example.kimberjin.kymusicplayer.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ky4910 on 2019/09/27
 */

public class OnlineFragment extends BaseFragment implements OnlineCallBack {

    public static final String TAG = "Online_Fragment";

    // example URL => http://tingapi.ting.baidu.com/v1/restserver/ting?size=3&type=1&offset=0&method=baidu.ting.billboard.billList
    // example URL2 => http://tingapi.ting.baidu.com/v1/restserver/ting?songid=672299768&method=baidu.ting.song.play

    View mView;
    RecyclerView recyclerView;
    OnlineMusicRvAdapter rvAdapter;

    static List<OnlineMusic> onlineMusicList = new ArrayList<>();

    public OnlineFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpClient.getOnlineMusicList("2", 50, 0, this);
        Log.e(TAG, "onCreate called!");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView called!");
        mView = inflater.inflate(R.layout.fragment_online_music, container, false);
        onlineMusicList = GlobalVal.getOnlineMusicList();

        Log.e(TAG, "onlinemusic size is " + onlineMusicList.size());
        return mView;
    }

    @Override
    protected void initView(View view) {
        Log.e(TAG, "initView called!");
    }

    public void loadView(final OnlineMusicList onlineMusicList) {

        final List<OnlineMusic> onlineList = onlineMusicList.getSong_list();

        recyclerView = mView.findViewById(R.id.rv_online_music);
        rvAdapter = new OnlineMusicRvAdapter(getContext(), onlineList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = 32;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        /*
        for (int i = 0; i < onlineList.size(); i++) {
            Log.e(TAG, "title:" + onlineList.get(i).getTitle() + " artist:"
                    + onlineList.get(i).getArtist_name() + " lrcLink:" + onlineList.get(i).getLrclink());
        }
        */

        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setOnItemClickListener(new OnlineMusicRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(getContext(), onlineList.get(position).getTitle()
                        + " selected!", Toast.LENGTH_SHORT).show();
                getSongUrl(onlineMusicList.getSong_list().get(position).getSong_id());
            }
        });
    }

    private void getSongUrl(String songId) {
        Log.e(TAG, "getSongUrl => " + "songId is " + songId);
        HttpClient.getOnlineMusicLink(songId, this);
    }

    @Override
    public void onGetInfoSuccess(Object response) {
        OnlineMusicList musicList = (OnlineMusicList) response;
        Log.e(TAG, "music size is " + musicList.getSong_list().size());
        loadView(musicList);
    }

    @Override
    public void onGetInfoFail(Throwable t) {
        Log.e(TAG, "Get music list failed!" + t.toString());
    }

    @Override
    public void onGetPlayLinkSuccess(Object response) {
        OnlineSong onlineSong = (OnlineSong) response;
        Log.e(TAG,  " onGetPlayLinkSuccess => " + onlineSong.getSongBitrate().getFile_link());
    }

    @Override
    public void onGetPlaylinkFail(Throwable t) {
        t.printStackTrace();
        Log.e(TAG, "onGetPlaylinkFail called!");
    }
}


