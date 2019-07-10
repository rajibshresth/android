package com.e.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.loginregister.API.FeedbackAPI;
import com.e.loginregister.API.RegisAPI;
import com.e.loginregister.Model.FeedbackModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedbackActivity extends AppCompatActivity {

    private EditText txtFullname, txtmail, txtmsg;
    private Button btnSend;

    private static final String BASE_URL = "http://10.0.2.2:3001/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        txtFullname = findViewById(R.id.txtFullname);
        txtmail = findViewById(R.id.txtmail);
        txtmsg = findViewById(R.id.txtmsg);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RegisAPI regisAPI = retrofit.create(RegisAPI.class);

                String name= txtFullname.getText().toString();
                String email = txtmail.getText().toString();
                String message = txtmsg.getText().toString();

                FeedbackModel feedbackModel = new FeedbackModel(name, email, message);

                Call<String> feedbackcall = regisAPI.feedback(name,email,message);

                feedbackcall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(FeedbackActivity.this,response.body(),Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(FeedbackActivity.this,"fail",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

    }
}
