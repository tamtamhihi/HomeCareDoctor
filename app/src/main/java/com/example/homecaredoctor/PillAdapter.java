package com.example.homecaredoctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.PillViewHolder> {
    private Context context;
    private ArrayList<Pill> pillList;

    public PillAdapter(Context context, ArrayList<Pill> pillList) {
        this.context = context;
        this.pillList = pillList;
    }

    @NonNull
    @Override
    public PillAdapter.PillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.pill, parent, false);
        return new PillViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PillAdapter.PillViewHolder holder, int position) {
        holder.name.setText(pillList.get(position).getPillName());
        Pill bindedPill = pillList.get(position);
        String name = bindedPill.getPillName();
        String description = bindedPill.toString();
        holder.stt.setText(Integer.toString(position+1));
        holder.name.setText(name);
        holder.description.setText(description);
    }

    @Override
    public int getItemCount() {
        return pillList.size();
    }

    public class PillViewHolder extends RecyclerView.ViewHolder {
        PillAdapter adapter;
        TextView stt, name, description;
        public PillViewHolder(@NonNull View itemView, PillAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            stt = itemView.findViewById(R.id.stt);
            name = itemView.findViewById(R.id.pill_name);
            description= itemView.findViewById(R.id.description);
        }

    }
}
