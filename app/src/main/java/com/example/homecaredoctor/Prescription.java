package com.example.homecaredoctor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prescription {
    List<Pill> pillList = new ArrayList<>();
    private Date startDate;
    private long date;
    private String note;

    @RequiresApi(api = Build.VERSION_CODES.O)

    public Prescription() {

    }

    public Prescription(long date, String note, ArrayList<Pill> pillList) {
        this.date = date;
        this.note = note;
        this.pillList = pillList;
    }
    public String getStartDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(date));
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Pill> getPillList() {
        return pillList;
    }

    public void setPillList(ArrayList<Pill> pillList) {
        this.pillList = pillList;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
