package com.e.loginregister;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.loginregister.API.RegisAPI;
import com.e.loginregister.channel.notification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingActivity extends AppCompatActivity {

    private EditText txtfullname, txtdate, txttime, txtnum, txtemp, txtaddress;
    private Button btnbook;

    private NotificationManagerCompat notificationManagerCompat;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static final String BASE_URL = "http://10.0.2.2:3001/";
    Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        notification channel = new notification(this);
        channel.notification();

        txtfullname =  findViewById(R.id.txtfullname);
        txtdate = findViewById(R.id.txtdate);
        txttime = findViewById(R.id.txttime);
        txtnum  = findViewById(R.id.txtnum);
        txtemp  = findViewById(R.id.txtemp);
        txtaddress  = findViewById(R.id.txtaddress);
        btnbook = findViewById(R.id.btnBook);

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RegisAPI regisAPI = retrofit.create(RegisAPI.class);

                String fullname= txtfullname.getText().toString();
                String date= txtdate.getText().toString();
                String time = txttime.getText().toString();
                String phonenumber = txtnum.getText().toString();
                String admins = txtemp.getText().toString();
                String address = txtaddress.getText().toString();

                Intent intent = new Intent(BookingActivity.this,DashboardActivity.class);
                startActivity(intent);
                DispalyNotification();

                Call<String> appointmentcall = regisAPI.appointment(fullname,date,time, phonenumber, admins, address);

                appointmentcall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(BookingActivity.this,response.body(),Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(BookingActivity.this,"fail",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void DispalyNotification() {
        Notification notification = new NotificationCompat.Builder(this, com.e.loginregister.channel.notification.Channel_3)
                .setSmallIcon(R.drawable.ic_more_black_24dp)
                .setContentTitle("Booking")
                .setContentText("Your Appointment Has been Booked")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(3, notification);
    }
}