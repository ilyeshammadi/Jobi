package com.example.ilyes.jobi.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.database.UserDataSource;
import com.example.ilyes.jobi.others.FakeData;
import com.example.ilyes.jobi.others.Util;
import com.gc.materialdesign.views.ButtonFlat;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class SignActivity extends Activity implements Validator.ValidationListener {

    @NotEmpty
    @Email
    EditText mEmailET;

    @NotEmpty
    @Password
    EditText mPasswordET;

    Button mSubmitBtn;
    ButtonFlat mSignUpWorker;
    ButtonFlat mSignUpClient;
    UserDataSource dataSource;

    Validator validator;


    SharedPreferences settings;

    public static final String IS_APP_FIRST_RUN = "is_app_first_run";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();


        if (!settings.getBoolean(IS_APP_FIRST_RUN, false)) {
            // Dummy data generator
            FakeData.generate(this);
            editor.putBoolean(IS_APP_FIRST_RUN, true);
            editor.apply();
        }


        // Get ref to the views
        mEmailET = (EditText) findViewById(R.id.emial_et);
        mPasswordET = (EditText) findViewById(R.id.password_et);
        mSubmitBtn = (Button) findViewById(R.id.submit_btn);
        mSignUpWorker = (ButtonFlat) findViewById(R.id.signin_worker_btn);
        mSignUpClient = (ButtonFlat) findViewById(R.id.signin_client_btn);
        dataSource = new UserDataSource(this);


        // The EditText validator
        validator = new Validator(this);
        validator.setValidationListener(this);

        // When click on submit button get data from the view
        // and check if the user is a worker or a client
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate the input before using it
                validator.validate();
            }
        });


        // Click Sign up as a Worker
        mSignUpWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignActivity.this, SignUpWorkerActivity.class));
            }
        });


        // Click Sign up as a Client
        mSignUpClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignActivity.this, SignUpClientActivity.class));
            }
        });

    }

    @Override
    public void onValidationSucceeded() {

        // If the validation succeeded
        // get the data from the EditText
        // and search for the user
        // in the workers and clients table
        String email = mEmailET.getText().toString();
        String password = mPasswordET.getText().toString();

        dataSource.open();

        Intent intent = new Intent(SignActivity.this, MainActivity.class);


        if (dataSource.isWorkerExist(email, password)) {

            // Put in the intent the id and the type of the user
            intent.putExtra(Util.ID_FLAG, dataSource.getWorkerId(email, password) + "");
            intent.putExtra(Util.USER_TYPE_FLAG, "worker");

            startActivity(intent);
            finish();

        } else if (dataSource.isClientExist(email, password)) {

            intent.putExtra(Util.ID_FLAG, dataSource.getClientId(email, password) + "");
            intent.putExtra(Util.USER_TYPE_FLAG, "client");

            startActivity(intent);
            finish();
        } else {
            // Print user does not exixst
            Toast.makeText(SignActivity.this, "user does not exist", Toast.LENGTH_SHORT).show();
        }

        dataSource.close();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
