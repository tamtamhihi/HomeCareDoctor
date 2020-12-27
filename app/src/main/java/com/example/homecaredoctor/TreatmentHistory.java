package com.example.homecaredoctor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TreatmentHistory extends Fragment {
    private TreatmentAdapter adapter;
    private RecyclerView treatmentsRecyclerView;
    private ArrayList<DigitalTreatment> treatmentsList = new ArrayList<>();

    public TreatmentHistory() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String userId = getArguments().getString("userId");
        new FirebaseDatabaseAdapter();
        final ArrayList<String> historyTreatmentIds = new ArrayList<>();
        try {
            FirebaseDatabaseAdapter.database.getReference("doctors").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("history_treatments")) {
                        dataSnapshot = dataSnapshot.child("history_treatments");
                        for (DataSnapshot entry : dataSnapshot.getChildren()) {
                            historyTreatmentIds.add(entry.getValue(String.class));
                        }
                        for (String historyTreatmentId : historyTreatmentIds) {
                            FirebaseDatabaseAdapter.database.getReference("digital_treatment").child(historyTreatmentId).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    DigitalTreatment t = dataSnapshot.getValue(DigitalTreatment.class);
                                    for (DataSnapshot data : dataSnapshot.child("prescriptions").getChildren()) {
                                        Prescription p = data.getValue(Prescription.class);
                                        for (DataSnapshot pillsData: data.child("pillList").getChildren()) {
                                            p.pillList.add(pillsData.getValue(Pill.class));
                                        }
                                        t.prescriptions.add(p);
                                    }
                                    treatmentsList.add(t);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        View root = inflater.inflate(R.layout.fragment_treatment_history, container, false);
        treatmentsRecyclerView = root.findViewById(R.id.digital_treatment_recyclerview);
        adapter = new TreatmentAdapter(getContext(), treatmentsList);

        //for (int i = 0; i < 5; ++i)
        //    treatmentsList.add(new DigitalTreatment(new User("Dien Tran" + Integer.toString(i+1), 20+i)));
        treatmentsRecyclerView.setAdapter(adapter);
        treatmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}
