package com.example.bt1t4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1t4.database.AppDatabase;
import com.example.bt1t4.database.Movie;
import com.example.bt1t4.database.Theater;

import java.util.ArrayList;
import java.util.List;

public class TheaterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_list);

        int movieId = getIntent().getIntExtra("MOVIE_ID", -1);
        Movie movie = AppDatabase.getInstance(this).appDao().getMovieById(movieId);

        TextView tvTitle = findViewById(android.R.id.text1); // Reuse or use a header
        setTitle("Rạp chiếu cho: " + (movie != null ? movie.title : "Unknown"));

        ListView lvTheaters = findViewById(R.id.lvTheaters);
        List<Theater> theaters = AppDatabase.getInstance(this).appDao().getTheatersByMovie(movieId);

        List<String> names = new ArrayList<>();
        for (Theater t : theaters) {
            names.add(t.name + " - " + t.location);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        lvTheaters.setAdapter(adapter);

        lvTheaters.setOnItemClickListener((parent, view, position, id) -> {
            Theater selectedTheater = theaters.get(position);
            Intent intent = new Intent(TheaterListActivity.this, ShowTimeListActivity.class);
            intent.putExtra("MOVIE_ID", movieId);
            intent.putExtra("THEATER_ID", selectedTheater.id);
            startActivity(intent);
        });
    }
}
