package com.example.kimberjin.kymusicplayer.http;

import com.example.kimberjin.kymusicplayer.bean.OnlineMusic;
import com.example.kimberjin.kymusicplayer.bean.OnlineMusicList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ky4910 on 2019/11/16 16:43
 */
public interface ApiService {

    // example URL => http://tingapi.ting.baidu.com/v1/restserver/ting?size=3&type=1&offset=0&method=baidu.ting.billboard.billList

    String BASE_URL = "http://tingapi.ting.baidu.com/";
    String BASE_URL_BEHIND = "v1/restserver/ting";

    /*
         注解里传入网络请求的部分URL地址
         Retrofit把网络请求的URL分成了两部分，一部分放在Retrofit对象里，另一部分放在网络请求接口里
         如果接口里的URL是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
         getOnLineMusicList()等api是接收网络请求数据的方法
     */
    @GET(BASE_URL_BEHIND)
    Call<OnlineMusicList> getOnLineMusicList(@Query("type")String type, @Query("size") String size,
                                               @Query("offset") String offset, @Query("method") String method);
    /*
    @GET(BASE_URL_BEHIND)
    Call<MusicLink> getMusicLink(@Query("songid")String songId, @Query("method") String method);

    @GET
    Call<ResponseBody> download(@Url String url);

    @GET(BASE_URL_BEHIND)
    Call<SearchMusic> getSearchMusic(@Query("method") String method, @Query("query")String keyword);

    @GET(BASE_URL_BEHIND)
    Call<Lrc> getLrc(@Query("method") String method, @Query("songid")String id);
    */
}
