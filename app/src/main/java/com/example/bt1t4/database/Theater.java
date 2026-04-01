package com.example.bt1t4.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "theaters")
public class Theater {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String location;
}
