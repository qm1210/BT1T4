package com.example.bt1t4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1t4.database.AppDatabase;
import com.example.bt1t4.database.Movie;
import com.example.bt1t4.database.ShowTime;
import com.example.bt1t4.database.Theater;

import java.util.ArrayList;
import java.util.List;

public class ShowTimeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_time_list);

        int movieId = getIntent().getIntExtra("MOVIE_ID", -1);
        int theaterId = getIntent().getIntExtra("THEATER_ID", -1);

        Movie movie = AppDatabase.getInstance(this).appDao().getMovieById(movieId);
        Theater theater = AppDatabase.getInstance(this).appDao().getTheaterById(theaterId);

        if (movie != null && theater != null) {
            setTitle("Lịch chiếu: " + movie.title + " tại " + theater.name);
        }

        ListView lvShowTimes = findViewById(R.id.lvShowTimes);
        List<ShowTime> showTimes = AppDatabase.getInstance(this).appDao()
                .getShowTimesByMovieAndTheater(movieId, theaterId);

        List<String> details = new ArrayList<>();
        for (ShowTime s : showTimes) {
            details.add("Thời gian: " + s.startTime);
        }

        if (details.isEmpty()) {
            details.add("Không có suất chiếu nào.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, details);
        lvShowTimes.setAdapter(adapter);
    }
}
