package com.example.bt1t4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1t4.database.AppDatabase;
import com.example.bt1t4.database.Theater;

import java.util.ArrayList;
import java.util.List;

public class TheaterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_list);

        ListView lvTheaters = findViewById(R.id.lvTheaters);
        List<Theater> theaters = AppDatabase.getInstance(this).appDao().getAllTheaters();

        List<String> names = new ArrayList<>();
        for (Theater t : theaters) {
            names.add(t.name + " - " + t.location);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        lvTheaters.setAdapter(adapter);
    }
}
