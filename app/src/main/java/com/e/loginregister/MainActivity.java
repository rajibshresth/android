package com.e.loginregister;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.e.loginregister.Adapter.ViewPagerAdapter;
import com.e.loginregister.Fragments.LoginActivity;
import com.e.loginregister.Fragments.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.viewpage);
        tabLayout=findViewById(R.id.tab);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new LoginActivity(),"Login");
        viewPagerAdapter.addFragment(new RegisterActivity(),"Register");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
