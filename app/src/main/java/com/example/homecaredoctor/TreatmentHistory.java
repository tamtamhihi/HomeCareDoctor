package com.example.homecaredoctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class TreatmentHistory extends Fragment {
    private TreatmentAdapter adapter;
    private RecyclerView treatmentsRecyclerView;
    private ArrayList<DigitalTreatment> treatmentsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_treatment_history, container, false);
        treatmentsRecyclerView = root.findViewById(R.id.digital_treatment_recyclerview);
        adapter = new TreatmentAdapter(getContext(), treatmentsList);

        for (int i = 0; i < 5; ++i)
            treatmentsList.add(new DigitalTreatment(new User("Dien Tran" + Integer.toString(i+1), 20+i)));
        treatmentsRecyclerView.setAdapter(adapter);
        treatmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}
