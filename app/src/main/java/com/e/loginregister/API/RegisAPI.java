package com.e.loginregister.API;

import com.e.loginregister.Model.RegisModel;
import com.e.loginregister.Model.TokenAuth;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RegisAPI {

    @FormUrlEncoded
    @POST("register")
    Call<String> useradd(@Field("fullname") String name,@Field("email") String email,@Field("password") String pass,@Field("phonenumber") String phonenumber,@Field("address") String address,@Field("usertype") String usertype);

    @GET("users")
    Call<List<RegisModel>> getUser();

    @FormUrlEncoded
    @POST("login")
    Call<TokenAuth> Login(@Field("email") String email, @Field("pass") String pass);
}
