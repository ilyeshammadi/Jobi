package com.example.ilyes.jobi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.database.UserDataSource;

public class SignActivity extends AppCompatActivity {

    EditText mEmailET;
    EditText mPasswordET;
    Button mSubmitBtn;
    UserDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        // Get ref to the views
        mEmailET = (EditText) findViewById(R.id.emial_et);
        mPasswordET = (EditText) findViewById(R.id.password_et);
        mSubmitBtn = (Button) findViewById(R.id.submit_btn);
        dataSource = new UserDataSource(this);

        // When click on submit button get data from the view
        // and check if the user is a worker or a client
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailET.getText().toString();
                String password = mPasswordET.getText().toString();

                dataSource.open();

                if (dataSource.isWorkerExist(email, password)) {
                    startActivity(new Intent(SignActivity.this, MainActivity.class));
                    finish();

                } else if (dataSource.isClientExist(email, password)) {
                    startActivity(new Intent(SignActivity.this, MainActivity.class));
                    finish();
                } else {
                    // Print user does not exixst
                    Toast.makeText(SignActivity.this, "user does not exist", Toast.LENGTH_SHORT).show();
                }

                dataSource.close();
            }
        });
    }
}
