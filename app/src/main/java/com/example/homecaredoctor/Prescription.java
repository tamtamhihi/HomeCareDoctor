package com.example.homecaredoctor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Prescription {
    ArrayList<Pill> pillList = new ArrayList<>();
    LocalDateTime startDate;
    String note;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Prescription() {
        startDate = LocalDateTime.now();
        for (int i = 0; i < 3; ++i)
            pillList.add(new Pill());
        this.note = "Ăn cơm";
    }

    public Prescription(LocalDateTime dateTime) {
        this.startDate = dateTime;
        for (int i = 0; i < 3; ++i)
            pillList.add(new Pill());
    }

    public Prescription(ArrayList<Pill> pillList) {
        this.pillList = pillList;
    }

    public ArrayList<Pill> getPillList() {
        return pillList;
    }

    public String getDate() {
        return startDate.toString();
    }
}
