package com.example.homecaredoctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PillAddAdapter extends RecyclerView.Adapter<PillAddAdapter.PillAddViewHolder> {
    private Context context;
    private ArrayList<Pill> pills = new ArrayList<>();

    public PillAddAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public PillAddAdapter.PillAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.digital_treatment_review, parent, false);
        return new PillAddViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PillAddAdapter.PillAddViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PillAddViewHolder extends RecyclerView.ViewHolder {
        PillAddAdapter adapter;
        AutoCompleteTextView pillName;
        CheckBox morning, afternoon, evening;
        Pill pill;

        public PillAddViewHolder(@NonNull View itemView, PillAddAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            pillName = itemView.findViewById(R.id.pill_name);
            morning = itemView.findViewById(R.id.morning);
            afternoon = itemView.findViewById(R.id.afternoon);
            evening = itemView.findViewById(R.id.evening);
        }
    }
}
