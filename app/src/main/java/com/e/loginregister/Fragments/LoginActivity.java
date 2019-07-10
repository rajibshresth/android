package com.e.loginregister.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.loginregister.AdminDashboardActivity;
import com.e.loginregister.BLL.LoginBLL;
import com.e.loginregister.DashboardActivity;
import com.e.loginregister.MainActivity;
import com.e.loginregister.Model.TokenAuth;
import com.e.loginregister.R;

import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginActivity extends Fragment implements View.OnClickListener{

    private EditText user, pswd;
    private Button btnlogin;

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
        }

        }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}