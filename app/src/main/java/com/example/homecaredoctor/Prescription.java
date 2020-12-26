package com.example.homecaredoctor;

import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    ArrayList<Pill> pillList = new ArrayList<>();
    Date startDate;

    public Prescription() {
        for (int i = 0; i < 3; ++i)
            pillList.add(new Pill());
    }

    public Prescription(ArrayList<Pill> pillList) {
        this.pillList = pillList;
    }
}
