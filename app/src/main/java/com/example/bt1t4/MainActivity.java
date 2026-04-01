package com.example.bt1t4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnMovies = findViewById(R.id.btnMovies);
        Button btnTheaters = findViewById(R.id.btnTheaters);
        Button btnShowtimes = findViewById(R.id.btnShowtimes);

        btnLogin.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        btnMovies.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MovieListActivity.class)));
        btnTheaters.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TheaterListActivity.class)));
        btnShowtimes.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ShowTimeListActivity.class)));
    }
}
