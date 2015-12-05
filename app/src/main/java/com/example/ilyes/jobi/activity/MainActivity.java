package com.example.ilyes.jobi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.adapter.SectionsPagerAdapter;
import com.example.ilyes.jobi.database.ClientDataSource;
import com.example.ilyes.jobi.database.WorkerDataSource;
import com.example.ilyes.jobi.model.Client;
import com.example.ilyes.jobi.model.Worker;
import com.example.ilyes.jobi.other.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the intent from the SignActivity
        Intent intent = getIntent();
        Toast.makeText(MainActivity.this,
                this.getLocalClassName() + intent.getStringExtra(Util.USER_TYPE_FLAG) + " id : " + intent.getStringExtra(Util.ID_FLAG),
                Toast.LENGTH_SHORT).show();

        int userId = Integer.parseInt(intent.getStringExtra(Util.ID_FLAG));
        String userType = intent.getStringExtra(Util.USER_TYPE_FLAG);

        Toolbar toolbar = setupToolBar();


        WorkerDataSource workerDataSource = new WorkerDataSource(this);
        ClientDataSource clientDataSource = new ClientDataSource(this);

        List<Worker> workers = new ArrayList<>();
        List<Client> clients = new ArrayList<>();


        // Get the worker data
        workerDataSource.open();
        workers = workerDataSource.readAll();
        workerDataSource.close();


        // Get the clients data
        clientDataSource.open();
        clients = clientDataSource.readAll();
        clientDataSource.close();

        // Remove the actual user from the array
        switch (userType) {
            case "worker":
                workers.remove(userId - 1);
                break;
            case "client":
                clients.remove(userId - 1);
                break;
            default:
                Toast.makeText(MainActivity.this, "ERROR SYSYEM", Toast.LENGTH_SHORT).show();
                break;
        }


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), workers, clients);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Setup the Floating Action Button
        setupFAB();

        // Setup the Navigation Drawer
        setupNavigation(toolbar);
    }

    private void setupNavigation(Toolbar toolbar) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private Toolbar setupToolBar() {
        // Setup the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SignActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
