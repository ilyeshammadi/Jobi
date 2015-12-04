package com.example.ilyes.jobi.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.database.WorkerDataSource;
import com.example.ilyes.jobi.model.Address;
import com.example.ilyes.jobi.model.Worker;
import com.example.ilyes.jobi.pattern.WorkerBuilder;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.List;

public class SignUpWorkerActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    AutoCompleteTextView mNameWorkerET;

    @NotEmpty
    @Email
    AutoCompleteTextView mEmailWorkerET;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    AutoCompleteTextView mPasswordrkerET;

    @ConfirmPassword
    AutoCompleteTextView mPasswordRepeatWorkerET;

    @NotEmpty
    AutoCompleteTextView mBirthDateWorkerET;

    @NotEmpty
    AutoCompleteTextView mNumeroTelephoneWorkerET;

    @NotEmpty
    AutoCompleteTextView mExpYearWorkerET;


    @NotEmpty
    @Length(max = 10)
    AutoCompleteTextView mStreetWorkerET;

    @NotEmpty
    @Length(max = 10)
    AutoCompleteTextView mCityWorkerET;

    @NotEmpty
    @Length(max = 10)
    AutoCompleteTextView mCountryWorkerET;

    FloatingActionButton mConfirmFab;

    Validator validator;

    WorkerDataSource dataSource;

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_worker);

        // Get ref to the views
        mNameWorkerET = (AutoCompleteTextView) findViewById(R.id.name_worker_et);
        mEmailWorkerET = (AutoCompleteTextView) findViewById(R.id.email_worker_et);
        mPasswordrkerET = (AutoCompleteTextView) findViewById(R.id.password_worker_et);
        mPasswordRepeatWorkerET = (AutoCompleteTextView) findViewById(R.id.password_confirm_worker_et);
        mBirthDateWorkerET = (AutoCompleteTextView) findViewById(R.id.birth_date_worker_et);
        mNumeroTelephoneWorkerET = (AutoCompleteTextView) findViewById(R.id.numero_tel_worker_et);
        mExpYearWorkerET = (AutoCompleteTextView) findViewById(R.id.exp_year_worker_et);
        mStreetWorkerET = (AutoCompleteTextView) findViewById(R.id.street_worker_et);
        mCityWorkerET = (AutoCompleteTextView) findViewById(R.id.city_worker_et);
        mCountryWorkerET = (AutoCompleteTextView) findViewById(R.id.country_worker_et);

        mConfirmFab = (FloatingActionButton) findViewById(R.id.confirm_sigup_worker_fab);
        validator = new Validator(this);
        validator.setValidationListener(this);

        dataSource = new WorkerDataSource(this);


        mConfirmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }

        });



        // When the Birht date Edit text is touched a date picker diaolg is displayed
        // and the user can pick a date
        mBirthDateWorkerET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int year = mcurrentDate.get(Calendar.YEAR);
                int month = mcurrentDate.get(Calendar.MONTH);
                int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(SignUpWorkerActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                // Print the result of the date picker in the edit text
                                mBirthDateWorkerET.setText(String.format("%d/%d/%d", dayOfMonth, monthOfYear, year));

                                // Copie the result of the date in the fields
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;
                            }
                        }, year, month, day);

                datePicker.setTitle("Select date");
                datePicker.show();
            }
        });

    }

    @Override
    public void onValidationSucceeded() {


        Worker worker = new WorkerBuilder()
                .setName(mNameWorkerET.getText().toString())
                .setEmail(mEmailWorkerET.getText().toString())
                .setPassword(mPasswordrkerET.getText().toString())
                .setBirthDate(new DateTime(mYear, mMonth, mDay, 0, 0))
                .setNumeroTel(mNumeroTelephoneWorkerET.getText().toString())
                // TODO add a spinner to choice the function
                .setFunction("Plombier")
                .setExpYears(Integer.parseInt(mExpYearWorkerET.getText().toString()))
                .setAddress(new Address(
                        mCountryWorkerET.getText().toString(),
                        mCityWorkerET.getText().toString(),
                        mStreetWorkerET.getText().toString()
                ))
                .build();

        dataSource.open();


        if (!dataSource.isUserRegistred(worker.getEmail())) {
            dataSource.create(worker);
            finish();
        } else {
            Toast.makeText(SignUpWorkerActivity.this, "This email is already in use by another user", Toast.LENGTH_SHORT).show();
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
