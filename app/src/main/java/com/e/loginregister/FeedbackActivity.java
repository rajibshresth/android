package com.e.loginregister;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.loginregister.API.RegisAPI;
import com.e.loginregister.Model.FeedbackModel;
import com.e.loginregister.channel.notification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedbackActivity extends AppCompatActivity {

    private EditText txtFullname, txtmail, txtmsg;
    private Button btnSend;

    private NotificationManagerCompat notificationManagerCompat;

    SharedPreferences preferences;
    SensorManager sensorManager;
    SharedPreferences.Editor editor;

    private static final String BASE_URL = "http://10.0.2.2:3001/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        notification channel = new notification(this);
        channel.notification();

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

                Intent intent = new Intent(FeedbackActivity.this,DashboardActivity.class);
                startActivity(intent);
                DispalyNotification();

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

    private void DispalyNotification() {
        Notification notification = new NotificationCompat.Builder(this, com.e.loginregister.channel.notification.Channel_2)
                .setSmallIcon(R.drawable.ic_more_black_24dp)
                .setContentTitle("Feedback")
                .setContentText("Your Feedback Has been Sent")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1, notification);
    }
    public void proximity()
    {
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensor == null)
        {
            Toast.makeText(this, "No sensor detected", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sensor Kicking in .....", Toast.LENGTH_SHORT).show();
        }

        SensorEventListener proximityListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                WindowManager.LayoutParams params = FeedbackActivity.this.getWindow().getAttributes();
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] == 0) {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = 0;
                        getWindow().setAttributes(params);
                    } else {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = -1f;
                        getWindow().setAttributes(params);
                    }
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(proximityListener,sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
}
