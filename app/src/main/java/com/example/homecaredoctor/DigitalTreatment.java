package com.example.homecaredoctor;

import android.os.Build;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DigitalTreatment {
    List<Prescription> prescriptions = new ArrayList<>();
    private String doctor_id, user_id, username, name;
    private int age;

    public DigitalTreatment() {}
    @RequiresApi(api = Build.VERSION_CODES.O)
    DigitalTreatment(User user) {
        prescriptions.add(new Prescription());
    }

    public DigitalTreatment(int age, String doctorId, String name, AutoCompleteTextView username, String s, Prescription prescription) {
        this.age = age;
        this.doctor_id = doctorId;
        this.name = name;
        this.username =username.getText().toString();
        this.user_id = s;
        this.prescriptions = new ArrayList<>(prescriptions);
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(ArrayList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
