package com.example.homecaredoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DigitalTreatmentActivity extends AppCompatActivity {
    RecyclerView prescriptionsRecyclerView;
    PrescriptionAdapter adapter;
    ArrayList<Prescription> prescriptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_treatment);
        prescriptionsRecyclerView = findViewById(R.id.prescription_recyclerview);
        prescriptions.add(new Prescription());
        adapter = new PrescriptionAdapter(this, prescriptions);
        prescriptionsRecyclerView.setAdapter(adapter);
        prescriptionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
