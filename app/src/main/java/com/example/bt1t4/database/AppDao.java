package com.example.bt1t4.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AppDao {
    // User
    @Insert
    void insertUser(User user);
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User login(String username, String password);

    // Movie
    @Insert
    void insertMovie(Movie movie);
    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    // Theater
    @Insert
    void insertTheater(Theater theater);
    @Query("SELECT * FROM theaters")
    List<Theater> getAllTheaters();

    // ShowTime
    @Insert
    void insertShowTime(ShowTime showTime);
    @Query("SELECT * FROM showtimes")
    List<ShowTime> getAllShowTimes();

    // Ticket
    @Insert
    void insertTicket(Ticket ticket);
    @Query("SELECT * FROM tickets WHERE userId = :userId")
    List<Ticket> getTicketsByUser(int userId);
}
