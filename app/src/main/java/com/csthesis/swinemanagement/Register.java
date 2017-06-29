package com.csthesis.swinemanagement;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements View.OnClickListener {


    public Register() {
        // Required empty public constructor
    }

    View mView;
    EditText user, pass;
    TextView signIn;
    Button btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_register, container, false);

        user      = (EditText) mView.findViewById(R.id.user);
        pass      = (EditText) mView.findViewById(R.id.password);
        signIn    = (TextView) mView.findViewById(R.id.signIn);
        btnAdd    = (Button)   mView.findViewById(R.id.btnAddUser);

        btnAdd.setOnClickListener(this);

        return mView;
    }

    private void addUser() {
        final String username = user.getText().toString().trim();
        final String password = pass.getText().toString().trim();

        class AddUser extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), Main.class);
                startActivity(intent);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_USER_NAME, username);
                params.put(Config.KEY_USER_PASS, password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddUser adduser = new AddUser();
        adduser.execute();
    }

    @Override
    public void onClick(View v) {
        addUser();
    }
}
