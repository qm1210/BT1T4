package com.example.bt1t4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "showtimes",
        foreignKeys = {
                @ForeignKey(entity = Movie.class,
                        parentColumns = "id",
                        childColumns = "movieId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Theater.class,
                        parentColumns = "id",
                        childColumns = "theaterId",
                        onDelete = ForeignKey.CASCADE)
        })
public class ShowTime {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int movieId;
    public int theaterId;
    public String startTime;
}
