package com.example.homecaredoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Fragment currentTreatment = new CurrentTreatment();
    Fragment treatmentHistory = new TreatmentHistory();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment activeFragment = currentTreatment;
    String userId;
    TextView name, prompt;
    FloatingActionButton addTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        prompt = findViewById(R.id.prompt);
        userId = getIntent().getStringExtra("userId");
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        treatmentHistory.setArguments(bundle);
        currentTreatment.setArguments(bundle);
        addTreatment = findViewById(R.id.add_button);

        addTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDigitalTreatment.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        new FirebaseDatabaseAdapter();
        FirebaseDatabaseAdapter.database.getReference("doctors").child(userId).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText((String)dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Set listener for navigation items
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.treatment:
                        fm.beginTransaction().hide(activeFragment).show(currentTreatment).commit();
                        activeFragment = currentTreatment;
                        prompt.setText("Danh sách các đơn khám trực tuyến của bác sĩ:");
                        return true;
                    case R.id.history:
                        fm.beginTransaction().hide(activeFragment).show(treatmentHistory).commit();
                        activeFragment = treatmentHistory;
                        prompt.setText("Lịch sử các đơn khám trực tuyến của bác sĩ:");
                        return true;
                }
                return false;
            }
        });

        // Add all fragments but show only maps fragment
        fm.beginTransaction().add(R.id.nav_host_fragment, currentTreatment, "1").commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, treatmentHistory, "2").hide(treatmentHistory).commit();
    }

}
