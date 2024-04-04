package com.example.schoolboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolboard.ApiResponse.ApiError;
import com.example.schoolboard.ApiResponse.Roles;
import com.example.schoolboard.ApiResponse.User;
import com.example.schoolboard.ApiResponse.UserJWT;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_login);
        EditText loginEditText = findViewById(R.id.loginEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button submitButton = findViewById(R.id.loginButton);
        TextView textView = findViewById(R.id.loginWelcomeTextView);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                NetworkService.getInstance().getBackService()
                        .login(login,password).enqueue(new Callback<UserJWT>() {
                            @Override
                            public void onResponse(Call<UserJWT> call, Response<UserJWT> response) {
                                try {
                                    if(response.isSuccessful()){

                                        UserJWT userJWT = response.body();
//                                        textView.setText(userJWT.getToken());
                                        JSONObject json = NetworkService.decoded(userJWT.getToken());
                                        JSONObject userInfoJson = json.getJSONObject("userInfo");
                                        //JSONArray rolesJson = json.getJSONArray("roles");
                                        User.setId(json.getInt("id"));
                                        Log.d("UserRoles", "UserRoles: "+User.roles);
                                        User.setGrade(userInfoJson.getInt("grade"));
                                        User.setName(userInfoJson.getString("name"));
                                        User.setSurname(userInfoJson.getString("surname"));
                                        User.setPatronymic(userInfoJson.getString("patronymic"));
                                        User.setCurrentPoints(userInfoJson.getInt("currentPoints"));
                                        User.setOverallPoints(userInfoJson.getInt("overallPoints"));
                                        User.setSchool(userInfoJson.getString("school"));
                                        User.setDateOfBirth(userInfoJson.get("dateOfBirth").toString());
                                        Log.d("test", "grade: " + User.getGrade());
                                        Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                                        startActivity(i);
                                    }else{
                                        Gson gson = new Gson();
                                        ApiError message=gson.fromJson(response.errorBody().charStream(),ApiError.class);
                                        Toast.makeText(getApplicationContext(), "Вы ввели неверные данные, повторите попытку", Toast.LENGTH_LONG).show();
                                        loginEditText.setText("");
                                        passwordEditText.setText("");
                                        loginEditText.setHintTextColor(Color.RED);
                                        passwordEditText.setHintTextColor(Color.RED);
                                    }

                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            @Override
                            public void onFailure(Call<UserJWT> call, Throwable t) {
                                textView.setText("Error occurred while getting request!");
                                t.printStackTrace();
                            }
                        });
            }
        });

    }
}