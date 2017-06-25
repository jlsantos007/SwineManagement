package com.csthesis.swinemanagement;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_main);

        Button register = (Button) findViewById(R.id.register);
        Button login    = (Button) findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FM      = getSupportFragmentManager();
                FragmentTransaction FT  = FM.beginTransaction();
                Register reg            = new Register();
                FT.add(R.id.register_container, reg);
                FT.addToBackStack("reg");
                FT.commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FM      = getSupportFragmentManager();
                FragmentTransaction FT  = FM.beginTransaction();
                Login log               = new Login();
                FT.add(R.id.register_container, log);
                FT.addToBackStack("log");
                FT.commit();
            }
        });
    }
}
