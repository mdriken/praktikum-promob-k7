package com.example.riken.etic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riken.etic.models.LoginResponse;
import com.example.riken.etic.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {
    private Call<LoginResponse> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputEditText etName = (TextInputEditText) findViewById(R.id.tvUsername);
        final TextInputEditText etPassword = (TextInputEditText) findViewById(R.id.tvPassword);
        MaterialButton btnLogin = (MaterialButton) findViewById(R.id.btnLogin);
        TextView btnRegister = (TextView) findViewById(R.id.btn_intent_signup);

        if (SharedPrefManager.getmInstance(this).isLoggin()){
            Toast.makeText(Login.this,"logged in",Toast.LENGTH_LONG).show();
//            Toast.makeText(MainActivity.this, "Hello, You are still login!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Login.this,"not logged in",Toast.LENGTH_LONG).show();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etName.getText().toString();
                String password = etPassword.getText().toString();
//                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

//                String savedPassword = preferences.getString(password, "");
//                String savedUserName = preferences.getString(user, "");

//                String userDetails = preferences.getString(user + password + "data","No information on that user.");
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("display",userDetails);
//                editor.commit();
                call = ApiClient.getApiService().login("jomblo1@gmail.com","gunawan26");
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        Log.d("response", " "+ response.body().getAccess_token());
                        if (response.body().isStatus()){
                            SharedPrefManager.getmInstance(Login.this).saveToken(loginResponse);
                            Intent displayScreen = new Intent(Login.this, MainActivity.class);
                            startActivity(displayScreen);
                            Toast.makeText(Login.this,"successfully login"+response.body().getAccess_token(),Toast.LENGTH_LONG).show();
                            Toast.makeText(Login.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                        }else{

                        }
//                        Toast.makeText(Login.this, "Ops.. Something went wrong with your internet connection", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(Login.this, "Ops.. Something went wrong with your internet connection", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerScreen = new Intent(Login.this, Register.class);
                startActivity(registerScreen);
            }
        });
    }



}
