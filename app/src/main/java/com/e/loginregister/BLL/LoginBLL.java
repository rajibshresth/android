package com.e.loginregister.BLL;

import com.e.loginregister.API.RegisAPI;
import com.e.loginregister.Model.TokenAuth;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginBLL {

    private String email;
    private String password;
    TokenAuth authtoken;
    private static final String BASE_URL = "http://10.0.2.2:3001/";
    Retrofit retrofit;

    public LoginBLL (String email, String password){
        this.email = email;
        this.password = password;
    }

    public TokenAuth  checkUser(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisAPI regisAPI = retrofit.create(RegisAPI.class);
        Call<TokenAuth> Logincall = regisAPI.Login(email, password);
        try {
            Response<TokenAuth> loginResponse = Logincall.execute();
            authtoken = loginResponse.body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return authtoken;
    }

}
