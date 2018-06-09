package com.example.john.gamenews.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUsuario {

    @SerializedName("token")
    @Expose
    private String token;

    public LoginUsuario() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
