package com.e.loginregister.Model;

public class TokenAuth {

    private String token;
    private  RegisModel regisModel;

    public TokenAuth(String token, RegisModel regisModel) {
        this.token = token;
        this.regisModel = regisModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisModel getRegisModel() {
        return regisModel;
    }

    public void setRegisModel(RegisModel regisModel) {
        this.regisModel = regisModel;
    }

}
