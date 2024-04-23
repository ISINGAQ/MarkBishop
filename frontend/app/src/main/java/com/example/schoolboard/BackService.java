package com.example.schoolboard;

import com.example.schoolboard.ApiResponse.AchievementGrade;
import com.example.schoolboard.ApiResponse.AchievementType;
import com.example.schoolboard.ApiResponse.Achievements;
import com.example.schoolboard.ApiResponse.Event;
import com.example.schoolboard.ApiResponse.RecordResponse;
import com.example.schoolboard.ApiResponse.ShopList;
import com.example.schoolboard.ApiResponse.TopUser;
import com.example.schoolboard.ApiResponse.UserJWT;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BackService {
    @FormUrlEncoded
    @POST("auth/login")
    public Call<UserJWT> login(@Field("login") String login, @Field("password") String password);

    @FormUrlEncoded
    @POST("events/createRecord")
    public Call<RecordResponse> sendRecord(@Field("userId") int userId, @Field("eventId") int eventId);

    @GET("/events/getAllEvents")
    public Call<ArrayList<Event>> getAllPosts();

    @GET("/achievements/getAllAchievementsByUser")
    public Call<ArrayList<Achievements>> getAllAchievementsByUser(@Query("userId") int userId);

    @GET("/achievements/getTypeById")
    public Call<AchievementType> getAchievementType(@Query("id") int id);
    @GET("/achievements/getGradeById")
    public Call<AchievementGrade> getAchievementGrade(@Query("id") int id);
    @GET("/events/getOneRecord")
    public Call<RecordResponse> getOneRecord(@Query("userId") int userId, @Query("eventId") int eventId);

    @GET("/shop/getAllShop")
    public Call<ArrayList<ShopList>> getAllShops();

    @GET("/users/getTop10")
    public Call<ArrayList<TopUser>> getTop10();


}
