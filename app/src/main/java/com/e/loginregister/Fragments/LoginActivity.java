package com.e.loginregister.Fragments;


import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.loginregister.BLL.LoginBLL;
import com.e.loginregister.DashboardActivity;
import com.e.loginregister.Model.TokenAuth;
import com.e.loginregister.R;
import com.e.loginregister.channel.notification;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginActivity extends Fragment implements View.OnClickListener{

    private EditText user, pswd;
    private Button btnlogin;

    private NotificationManagerCompat notificationManagerCompat;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static final String BASE_URL = "http://10.0.2.2:3001/";

    public LoginActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.activity_login, container, false);


        View view = inflater.inflate(R.layout.activity_login, container, false);
        notificationManagerCompat = NotificationManagerCompat.from(getActivity());

        notification channel = new notification(getActivity());
        channel.notification();


        user = view.findViewById(R.id.txtEmail);
        pswd = view.findViewById(R.id.txtpass);
        btnlogin = view.findViewById(R.id.btnLogin);

        btnlogin.setOnClickListener(this);
        preferences = getActivity().getSharedPreferences("APP", Context.MODE_PRIVATE);
        editor = preferences.edit();

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnLogin) {

            final String email = user.getText().toString().trim();
            final String password = pswd.getText().toString().trim();

            LoginBLL loginBusinessLogic = new LoginBLL(email,password);
            StrictMode();
            TokenAuth userData = loginBusinessLogic.checkUser();

            preferences = getActivity().getSharedPreferences("UserData", 0);

            editor = preferences.edit();

            editor.putString("token", userData.getToken());
            //editor.putString("uid", userData.getRegisModel().get_id());
            Toast.makeText(getActivity(),"User id:" + userData.getToken(),Toast.LENGTH_SHORT).show();

            editor.commit();

            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            startActivity(intent);
            getActivity().finish();
            DispalyNotification();
        }

        }

    private void DispalyNotification() {
        Notification notification = new NotificationCompat.Builder(getActivity(), com.e.loginregister.channel.notification.Channel_1)
                .setSmallIcon(R.drawable.ic_more_black_24dp)
                .setContentTitle("Login Successful")
                .setContentText("You Have been Logged In")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}