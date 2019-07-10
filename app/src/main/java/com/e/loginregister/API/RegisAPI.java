package com.e.loginregister.API;

import com.e.loginregister.Model.RegisModel;
import com.e.loginregister.Model.TokenAuth;
import com.e.loginregister.Model.UserProfile;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Date;
import java.util.List;

public interface RegisAPI {

    @POST("Signup")
    Call<Void> useradd(@Body UserProfile userProfile);

    @GET("users")
    Call<List<RegisModel>> getUser();

    @FormUrlEncoded
    @POST("login")
    Call<TokenAuth> Login(@Field("email") String email, @Field("password") String pass);


    @PUT("/v1/user/{id}")
    Call<Void> userUpdate(@Path("id") int id, @Body UserProfile userProfileModel);

    @FormUrlEncoded
    @POST("/feedback")
    Call<String> feedback(
                    @Field("name")String name,
                    @Field("email")String email,
                    @Field("message")String message);

    @FormUrlEncoded
    @POST("/appointment")
    Call<String> appointment(
            @Field("fullname")String name,
            @Field("date") String date,
            @Field("time")String time,
            @Field("phonenumber")String phonenumber,
            @Field("admin")String admin,
            @Field("address")String address);
}
