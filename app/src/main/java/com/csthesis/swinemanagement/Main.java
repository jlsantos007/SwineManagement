package com.csthesis.swinemanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Main extends AppCompatActivity implements View.OnClickListener {

    private EditText user, pass;
    private TextView txtRegister;
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_main);

        user        = (EditText) findViewById(R.id.userLog);
        pass        = (EditText) findViewById(R.id.passLog);
        txtRegister = (TextView) findViewById(R.id.register);
        btnLogIn    = (Button)   findViewById(R.id.login);

        txtRegister.setOnClickListener(new View.OnClickListener() {
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

        btnLogIn.setOnClickListener(this);
    }

    private void LogIn() {
        final String username = user.getText().toString().trim();
        final String password = pass.getText().toString().trim();

        class logIn extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Main.this, "Authenticating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Main.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_USER_NAME, username);
                hashMap.put(Config.KEY_USER_PASS, password);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_SELECT, hashMap);
                return s;
            }
        }

        logIn log = new logIn();
        log.execute();

        Intent intent = new Intent(getApplicationContext(), Splash.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogIn){
            LogIn();
        }
        Intent intent = new Intent(getApplicationContext(),NavDrawHome.class);
        startActivity(intent);
    }
}
