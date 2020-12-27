package com.example.homecaredoctor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.Map;

public class FirebaseDatabaseAdapter {
    static FirebaseDatabase database = null;
    static DatabaseReference ref;

    public FirebaseDatabaseAdapter() {
        if (database == null)
            database = FirebaseDatabase.getInstance("https://homecare-9d58b-default-rtdb.firebaseio.com/");
    }

    public String verifyUser(final String username, final String password) {
        final String[] userId = {""};

        return userId[0];
    }


}
