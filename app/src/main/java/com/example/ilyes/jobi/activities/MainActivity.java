package com.example.ilyes.jobi.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.adapters.SectionsPagerAdapter;
import com.example.ilyes.jobi.database.ClientDataSource;
import com.example.ilyes.jobi.database.PostDataSource;
import com.example.ilyes.jobi.database.WorkerDataSource;
import com.example.ilyes.jobi.models.Client;
import com.example.ilyes.jobi.models.Post;
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

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private User actualUser;
    private TabLayout mTabLayout;
    private String userType;
    private int userId;
    private String workerFunction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Install CustomActivityOnCrash
        CustomActivityOnCrash.install(this);

        // Get the intent from the SignActivity
        Intent intent = getIntent();

        userId = 0;
        userType = null;

        if (intent != null) {
            userId = Integer.parseInt(intent.getStringExtra(Util.ID_FLAG));
            userType = intent.getStringExtra(Util.USER_TYPE_FLAG);
        }


        mToolbar = setupToolBar();


        WorkerDataSource workerDataSource = new WorkerDataSource(this);
        ClientDataSource clientDataSource = new ClientDataSource(this);
        PostDataSource postDataSource = new PostDataSource(this);


        List<Worker> workers = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        List<Post> posts = new ArrayList<>();

        // Get the worker data
        workerDataSource.open();
        workers = workerDataSource.readAll();
        workerDataSource.close();


        // Get the clients data
        clientDataSource.open();
        clients = clientDataSource.readAll();
        clientDataSource.close();

        // Get all the posts
        postDataSource.open();
        posts = postDataSource.readAll();
        postDataSource.close();


        // Remove the actual user from the array
        switch (userType) {
            case Util.WORKER:
                actualUser = workers.get(userId - 1);
                workerFunction = workers.get(userId - 1).getFunction();
                workers.remove(userId - 1);
                break;
            case Util.CLIENT:
                actualUser = clients.get(userId - 1);
                clients.remove(userId - 1);
                break;
            default:
                Toast.makeText(MainActivity.this, "ERROR SYSTEM", Toast.LENGTH_SHORT).show();
                break;
        }


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        // pass to the adapter the user id and the user type as a flag
        // the role of this informations here is to now how to display
        // if it's a worker there some stufs that will be hiden
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), workers, posts, userId, userType);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);


        // Setup the Navigation Drawer
        setupNavigationDrawer();


        // Say hi to the user
        SharedPreferences settings = getPreferences(MODE_PRIVATE);

        String snackbarMessage = "Hello " + actualUser.getName();

        Snackbar.make(mTabLayout, snackbarMessage, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }




    private void setupNavigationDrawer() {

        int profileImageResource;

        if (userType.equals(Util.WORKER)) {
            profileImageResource = Util.getPictureAsResource(workerFunction);
        } else {
            profileImageResource = R.drawable.client;
        }


        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.side_nav_bar)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(actualUser.getName())
                                .withEmail(actualUser.getEmail())
                                .withIcon(getResources()
                                        .getDrawable(profileImageResource))
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


            // Print a box when the user click on Sign out
            MaterialDialog dialog = new MaterialDialog.Builder(this)
                    .title("Sign out ?")
                    .positiveText("OK")
                    .negativeText("Cancel")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                            startActivity(new Intent(MainActivity.this, SignActivity.class));
                            finish();
                        }
                    })
                    .build();

            dialog.show();
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
