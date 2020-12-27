package com.example.homecaredoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDigitalTreatment extends AppCompatActivity {
    DigitalTreatment treatment;
    AutoCompleteTextView username;
    TextView description;
    Button submitButton, addButton, okButton;
    LinearLayout addPill;
    AutoCompleteTextView addPillName;
    CheckBox morning, afternoon, evening;
    ArrayAdapter<String> usernames = null;
    ArrayAdapter<String> pillNames = null;
    RecyclerView addedPills;
    PillAdapter adapter;
    ArrayList<Pill> pillArrayList = new ArrayList<>();
    Map<String, String> pillNameToId = new HashMap<>();
    Map<String, String> userNameToId = new HashMap<>();
    String doctorId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_digital_treatment);
        doctorId = getIntent().getStringExtra("userId");
        username = findViewById(R.id.user);
        description = findViewById(R.id.note);
        submitButton = findViewById(R.id.submit);
        addButton = findViewById(R.id.add_button);
        okButton = findViewById(R.id.ok);
        addPill = findViewById(R.id.add_pill);
        morning = findViewById(R.id.morning);
        afternoon = findViewById(R.id.afternoon);
        evening = findViewById(R.id.evening);
        addPillName = findViewById(R.id.pill_name);
        addedPills = findViewById(R.id.pills);
        adapter = new PillAdapter(this, pillArrayList);
        addedPills.setAdapter(adapter);
        addedPills.setLayoutManager(new LinearLayoutManager(this));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        usernames = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        new FirebaseDatabaseAdapter();
        FirebaseDatabaseAdapter.database.getReference("authentication").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot suggestion : dataSnapshot.getChildren()) {
                    DataSnapshot data = suggestion.child("username");
                    usernames.add(data.getValue(String.class));
                    userNameToId.put(data.getValue(String.class), suggestion.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        username.setAdapter(usernames);

        pillNames = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        FirebaseDatabaseAdapter.database.getReference("pill_data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot suggestion : dataSnapshot.getChildren()) {
                    DataSnapshot pName = suggestion.child("name");
                    pillNames.add(pName.getValue(String.class));
                    pillNameToId.put(pName.getValue(String.class), pName.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        addPillName.setAdapter(pillNames);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mName = username.getText().toString();
                Log.d("Hiiiiiiiiiiiiiiiiiiiiiiiii", "hh" + userNameToId.get(mName) + "hh");
                FirebaseDatabaseAdapter.database.getReference("users").child(userNameToId.get(mName)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        DatabaseReference ref = FirebaseDatabaseAdapter.database.getReference("digital_treatment").push();
                        String treatmentId = ref.getKey();
                        ref.child("age").setValue(user.getAge());
                        ref.child("doctor_id").setValue(doctorId);
                        ref.child("name").setValue(user.getName());
                        ref.child("username").setValue(username.getText().toString());
                        ref.child("user_id").setValue(userNameToId.get(mName));
                        ref = ref.child("prescriptions");
                        ref = ref.push();
                        String key = ref.getKey();
                        ref.child("date").setValue(Calendar.getInstance().getTimeInMillis());
                        ref.child("note").setValue(description.getText().toString());
                        ref = ref.child("pillList");
                        for (Pill p : pillArrayList) {
                            DatabaseReference r = ref;
                            String kk = r.push().getKey();
                            r.child("pillId").setValue(p.getPillId());
                            r.child("taken").setValue(new ArrayList<>(p.getTaken()));
                        }
                        FirebaseDatabaseAdapter.database.getReference("users").child(userNameToId.get(mName)).child("treatments").push().setValue(treatmentId);
                        FirebaseDatabaseAdapter.database.getReference("doctors").child(doctorId).child("treatments").push().setValue(treatmentId);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(AddDigitalTreatment.this, MainActivity.class);
                intent.putExtra("userId", doctorId);
                startActivity(intent);
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addPillName.getText().toString().isEmpty()) {
                    Toast.makeText(AddDigitalTreatment.this, "Vui lòng nhập tên thuốc.", Toast.LENGTH_SHORT).show();
                } else if (!morning.isChecked() && !evening.isChecked() && !afternoon.isChecked()) {
                    Toast.makeText(AddDigitalTreatment.this, "Hãy chọn ít nhất 1 buổi uống", Toast.LENGTH_SHORT).show();
                } else {
                    String pillName = addPillName.getText().toString();
                    int[] taken = new int[3];
                    if (morning.isChecked())
                        taken[0] = 1;
                    if (afternoon.isChecked())
                        taken[1] = 1;
                    if (evening.isChecked())
                        taken[2] = 1;
                    ArrayList<Integer> pl = new ArrayList<>();
                    pl.add(taken[0]);
                    pl.add(taken[1]);
                    pl.add(taken[2]);
                    pillArrayList.add(new Pill(pillNameToId.get(pillName), pillName, pl));
                    adapter.notifyDataSetChanged();
                    morning.setChecked(false);
                    evening.setChecked(false);
                    afternoon.setChecked(false);
                    pillNameToId.clear();
                    addPillName.getText().clear();
                    addPill.setVisibility(View.GONE);
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPill.setVisibility(View.VISIBLE);
            }
        });
    }
}
