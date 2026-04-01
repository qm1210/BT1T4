package com.example.bt1t4;

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
            movieTitles.add(m.title + " (" + m.duration + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieTitles);
        lvMovies.setAdapter(adapter);
    }
}
