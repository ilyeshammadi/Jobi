package com.example.ilyes.jobi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ilyes.jobi.fragment.ListClientsFragment;
import com.example.ilyes.jobi.fragment.ListWorkerFragment;
import com.example.ilyes.jobi.model.Client;
import com.example.ilyes.jobi.model.Worker;

import java.util.List;

/**
 * Created by ilyes on 05/12/15.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    List<Worker> workers;
    List<Client> clients;


    public SectionsPagerAdapter(FragmentManager fm, List<Worker> workers, List<Client> clients) {
        super(fm);
        this.workers = workers;
        this.clients = clients;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return new ListWorkerFragment(workers);
            case 1:
                return new ListClientsFragment();
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