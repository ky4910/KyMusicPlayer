package com.example.kimberjin.kymusicplayer.service;

import com.example.kimberjin.kymusicplayer.bean.Music;

import java.util.List;

/**
 * Created by ky4910 on 2019/10/8
 */
public interface IPlayerService {
    void onPlay();
    void onPause();
    void onPlayNext();
    void onPlayPrev();
}
