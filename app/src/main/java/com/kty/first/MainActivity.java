package com.kty.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    String email_OK="123@gamil.com";
    String password_OK="1234";
    String email_input="";
    String password_input="";
    TextInputEditText TextInputEditText_email, TextInputEditText_password;//자료형
    RelativeLayout RelativeLayout_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //id를 이용해서 값을 가져온다.
        TextInputEditText_email=findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password=findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login=findViewById(R.id.RelativeLayout_login);
        RelativeLayout_login.setClickable(false);
        TextInputEditText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s , int start,int before , int count){
                    if(s!=null){
                        email_input= s.toString();
                    }
                RelativeLayout_login.setClickable(validation());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s , int start,int before , int count){
                if(s!=null) {
                    password_input = s.toString();
                }

                RelativeLayout_login.setClickable(validation());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //값을 검사해서
        //1.클릭을 감지한다.
        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=TextInputEditText_email.getText().toString();
                String password=TextInputEditText_password.getText().toString();
                //3. 1번의 값을 다음 액티비티로 넘긴다.  Intent이용한다.

                Intent intent= new Intent(MainActivity.this,LoginResultActivity.class);//from왼쪽to 오른쪽
                intent.putExtra("email",email);//이름으로 찾아가서 값을 빼주는것이다.
                intent.putExtra("password",password );

                startActivity(intent);

            }
        });

    }
    public boolean validation(){
        return (email_input.equals(email_OK)  && password_input .equals(password_OK) );
    }

}
