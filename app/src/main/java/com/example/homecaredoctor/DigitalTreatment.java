package com.example.homecaredoctor;

import java.util.ArrayList;

public class DigitalTreatment {
    ArrayList<Prescription> prescriptions = new ArrayList<>();
    User user;
    private int valid = 1;

    DigitalTreatment(User user) {
        prescriptions.add(new Prescription());
        this.user = user;
    }

    public String getName() {
        return user.getName();
    }

    public int getAge() {
        return user.getAge();
    }
}
