package com.example.bt1t4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1t4.database.AppDao;
import com.example.bt1t4.database.AppDatabase;
import com.example.bt1t4.database.Movie;
import com.example.bt1t4.database.ShowTime;
import com.example.bt1t4.database.Theater;
import com.example.bt1t4.database.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initSampleData();

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLoginAction = findViewById(R.id.btnLoginAction);
        TextView tvRegisterLink = findViewById(R.id.tvRegisterLink);

        btnLoginAction.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            User user = AppDatabase.getInstance(this).appDao().login(username, password);
            if (user != null) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MovieListActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegisterLink.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private void initSampleData() {
        AppDao dao = AppDatabase.getInstance(this).appDao();
        new Thread(() -> {
            if (dao.getAllUsers().isEmpty()) {
                // User admin mặc định
                User user = new User();
                user.username = "admin";
                user.password = "123";
                dao.insertUser(user);

                // Thêm nhiều Movies mẫu
                String[] titles = {"Avengers: Endgame", "The Batman", "Spider-Man: No Way Home", "Inception", "Joker", "Interstellar"};
                for (String title : titles) {
                    Movie m = new Movie();
                    m.title = title;
                    m.duration = "120";
                    dao.insertMovie(m);
                }

                // Cập nhật danh sách rạp tại Hà Nội
                String[][] theaters = {
                        {"CGV Vincom Bà Triệu", "Hai Bà Trưng, Hà Nội"},
                        {"Lotte Cinema Keangnam", "Nam Từ Liêm, Hà Nội"},
                        {"BHD Star Phạm Ngọc Thạch", "Đống Đa, Hà Nội"},
                        {"Galaxy Mipec Long Biên", "Long Biên, Hà Nội"},
                        {"Trung tâm Chiếu phim Quốc gia", "Ba Đình, Hà Nội"}
                };
                for (String[] tInfo : theaters) {
                    Theater t = new Theater();
                    t.name = tInfo[0];
                    t.location = tInfo[1];
                    dao.insertTheater(t);
                }

                // Thêm Showtimes mẫu
                String[] times = {"09:00 AM", "12:30 PM", "03:45 PM", "07:00 PM", "10:15 PM"};
                
                int movieCount = 6;
                int theaterCount = 5;
                
                for (int mId = 1; mId <= movieCount; mId++) {
                    for (int tId = 1; tId <= theaterCount; tId++) {
                        ShowTime s1 = new ShowTime();
                        s1.movieId = mId;
                        s1.theaterId = tId;
                        s1.startTime = times[(mId + tId) % 5];
                        dao.insertShowTime(s1);

                        ShowTime s2 = new ShowTime();
                        s2.movieId = mId;
                        s2.theaterId = tId;
                        s2.startTime = times[(mId * tId) % 5];
                        dao.insertShowTime(s2);
                    }
                }
            }
        }).start();
    }
}
