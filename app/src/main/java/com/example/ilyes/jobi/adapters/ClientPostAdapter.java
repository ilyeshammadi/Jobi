package com.example.ilyes.jobi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.database.ClientDataSource;
import com.example.ilyes.jobi.models.Client;
import com.example.ilyes.jobi.models.Post;

import java.util.List;

/**
 * Created by ilyes on 24/11/15.
 */
public class ClientPostAdapter extends RecyclerView.Adapter<ClientPostAdapter.MyHolder> {

    private List<Post> mData;
    private ClientDataSource dataSource;


    // Provide a suitable constructor (depends on the kind of dataset)
    public ClientPostAdapter(Context context, List<Post> data) {
        this.mData = data;
        dataSource = new ClientDataSource(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_post_list_item, parent, false);
        return new MyHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Post post = mData.get(position);
        Client client = new Client();

        dataSource.open();

        client = dataSource.read(post.getUserOwnerId());


        // Parse the data here
        holder.clientNameTV.setText(client.getName());
        holder.postTitleTV.setText(post.getTitle());
        holder.postTextTV.setText(post.getText());

        dataSource.close();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyHolder extends RecyclerView.ViewHolder {
        // Your View ref
        TextView clientNameTV;
        TextView postTitleTV;
        TextView postTextTV;

        public MyHolder(View itemView) {
            super(itemView);

            // Instance of the view here with findViewById(R.id.view_id)
            clientNameTV = (TextView) itemView.findViewById(R.id.client_name_tv);
            postTitleTV = (TextView) itemView.findViewById(R.id.client_post_title_tv);
            postTextTV = (TextView) itemView.findViewById(R.id.client_post_text_tv);
        }


    }


}
