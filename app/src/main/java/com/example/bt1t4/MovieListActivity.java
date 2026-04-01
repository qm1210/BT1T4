package com.example.bt1t4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1t4.database.AppDatabase;
import com.example.bt1t4.database.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ListView lvMovies = findViewById(R.id.lvMovies);
        List<Movie> movies = AppDatabase.getInstance(this).appDao().getAllMovies();
        
        List<String> movieTitles = new ArrayList<>();
        for (Movie m : movies) {
            // Đã bỏ phần hiển thị thời lượng (phút) theo yêu cầu
            movieTitles.add(m.title);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieTitles);
        lvMovies.setAdapter(adapter);

        lvMovies.setOnItemClickListener((parent, view, position, id) -> {
            Movie selectedMovie = movies.get(position);
            Intent intent = new Intent(MovieListActivity.this, TheaterListActivity.class);
            intent.putExtra("MOVIE_ID", selectedMovie.id);
            startActivity(intent);
        });
    }
}
