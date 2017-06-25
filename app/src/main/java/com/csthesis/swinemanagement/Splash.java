package com.csthesis.swinemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    intent = new Intent(getApplicationContext(), Main.class);
                    finish();
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
}
