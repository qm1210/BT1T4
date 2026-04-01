package com.example.bt1t4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1t4.database.AppDatabase;
import com.example.bt1t4.database.ShowTime;

import java.util.ArrayList;
import java.util.List;

public class ShowTimeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_time_list);

        ListView lvShowTimes = findViewById(R.id.lvShowTimes);
        List<ShowTime> showTimes = AppDatabase.getInstance(this).appDao().getAllShowTimes();

        List<String> details = new ArrayList<>();
        for (ShowTime s : showTimes) {
            details.add("Movie ID: " + s.movieId + " - Theater ID: " + s.theaterId + " - " + s.startTime);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, details);
        lvShowTimes.setAdapter(adapter);
    }
}
