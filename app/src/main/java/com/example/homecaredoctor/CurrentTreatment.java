package com.example.homecaredoctor;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;


public class CurrentTreatment extends Fragment {
    private TreatmentAdapter adapter;
    private RecyclerView treatmentsRecyclerView;
    private ArrayList<DigitalTreatment> treatmentsList = new ArrayList<>();

    public CurrentTreatment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String userId = getArguments().getString("userId");
        View root = inflater.inflate(R.layout.fragment_treatment_history, container, false);
        treatmentsRecyclerView = root.findViewById(R.id.digital_treatment_recyclerview);
        adapter = new TreatmentAdapter(getContext(), treatmentsList);
        treatmentsRecyclerView.setAdapter(adapter);
        treatmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        new FirebaseDatabaseAdapter();
        Log.d("USER", userId);
        final ArrayList<String> currentTreatmentIds = new ArrayList<>();
        FirebaseDatabaseAdapter.database.getReference("doctors").child(userId).child("treatments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot entry : dataSnapshot.getChildren()) {
                    currentTreatmentIds.add(entry.getValue(String.class));
                    Log.d("TREATMENT", entry.getValue(String.class));
                }
                for (String currentTreatmentId : currentTreatmentIds) {
                    FirebaseDatabaseAdapter.database.getReference("digital_treatment").child(currentTreatmentId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            DigitalTreatment t = new DigitalTreatment();
                            String doctorId = dataSnapshot.child("doctor_id").getValue(String.class);
                            t.setDoctor_id(dataSnapshot.child("doctor_id").getValue(String.class));
                            t.setName(dataSnapshot.child("name").getValue(String.class));
                            t.setUser_id(dataSnapshot.child("user_id").getValue(String.class));
                            t.setUsername(dataSnapshot.child("username").getValue(String.class));
                            for (DataSnapshot data : dataSnapshot.child("prescriptions").getChildren()) {
                                Prescription p = new Prescription();
                                Log.d("BANGTAMMMMMMM", data.getKey());
                                p.setDate(data.child("date").getValue(Long.class));
                                p.setNote(data.child("note").getValue(String.class));
                                for (DataSnapshot pillsData: data.child("pillList").getChildren()) {
                                    Pill ppp = new Pill();
                                    ppp.setPillId(pillsData.child("pillId").getValue(String.class));
                                    ArrayList<Integer> pppp = new ArrayList<>();
                                    for (DataSnapshot ss : pillsData.child("taken").getChildren())
                                        pppp.add(ss.getValue(Integer.class));
                                    ppp.setTaken(pppp);
                                    p.pillList.add(ppp);
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
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for (int i = 0; i < 5; ++i)
        //    treatmentsList.add(new DigitalTreatment(new User("Dien Tran " + Integer.toString(i), 21+i)));

        return root;
    }
}
