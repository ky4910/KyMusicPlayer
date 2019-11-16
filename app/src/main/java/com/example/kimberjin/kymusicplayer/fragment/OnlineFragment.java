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

import com.example.kimberjin.kymusicplayer.R;
import com.example.kimberjin.kymusicplayer.adapter.OnlineMusicRvAdapter;
import com.example.kimberjin.kymusicplayer.bean.Music;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusic;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusicList;
import com.example.kimberjin.kymusicplayer.http.GetRequest;
import com.example.kimberjin.kymusicplayer.http.HttpClient;
import com.example.kimberjin.kymusicplayer.http.HttpHelper;
import com.example.kimberjin.kymusicplayer.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 *  Created by ky4910 on 2019/09/27
 */

public class OnlineFragment extends BaseFragment {

    public static final String TAG = "Online_Fragment";

    RecyclerView recyclerView;
    OnlineMusicRvAdapter rvAdapter;

    Button getBtn;

    private List<Music> onlineSongList = new ArrayList<>();

    public OnlineFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online_music, container, false);
        getBtn = view.findViewById(R.id.online_test_btn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpClient.getOnlineMusicList("1", 3, 0, new GetRequest<OnlineMusicList>() {
                    @Override
                    public void onSuccess(OnlineMusicList response) {
                        parseData(response);
                    }

                    @Override
                    public void onFail(Throwable t) {
                        Log.e(TAG, "get online music list error!");
                        t.printStackTrace();
                    }
                });
            }
        });
        return view;
    }

    @Override
    protected void initView(View view) {
        Log.i(TAG, "initView called!");

        onlineSongList = null;

        recyclerView = view.findViewById(R.id.rv_online_music);
        rvAdapter = new OnlineMusicRvAdapter(getContext(), onlineSongList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = 32;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

    }

    public void parseData(OnlineMusicList body) {
        List<OnlineMusic> list = body.getSong_list();
        for (int i = 0; i < list.size(); i++) {
            OnlineMusic tmpMusic = list.get(i);
            Log.e(TAG, "title:" + tmpMusic.getTitle() + " albumTitle:" + tmpMusic.getAlbum_title()
                    + " artist:" + tmpMusic.getArtist_name() + " lrcLink:" + tmpMusic.getLrclink());
        }
    }
}
