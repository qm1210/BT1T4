package com.example.bt1t4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1t4.database.AppDatabase;
import com.example.bt1t4.database.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLoginAction = findViewById(R.id.btnLoginAction);

        btnLoginAction.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            User user = AppDatabase.getInstance(this).appDao().login(username, password);
            if (user != null) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
