package com.example.ilyes.jobi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.database.WorkerDataSource;
import com.example.ilyes.jobi.models.Worker;
import com.example.ilyes.jobi.others.Util;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WorkerDetailActivity extends AppCompatActivity {

    @InjectView(R.id.worker_name_tv)
    TextView mNameTV;

    @InjectView(R.id.worker_email_tv)
    TextView mEmailTV;

    @InjectView(R.id.worker_phone_tv)
    TextView mPhoneTV;

    @InjectView(R.id.worker_address_tv)
    TextView mAddressTV;

    @InjectView(R.id.worker_exp_tv)
    TextView mExpTV;

    @InjectView(R.id.worker_function_tv)
    TextView mFunctionTV;


    @InjectView(R.id.worker_function_iv)
    ImageView mFunctionImage;

    Worker acctualWorker;
    WorkerDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_detail);


        // Using the Butter Kinfe library
        ButterKnife.inject(this);

        // Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Setup the Floatin Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setup the database
        dataSource = new WorkerDataSource(this);

        // Get the intent that was send by the MainActivity
        Intent intent = getIntent();
        long id = Integer.parseInt(intent.getStringExtra(Util.ID_FLAG));

        dataSource.open();
        acctualWorker = dataSource.read(id);
        dataSource.close();


        // Put the worker data into the Text Views
        mNameTV.setText(acctualWorker.getName());
        mEmailTV.setText(acctualWorker.getEmail());
        mPhoneTV.setText(acctualWorker.getNumeroTel());
        mAddressTV.setText(acctualWorker.getAddress().printAddress());
        mExpTV.setText(acctualWorker.getExpYears() + " ans");
        mFunctionTV.setText(acctualWorker.getFunction());
        mFunctionImage.setImageResource(Util.getPictureAsResource(acctualWorker.getFunction()));
    }
}
