package com.example.john.gamenews.Interface;

import com.example.john.gamenews.Object.LoginUsuario;
import com.example.john.gamenews.Object.News;
import com.example.john.gamenews.Object.Players;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GameNewsAPI {

    @FormUrlEncoded
    @POST("login")
    Call<LoginUsuario> sign(@Field("user") String username, @Field("password") String password);

    @GET("news")
    Call<List<News>> signNews(@Header("Authorization") String token);

    @GET("players")
    Call<List<Players>> signPlayers(@Header("Authorization") String token);
}
