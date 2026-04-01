package com.example.bt1t4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tickets",
        foreignKeys = {
                @ForeignKey(entity = ShowTime.class,
                        parentColumns = "id",
                        childColumns = "showTimeId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int showTimeId;
    public int userId;
    public String seatNumber;
    public double price;
}
