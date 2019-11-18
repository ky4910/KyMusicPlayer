package com.example.kimberjin.kymusicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.kimberjin.kymusicplayer.adapter.LocalMusicRvAdapter;
import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.db.DbClient;
import com.example.kimberjin.kymusicplayer.service.PlayerService;
import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ky4910 on 2019/09/27
 */

/*
    播放器底部实现思路：
        方案1. fragment，但侵入性太强，接入成本高
        方案2. 悬浮窗，但需要考虑权限问题，以及退出应用后悬浮窗要隐藏。不过应用内弹窗可能不需要权限
        方案3. rootview 这个思路不错
        方案4. 利用Material Design中的共享元素，因为位置大小都一样，所以跳转Activity没有变化。理论上
                可行，浸入性比fragment小
 */

public class LocalFragment extends BaseFragment {

    public static final String TAG = "Local_Fragment";

    private RecyclerView recyclerView;
    LocalMusicRvAdapter rvAdapter;

    private List<Music> localSongsList = new ArrayList<>();

    public LocalFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView called!");
        View view = inflater.inflate(R.layout.fragment_local_music, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        Log.i(TAG, "initView called!");
        localSongsList = GlobalVal.getLocalMusicList();
        recyclerView = view.findViewById(R.id.rv_local_music);
        Log.e(TAG, "List size is " + localSongsList.size());
        rvAdapter = new LocalMusicRvAdapter(getContext(), localSongsList);

        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = 32;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setOnItemClickListener(new LocalMusicRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(getContext(), localSongsList.get(position).getTitle()
                        + " selected!", Toast.LENGTH_SHORT).show();
                // Fragment调用PlayerService的play()方法，play()中回调OnPlayMusicListener接口的
                // onMusicPlay()，onMusicPlay()由MainActiviti继承OnPlayMusicListener后实现，
                // 并在onMusicPlay()中实现Bottom PlayBar UI更新操作
                GlobalVal.getPlayService().play(localSongsList, position);
            }
        });
    }
}

