package com.example.ilyes.jobi.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.adapters.ClientPostAdapter;
import com.example.ilyes.jobi.database.PostDataSource;
import com.example.ilyes.jobi.models.Post;
import com.example.ilyes.jobi.others.Util;
import com.gc.materialdesign.views.ButtonFloat;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListClientsPostsFragment extends Fragment {


    private View mRoot;
    private RecyclerView mClientPostsRV;
    private ButtonFloat mCreateNewPostFAB;

    private AutoCompleteTextView mPostTitleDialogET;

    private AutoCompleteTextView mPostTextDialogET;


    private List<Post> mPosts;
    private ClientPostAdapter mAdapter;

    public ListClientsPostsFragment() {

    }


    long acctualUserId;
    String userType;


    @SuppressLint("ValidFragment")
    public ListClientsPostsFragment(List<Post> posts, long acctualUserId, String userType) {
        // Required empty public constructor
        mPosts = posts;
        this.acctualUserId = acctualUserId;
        this.userType = userType;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.fragment_list_clients, container, false);


        // Get refrence and setup Receycle
        mClientPostsRV = (RecyclerView) mRoot.findViewById(R.id.list_clients_posts);
        mClientPostsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ClientPostAdapter(getActivity(), mPosts, acctualUserId);
        mClientPostsRV.setAdapter(mAdapter);


        // Get refrence and setup create new post Floatin Action Button
        mCreateNewPostFAB = (ButtonFloat) mRoot.findViewById(R.id.create_new_post_fab);

        if (userType.equals(Util.CLIENT)) {

            // Display a Dialog when the user click
            mCreateNewPostFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Create a dialog box to create a new post
                    final MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                            .title("New Post")
                            .customView(R.layout.new_post, true)
                            .positiveText("Publier")
                            .build();
                    dialog.show();


                    // Get the custom view that is displayed in the dialog box
                    final View view = dialog.getCustomView();


                    // When the user click on the Positve button get the data
                    // and close the dialog box
                    dialog.getActionButton(DialogAction.POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (view != null) {

                                PostDataSource dataSource = new PostDataSource(getActivity());
                                Post post = new Post();

                                mPostTitleDialogET = (AutoCompleteTextView) view.findViewById(R.id.post_title_et);
                                mPostTextDialogET = (AutoCompleteTextView) view.findViewById(R.id.post_text_et);

                                String postTitle = mPostTitleDialogET.getText().toString();
                                String postText = mPostTextDialogET.getText().toString();

                                boolean isPostTextNotEmpty = !postText.isEmpty() && !postText.equals(" ");
                                boolean isPostTitleNotEmpty = !postTitle.isEmpty() && !postTitle.equals(" ");


                                if (isPostTitleNotEmpty && isPostTextNotEmpty) {


                                    // Set the data into a temp post model
                                    post.setTitle(postTitle);
                                    post.setText(postText);
                                    post.setUserOwnerId(acctualUserId);

                                    // Open and create the post in the database
                                    dataSource.open();
                                    dataSource.create(post);

                                    // Retrieve all the posts
                                    mPosts = dataSource.readAll();
                                    dataSource.close();

                                    // Print all the posts in the Recycle View
                                    mAdapter = new ClientPostAdapter(getActivity(), mPosts, acctualUserId);
                                    mClientPostsRV.setAdapter(mAdapter);

                                }

                            }
                            dialog.hide();
                        }
                    });
                }
            });
        } else {
            mCreateNewPostFAB.setVisibility(View.GONE);


        }

        return mRoot;
    }

}
