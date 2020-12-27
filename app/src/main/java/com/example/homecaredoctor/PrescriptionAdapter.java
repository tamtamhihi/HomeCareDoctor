package com.example.homecaredoctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.PrescriptionViewHolder> {
    private Context context;
    private ArrayList<Prescription> prescriptions;

    public PrescriptionAdapter(Context context, ArrayList<Prescription> prescriptions) {
        this.context = context;
        this.prescriptions = prescriptions;
    }

    @NonNull
    @Override
    public PrescriptionAdapter.PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.prescription, parent, false);
        return new PrescriptionViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionAdapter.PrescriptionViewHolder holder, int position) {
        Prescription bindedPrescription = prescriptions.get(position);
        holder.pillAdapter = new PillAdapter(context, bindedPrescription.getPillList());
        holder.pillList.setAdapter(holder.pillAdapter);
        holder.pillList.setLayoutManager(new LinearLayoutManager(context));
        holder.date.setText(bindedPrescription.getStartDate());
    }

    @Override
    public int getItemCount() {
        return prescriptions.size();
    }

    public class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        PrescriptionAdapter adapter;
        TextView date;
        RecyclerView pillList;
        PillAdapter pillAdapter;

        public PrescriptionViewHolder(@NonNull View itemView, PrescriptionAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            date = itemView.findViewById(R.id.date);
            pillList = itemView.findViewById(R.id.pill_list);
        }
    }
}
