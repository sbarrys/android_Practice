package com.kty.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginResultActivity extends AppCompatActivity {

    TextView TextView_get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("LoginResultActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView_get= findViewById(R.id.TextView_get);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String email= bundle.getString("email");
        String password=bundle.getString("password");

        TextView_get.setText(email+"/"+password);
    }
}
