package com.example.ilyes.jobi.activities;

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
import com.example.ilyes.jobi.adapters.SectionsPagerAdapter;
import com.example.ilyes.jobi.database.ClientDataSource;
import com.example.ilyes.jobi.database.WorkerDataSource;
import com.example.ilyes.jobi.models.Client;
import com.example.ilyes.jobi.models.User;
import com.example.ilyes.jobi.models.Worker;
import com.example.ilyes.jobi.others.Util;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private User actualUser;
    private TabLayout mTabLayout;

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

        mToolbar = setupToolBar();


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
                actualUser = workers.get(userId - 1);
                workers.remove(userId - 1);
                break;
            case "client":
                actualUser = clients.get(userId - 1);
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

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        // Setup the Floating Action Button
        setupFAB();

//        // Setup the Navigation Drawer
//        setupNavigation(mToolbar);

        testingTheNewNavigationDrawer();
    }

    private void testingTheNewNavigationDrawer() {

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.side_nav_bar)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(actualUser.getName())
                                .withEmail(actualUser.getEmail())
                                .withIcon(getResources()
                                        .getDrawable(android.R.drawable.sym_def_app_icon))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem itemWorker = new PrimaryDrawerItem()
                .withName(R.string.drawer_item_worker);

        PrimaryDrawerItem itemClient = new PrimaryDrawerItem()
                .withName(R.string.drawer_item_client);


        //create the drawer and remember the `Drawer` result object
        Drawer result = null;
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        itemWorker,
                        itemClient,
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.action_settings)
                ).build();

        final Drawer finalResult = result;
        result.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                // do something with the clicked item :D
                switch (position) {
                    case 1:
                        mViewPager.setCurrentItem(0);
                        finalResult.closeDrawer();
                        return true;
                    case 2:
                        mViewPager.setCurrentItem(1);
                        finalResult.closeDrawer();
                        return true;
                }
                return false;
            }
        });

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
        // Setup the mToolbar
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
