package com.example.kimberjin.kymusicplayer.http;

/**
 * Created by ky4910 on 2019/11/18 23:02
 */
public interface OnlineCallBack<T> {

    void onGetInfoSuccess(T response);
    void onGetInfoFail(Throwable t);

}
