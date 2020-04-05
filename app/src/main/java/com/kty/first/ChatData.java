package com.kty.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChatData  {
    private String nickname;
    private String message;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
