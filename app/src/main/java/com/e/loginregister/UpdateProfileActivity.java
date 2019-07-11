package com.e.loginregister;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.loginregister.API.RegisAPI;
import com.e.loginregister.Model.RegisModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtfullname, txtemail,  txtPwd, txtphone, txtaddress;
    Button btnupdate;

    private static final String BASE_URL = "http://10.0.2.2:3001/";
    RegisAPI regisAPI;
    Retrofit retrofit;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        LoadUserDetail();
        txtfullname = findViewById(R.id.txtfullname);
        txtemail = findViewById(R.id.txtemail);
        txtPwd = findViewById(R.id.txtPwd);
        txtphone = findViewById(R.id.txtphone);
        txtaddress = findViewById(R.id.txtAddress);
        btnupdate = findViewById(R.id.btnupdate);

        btnupdate.setOnClickListener(this);
    }

    private void LoadUserDetail() {
        createInstance();
        sharedPreferences = getSharedPreferences("UserData", 0);

        String userid = sharedPreferences.getString("uid", null);
        Toast.makeText(UpdateProfileActivity.this, "User id" + userid, Toast.LENGTH_SHORT).show();

        Call<RegisModel> userModelCall = regisAPI.loadprofile(userid);

        userModelCall.enqueue(new Callback<RegisModel>() {
            @Override
            public void onResponse(Call<RegisModel> call, Response<RegisModel> response) {
                RegisModel regisModel = response.body();
                txtfullname.setText(regisModel.getFullname());
                txtemail.setText(regisModel.getEmail());
                txtPwd.setText(regisModel.getPassword());
                txtphone.setText(regisModel.getPhonenumber());
                txtaddress.setText(regisModel.getAddress());
            }

            @Override
            public void onFailure(Call<RegisModel> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnupdate){
            updatedetail();
        }

    }

    private void updatedetail() {
        createInstance();
        String newfullname, newemail, newpassword, newphonenumber, newaddress;
        newfullname = txtfullname.getText().toString();
        newemail = txtemail.getText().toString();
        newpassword = txtPwd.getText().toString();
        newphonenumber = txtphone.getText().toString();
        newaddress = txtaddress.getText().toString();

        SharedPreferences sharedPreferences = (UpdateProfileActivity.this).getSharedPreferences("UserData",0);
        String userId = sharedPreferences.getString("uid", null);
        Toast.makeText(this, "User id: +" +userId, Toast.LENGTH_SHORT).show();

        Call<String> updateProfileData = regisAPI.updatedetail(userId, newfullname, newemail, newpassword, newphonenumber, newaddress);

        updateProfileData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(UpdateProfileActivity.this, "Profile Update Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(UpdateProfileActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void createInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        regisAPI = retrofit.create(RegisAPI.class);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
