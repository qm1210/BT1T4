package com.example.bt1t4.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public String duration;
}
