package com.example.ilyes.jobi.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.database.ClientDataSource;
import com.example.ilyes.jobi.models.Address;
import com.example.ilyes.jobi.models.Client;
import com.example.ilyes.jobi.patterns.ClientBuilder;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class SignUpClientActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    AutoCompleteTextView mNameClientET;

    @NotEmpty
    @Email
    AutoCompleteTextView mEmailClientET;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    AutoCompleteTextView mPasswordClientET;

    @ConfirmPassword
    AutoCompleteTextView mPasswordConfirmClientET;

    @NotEmpty
    AutoCompleteTextView mNumeroTelephoneClientET;

    @NotEmpty
    @Length(max = 10)
    AutoCompleteTextView mStreetClientET;

    @NotEmpty
    @Length(max = 10)
    AutoCompleteTextView mCityClientET;

    @NotEmpty
    @Length(max = 10)
    AutoCompleteTextView mCountryClientET;

    FloatingActionButton mConfirmFab;

    Validator validator;

    ClientDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_client);


        // Get ref to the views
        mNameClientET = (AutoCompleteTextView) findViewById(R.id.name_client_et);
        mEmailClientET = (AutoCompleteTextView) findViewById(R.id.email_client_et);
        mPasswordClientET = (AutoCompleteTextView) findViewById(R.id.password_client_et);
        mPasswordConfirmClientET = (AutoCompleteTextView) findViewById(R.id.password_confirm_client_et);
        mNumeroTelephoneClientET = (AutoCompleteTextView) findViewById(R.id.numero_tel_client_et);
        mStreetClientET = (AutoCompleteTextView) findViewById(R.id.street_client_et);
        mCityClientET = (AutoCompleteTextView) findViewById(R.id.city_client_et);
        mCountryClientET = (AutoCompleteTextView) findViewById(R.id.country_client_et);

        mConfirmFab = (FloatingActionButton) findViewById(R.id.confirm_sigup_client_fab);
        validator = new Validator(this);
        validator.setValidationListener(this);

        dataSource = new ClientDataSource(this);

        mConfirmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

    }

    @Override
    public void onValidationSucceeded() {

        Client client = new ClientBuilder()
                .setName(mNameClientET.getText().toString())
                .setEmail(mEmailClientET.getText().toString())
                .setPassword(mPasswordClientET.getText().toString())
                .setNumeroTel(mNumeroTelephoneClientET.getText().toString())
                .setAddress(new Address(
                        mCountryClientET.getText().toString(),
                        mCityClientET.getText().toString(),
                        mStreetClientET.getText().toString()
                ))
                .build();

        dataSource.open();

        if (!dataSource.isUserRegistred(client.getEmail())) {
            dataSource.create(client);
            finish();
        } else {
            Toast.makeText(SignUpClientActivity.this, "This email is already in use by another user", Toast.LENGTH_SHORT).show();
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
