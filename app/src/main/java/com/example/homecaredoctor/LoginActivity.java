package com.example.homecaredoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    EditText username;
    EditText password;
    Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        signinButton = findViewById(R.id.submit);
        signinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseDatabaseAdapter adapter = new FirebaseDatabaseAdapter();
        FirebaseDatabaseAdapter.database.getReference().child("authentication_doctor").addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String mUsername = username.getText().toString();
        String mPassword = password.getText().toString();
        for (DataSnapshot entry : dataSnapshot.getChildren()) {
            User user = entry.getValue(User.class);
            if (user.getUsername().equals(mUsername)) {
                if (user.getPassword().equals(mPassword)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("userId", entry.getKey());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
