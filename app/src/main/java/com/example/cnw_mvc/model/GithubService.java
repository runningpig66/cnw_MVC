package com.example.cnw_mvc.model;

import com.example.cnw_mvc.Util.InfoInterceptor;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GithubService {
    @GET("users/{username}/repos")
    Observable<List<Repository>> publicRepositories(@Path("username") String username);

    @GET
    Observable<List<Repo>> javaRepositories(@Url String javaUrl);

    class Factory {
        public static GithubService create() {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new InfoInterceptor())
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(GithubService.class);
        }
    }
}
