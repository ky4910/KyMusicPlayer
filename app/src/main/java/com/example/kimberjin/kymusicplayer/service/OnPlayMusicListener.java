package com.example.kimberjin.kymusicplayer.service;

/**
 * Created by ky4910 on 2019/10/10
 */
public interface OnPlayMusicListener {

    void onMusicPlay();

    void onMusicCurrentPosition(int currentPosition);

    void onMusicStop();

    void onMusicComplete();

}
