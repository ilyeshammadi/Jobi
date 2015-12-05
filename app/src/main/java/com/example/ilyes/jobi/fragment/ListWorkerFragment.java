package com.example.ilyes.jobi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.adapter.WorkerAdapter;
import com.example.ilyes.jobi.model.Address;
import com.example.ilyes.jobi.model.Worker;
import com.example.ilyes.jobi.pattern.WorkerBuilder;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListWorkerFragment extends Fragment {


    private View mRoot;
    private RecyclerView mWorkerListRV;
    private List<Worker> workers = new ArrayList<>();

    public ListWorkerFragment() {

    }

    public ListWorkerFragment(List<Worker> workers) {
        // Required empty public constructor
        dummyData();
        // TODO: 05/12/15 get the data from the database in the MainActivity and pase it to this fragment

        this.workers = workers;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.fragment_list_worker, container, false);

        mWorkerListRV = (RecyclerView) mRoot.findViewById(R.id.list_workers_rv);
        mWorkerListRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWorkerListRV.setAdapter(new WorkerAdapter(workers));
        return mRoot;
    }


    public void dummyData() {
        Worker worker = new WorkerBuilder()
                .setName("toto")
                .setEmail("toto@toto.com")
                .setPassword("a123466")
                .setBirthDate(new DateTime())
                .setNumeroTel("123456")
                .setExpYears(2)
                .setAddress(new Address("dd", "dd", "dd"))
                .build();

        workers.add(worker);
    }


}
