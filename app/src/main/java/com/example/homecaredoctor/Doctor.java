package com.example.homecaredoctor;

import java.util.ArrayList;

public class Doctor {
    ArrayList<DigitalTreatment> treatments;
    private String name;
    private String description;
    private String major;

    Doctor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
