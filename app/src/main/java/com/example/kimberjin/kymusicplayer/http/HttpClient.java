package com.example.kimberjin.kymusicplayer.http;

import android.util.Log;

import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusic;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusicList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ky4910 on 2019/11/16 16:54
 */
public class HttpClient extends HttpHelper {

    public static final String TAG = "HttpClient";
    private static final String GET_MUSIC_LIST_METHOD = "baidu.ting.billboard.billList";

    private static List<OnlineMusic> onlineList = new ArrayList<>();

    /*
     * 排行音乐
     * 参数： type = 1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,
     *                  22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜
     */
    public static void getOnlineMusicList(String type,int size,int offset) {
        getRequestInstance().getOnLineMusicList(type, String.valueOf(size), String.valueOf(offset), GET_MUSIC_LIST_METHOD)
                .enqueue(new Callback<OnlineMusicList>() {
                    @Override
                    public void onResponse(Call<OnlineMusicList> call, Response<OnlineMusicList> response) {
                        handleResponse(response.body());
                    }

                    @Override
                    public void onFailure(Call<OnlineMusicList> call, Throwable t) {
                        t.printStackTrace();
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
