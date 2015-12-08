package com.example.ilyes.jobi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ilyes.jobi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListClientsFragment extends Fragment {


    private View mRoot;

    public ListClientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.fragment_list_clients, container, false);



        return mRoot;
    }

}
