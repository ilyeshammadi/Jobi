package com.example.ilyes.jobi.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
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
import com.rubengees.introduction.IntroductionBuilder;
import com.rubengees.introduction.entity.Slide;

import java.util.ArrayList;
import java.util.List;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

public class SignActivity extends Activity implements Validator.ValidationListener {

    @NotEmpty
    @Email
    EditText mEmailET;

    @NotEmpty
    @Password
    EditText mPasswordET;

    Button mSubmitBtn;
    ButtonFlat mSignup;
    ButtonFlat mSignUpWorker;
    ButtonFlat mSignUpClient;
    UserDataSource dataSource;

    Validator validator;


    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        //Install CustomActivityOnCrash
        CustomActivityOnCrash.install(this);

        settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();


        if (!settings.getBoolean(Util.IS_APP_FIRST_RUN, false)) {
            // Show the slides
            new IntroductionBuilder(this).withSlides(generateSlides()).introduceMyself();
            // Dummy data generator
            FakeData.generate(this);
            editor.putBoolean(Util.IS_APP_FIRST_RUN, true);
            editor.apply();
        }


        // Get ref to the views
        mEmailET = (EditText) findViewById(R.id.emial_et);
        mPasswordET = (EditText) findViewById(R.id.password_et);
        mSubmitBtn = (Button) findViewById(R.id.submit_btn);
        mSignup = (ButtonFlat) findViewById(R.id.sign_up_flatt_btn);
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

        // When the user click on don't have an account button
        // display a dialog box to sign up and choice
        // what of type of account to sign up with
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create and setup the dialog box
                MaterialDialog dialog = new MaterialDialog.Builder(SignActivity.this)
                        .title("Sign up")
                        .customView(R.layout.signup, false)
                        .build();

                dialog.show();

                View signUpView = dialog.getCustomView();

                if (signUpView != null) {

                    // Inflate the sig up view views
                    mSignUpWorker = (ButtonFlat) signUpView.findViewById(R.id.signin_worker_btn);
                    mSignUpClient = (ButtonFlat) signUpView.findViewById(R.id.signin_client_btn);

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


    private List<Slide> generateSlides() {
        List<Slide> result = new ArrayList<>();


        result.add(new Slide()
                .withTitle("Welcome to Jobi")
                .withImage(R.drawable.jobi_logo)
                .withColorResource(R.color.md_indigo_500));

        result.add(new Slide()
                .withTitle("Jobi with a Client")
                .withDescription("La recherche et l'affichagee automatique des ouvrier de la régions et les mieux califier")
                .withColorResource(R.color.md_indigo_500)
                .withImage(R.drawable.clients));


        result.add(new Slide()
                .withTitle("Jobi with a Worker")
                .withDescription("La recherche et la mise en contact de clients rapidement et efficasement")
                .withColorResource(R.color.md_indigo_500)
                .withImage(R.drawable.farmer)
        );


        result.add(new Slide()
                .withTitle("The Goal is to make the deal")
                .withColorResource(R.color.md_indigo_500)
                .withImage(R.drawable.competitors)
        );

        return result;
    }

}
