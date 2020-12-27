package com.example.homecaredoctor;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class
Pill {
    private String pillId;
    private List<Integer> taken = new ArrayList<>(3);
    public String[] time = {"sáng", "trưa", "chiều"};
    String pillName, counter;

    public Pill() {
    }

    public Pill(String pillId, String pillName, ArrayList<Integer> taken) {
        this.pillId = pillId;
        this.taken = taken;
        this.pillName = pillName;
        this.counter = "viên";
    }

    public void setTaken(ArrayList<Integer> taken) {
        this.taken = taken;
    }
    public List<Integer> getTaken() {
        return this.taken;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    // TODO: get pillname and counter type from this.pillId
    public String getPillName() {
        /*
        FirebaseDatabaseAdapter.database.getReference("pill_data").child(pillId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int c = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (c == 0) {
                        counter = data.getValue(String.class);
                        c++;
                    } else
                        pillName = data.getValue(String.class);
                }
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
         */
        return this.pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getCounter() {
        return counter;
    }

    public String toString() {
        String counter = getCounter();
        String ans = new String();
        for (int i = 0; i < 3; ++i) {
            if (taken.get(i) > 0)
                ans += Integer.toString(taken.get(i)) + " " + counter + " buổi " + time[i] + "    ";
        }
        return ans;
    }

    public String getPillId() {
        return pillId;
    }

    public void setPillId(String pillId) {
        this.pillId = pillId;
    }
}
