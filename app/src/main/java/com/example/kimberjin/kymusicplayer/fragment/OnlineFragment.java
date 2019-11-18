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
import android.widget.Button;
import android.widget.Toast;

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.OnlineMusicRvAdapter;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusic;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusicList;
import com.example.kimberjin.kymusicplayer.http.HttpClient;
import com.example.kimberjin.kymusicplayer.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ky4910 on 2019/09/27
 */

public class OnlineFragment extends BaseFragment{

    public static final String TAG = "Online_Fragment";

    RecyclerView recyclerView;
    OnlineMusicRvAdapter rvAdapter;

    View mView;
    Button getBtn;

    static List<OnlineMusic> onlineSongList = new ArrayList<>();

    public OnlineFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpClient.getOnlineMusicList("2", 5, 0);
        Log.e(TAG, "onCreate called!");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView called!");
        mView = inflater.inflate(R.layout.fragment_online_music, container, false);
        onlineSongList = GlobalVal.getOnlineMusicList();

        getBtn = mView.findViewById(R.id.online_test_btn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onlineSongList = GlobalVal.getOnlineMusicList();
                Toast.makeText(getContext(), onlineSongList.get(0).getTitle()
                        + "\nmusic list size is " + onlineSongList.size(), Toast.LENGTH_SHORT).show();
            }
        });

        Log.e(TAG, "onlinemusic size is " + onlineSongList.size());
        return mView;
    }

    @Override
    protected void initView(View view) {
        Log.e(TAG, "initView called!");
        /*
        recyclerView = view.findViewById(R.id.rv_online_music);
        rvAdapter = new OnlineMusicRvAdapter(getContext(), onlineSongList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = 32;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        recyclerView.setAdapter(rvAdapter);
        */
    }

    public void testInitView(OnlineMusicList onlineMusicList) {
        List<OnlineMusic> onlineList = onlineMusicList.getSong_list();
        recyclerView = mView.findViewById(R.id.rv_online_music);
        rvAdapter = new OnlineMusicRvAdapter(getContext(), onlineList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = 32;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        recyclerView.setAdapter(rvAdapter);
    }

}
