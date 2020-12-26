package com.example.homecaredoctor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder> {
    private Context context;
    private ArrayList<DigitalTreatment> digitalTreatments; // Real parking lot for adapter to track

    public TreatmentAdapter(@NonNull Context context, ArrayList<DigitalTreatment> list) {
        this.digitalTreatments = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TreatmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.digital_treatment_review, parent, false);
        return new TreatmentViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TreatmentViewHolder holder, int position) {
        holder.avatar.setImageDrawable(context.getDrawable(R.drawable.no_image));
        DigitalTreatment bindedTreatment = digitalTreatments.get(position);
        String mName = bindedTreatment.getName();
        String mAge = Integer.toString(bindedTreatment.getAge()) + " tuổi";
        holder.name.setText(mName);
        holder.age.setText(mAge);
        //if (bindedTreatment.getPlacePhotos() != null && bindedTreatment.getPlacePhotos().size() > 0) {
        //    Glide.with(context).load(bindedTreatment.getPlacePhotos().get(0)).into(holder.photo);
        //}
    }

    @Override
    public int getItemCount() {
        return digitalTreatments.size();
    }


    class TreatmentViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TreatmentAdapter adapter;
        TextView name;
        TextView age;
        ImageView avatar;

        public TreatmentViewHolder(@NonNull View itemView, TreatmentAdapter adapter) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            avatar = itemView.findViewById(R.id.avatar);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DigitalTreatment clickedItem = digitalTreatments.get(getAdapterPosition());
            Intent intent = new Intent(context, DigitalTreatmentActivity.class);
            //intent.putExtra("key", clickedItem.getKey());
            context.startActivity(intent);
        }

        private String encodeBitmap(Bitmap bitmap) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            bitmap.recycle();
            byte[] bytes = baos.toByteArray();
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        }
    }
}
