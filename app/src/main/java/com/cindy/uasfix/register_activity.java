package com.cindy.uasfix;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class register_activity extends AppCompatActivity {
    private EditText emailRegister, passwordRegister;
    private Button registerButton;
    private TextView loginRedirect;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailRegister = findViewById(R.id.emailRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        registerButton = findViewById(R.id.registerButton);
        loginRedirect = findViewById(R.id.loginRedirect);
        auth = FirebaseAuth.getInstance();

        // Tombol Register
        registerButton.setOnClickListener(view -> registerUser());

        // Redirect ke halaman Login
        loginRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(register_activity.this, login.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        String email = emailRegister.getText().toString().trim();
        String password = passwordRegister.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Isi semua field!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(register_activity.this, login.class));
            } else {
                Toast.makeText(this, "Registrasi gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}