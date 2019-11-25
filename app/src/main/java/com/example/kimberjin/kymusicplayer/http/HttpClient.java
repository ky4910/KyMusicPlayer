package com.example.kimberjin.kymusicplayer.http;

import android.util.Log;

import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusic;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusicList;
import com.example.kimberjin.kymusicplayer.bean.OnlineSong;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ky4910 on 2019/11/16 16:54
 */
public class HttpClient extends HttpHelper {

    private static final String TAG = "HttpClient";
    private static final String GET_MUSIC_LIST_METHOD = "baidu.ting.billboard.billList";
    private static final String GET_MUSIC_LINK = "baidu.ting.song.play";

    private static List<OnlineMusic> onlineList = new ArrayList<>();

    /*
     * 排行音乐
     * 参数： type = 1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,
     *                  22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜
     */
    public static void getOnlineMusicList(String type,int size,int offset, final OnlineCallBack<OnlineMusicList> callback) {
        getRequestInstance().getOnLineMusicList(type, String.valueOf(size), String.valueOf(offset), GET_MUSIC_LIST_METHOD)
                // 指定网络请求在io后台线程中进行
                .subscribeOn(Schedulers.io())
                // 指定observer回调在UI主线程中进行
                .observeOn(AndroidSchedulers.mainThread())
                // 发起请求，请求的结果会回调到订阅者observer中
                .subscribe(new Observer<OnlineMusicList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "Start subscribe connection!");
                    }

                    @Override
                    public void onNext(OnlineMusicList onlineMusicList) {
                        if (callback != null) {
                            callback.onGetInfoSuccess(onlineMusicList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGetInfoFail(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "RxJava onComplete called!");
                    }
                });
          /*
                .enqueue(new Callback<OnlineMusicList>() {
                    @Override
                    public void onResponse(@NonNull Call<OnlineMusicList> call, @NonNull Response<OnlineMusicList> response) {
                        if (callback != null) {
                            callback.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OnlineMusicList> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        callback.onFail(t);
                    }
                });
        */
    }

    public static void getOnlineMusicLink(String songId, final OnlineCallBack<OnlineSong> callback) {
        getRequestInstance().getMusicLink(songId, GET_MUSIC_LINK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OnlineSong>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OnlineSong onlineSong) {
                        callback.onGetPlayLinkSuccess(onlineSong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGetPlaylinkFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private static void handleResponse(OnlineMusicList body) {
        GlobalVal.setOnlineMusicList(body.getSong_list());
        onlineList = GlobalVal.getOnlineMusicList();
        for (int i = 0; i < onlineList.size(); i++) {
            Log.e(TAG, "title:" + onlineList.get(i).getTitle()
                    + " artist:" + onlineList.get(i).getArtist_name()
                    + " lrclink:" + onlineList.get(i).getLrclink());
        }
    }
}
