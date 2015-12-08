package com.example.ilyes.jobi.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.adapters.ClientPostAdapter;
import com.example.ilyes.jobi.models.Post;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListClientsPostsFragment extends Fragment {


    private View mRoot;
    private RecyclerView mClientPostsRV;
    private List<Post> mPosts;

    public ListClientsPostsFragment() {

    }

    @SuppressLint("ValidFragment")
    public ListClientsPostsFragment(List<Post> posts) {
        // Required empty public constructor
        mPosts = posts;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.fragment_list_clients, container, false);

        mClientPostsRV = (RecyclerView) mRoot.findViewById(R.id.list_clients_posts);
        mClientPostsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mClientPostsRV.setAdapter(new ClientPostAdapter(getActivity(), mPosts));

        return mRoot;
    }

}
