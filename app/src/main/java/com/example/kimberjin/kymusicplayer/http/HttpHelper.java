package com.example.kimberjin.kymusicplayer.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ky4910 on 2019/11/16 16:45
 */
public abstract class HttpHelper {

    private static Retrofit retrofit;
    private static OkHttpClient httpClient;

    public static Retrofit initRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder builder = chain.request().newBuilder();
                            builder.addHeader("User-Agent", "Firefox/25.0");
                            return chain.proceed(builder.build());
                        }
                    }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getRequestInstance(){
        return initRetrofit().create(ApiService.class);
    }
}
