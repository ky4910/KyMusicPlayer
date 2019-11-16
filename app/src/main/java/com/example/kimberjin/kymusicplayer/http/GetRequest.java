package com.example.kimberjin.kymusicplayer.http;

/**
 * Created by ky4910 on 2019/11/16 16:43
 */
public interface GetRequest<T> {

    void onSuccess(T response);
    void onFail(Throwable t);

}
