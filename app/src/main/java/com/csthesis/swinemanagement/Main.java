package com.csthesis.swinemanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
//import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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

//    public static final String MyPREFERENCES = "MyPrefs";
//    public static final String Username      = "userKey";
//    public static final String Password      = "passKey";
//
//    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        if(!isConnected(Main.this)) {
            buildDialog(Main.this).show();
        }
        else {
            setContentView(R.layout.layout_main);
        }
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
//        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        }
        else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
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
                String res= rh.sendPostRequest(Config.URL_SELECT, hashMap);
                return res;
            }
        }

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(Username, username);
//        editor.putString(Password, password);
//        editor.commit();

        logIn log = new logIn();
        log.execute();

        Intent intent = new Intent(getApplicationContext(), NavDrawHome.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogIn){
            LogIn();
        }
    }
}
