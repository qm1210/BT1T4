package com.example.bt1t4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1t4.database.AppDatabase;
import com.example.bt1t4.database.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etRegUsername = findViewById(R.id.etRegUsername);
        EditText etRegPassword = findViewById(R.id.etRegPassword);
        EditText etRegConfirmPassword = findViewById(R.id.etRegConfirmPassword);
        Button btnRegisterAction = findViewById(R.id.btnRegisterAction);
        TextView tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnRegisterAction.setOnClickListener(v -> {
            String username = etRegUsername.getText().toString().trim();
            String password = etRegPassword.getText().toString().trim();
            String confirmPass = etRegConfirmPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPass)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.username = username;
            user.password = password;

            AppDatabase.getInstance(this).appDao().insertUser(user);
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });

        tvBackToLogin.setOnClickListener(v -> finish());
    }
}
