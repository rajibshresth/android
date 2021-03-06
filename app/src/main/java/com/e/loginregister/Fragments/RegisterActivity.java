package com.e.loginregister.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.loginregister.API.RegisAPI;
import com.e.loginregister.Model.UserProfile;
import com.e.loginregister.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterActivity extends Fragment implements View.OnClickListener {

    private EditText fullname,email,password,phonenumber, address, usertype;

    private Button btnregister;
    RegisAPI signupAPI;
    private Context mcontext;

    public RegisterActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.activity_register,container,false);

        fullname=view.findViewById(R.id.txtName);
        email=view.findViewById(R.id.txtEmail);
        password=view.findViewById(R.id.txtpass);
        phonenumber=view.findViewById(R.id.txtphone);
        address=view.findViewById(R.id.txtAddress);
        usertype=view.findViewById(R.id.txtUsertype);
        btnregister=view.findViewById(R.id.btnRegis);

        btnregister.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegis){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3001/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            signupAPI = retrofit.create(RegisAPI.class);

            UserProfile userProfile = new UserProfile(
                    fullname.getText().toString(),email.getText().toString(),
                    password.getText().toString(),phonenumber.getText().toString(),
                    address.getText().toString(),usertype.getText().toString());

            Call<Void> usercall = signupAPI.useradd(userProfile);

            usercall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}