package com.example.kimberjin.kymusicplayer.http;

import com.example.kimberjin.kymusicplayer.bean.OnlineMusicList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ky4910 on 2019/11/16 16:54
 */
public class HttpClient extends HttpHelper {

    public static final String METHOD_GET_MUSIC_LIST = "baidu.ting.billboard.billList";

    /*
     * 排行音乐
     * */
    public static void getOnlineMusicList(String type,int size,int offset, final GetRequest<OnlineMusicList> callBack){
        getApiService().getOnLineMusicList(type, String.valueOf(size), String.valueOf(offset), METHOD_GET_MUSIC_LIST)
                .enqueue(new Callback<OnlineMusicList>() {
                    @Override
                    public void onResponse(Call<OnlineMusicList> call, Response<OnlineMusicList> response) {
                        if (response.isSuccessful()) {
                            callBack.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<OnlineMusicList> call, Throwable t) {
                        callBack.onFail(t);
                    }
                });
    }
}
