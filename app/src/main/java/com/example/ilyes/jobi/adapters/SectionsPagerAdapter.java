package com.example.ilyes.jobi.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ilyes.jobi.fragments.ListClientsPostsFragment;
import com.example.ilyes.jobi.fragments.ListWorkerFragment;
import com.example.ilyes.jobi.models.Post;
import com.example.ilyes.jobi.models.Worker;

import java.util.List;

/**
 * Created by ilyes on 05/12/15.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    List<Worker> workers;
    List<Post> posts;
    long acctualUserId;
    String userType;


    public SectionsPagerAdapter(FragmentManager fm, List<Worker> workers, List<Post> posts, long acctualUserId, String userType) {
        super(fm);
        this.workers = workers;
        this.posts = posts;
        this.acctualUserId = acctualUserId;
        this.userType = userType;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return new ListWorkerFragment(workers);
            case 1:
                return new ListClientsPostsFragment(posts, acctualUserId, userType);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Workers";
            case 1:
                return "Clients";
        }
        return null;
    }
}