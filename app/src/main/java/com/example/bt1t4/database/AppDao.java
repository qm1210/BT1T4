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
    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    // Movie
    @Insert
    void insertMovie(Movie movie);
    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();
    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    Movie getMovieById(int movieId);

    // Theater
    @Insert
    void insertTheater(Theater theater);
    @Query("SELECT * FROM theaters")
    List<Theater> getAllTheaters();
    @Query("SELECT DISTINCT t.* FROM theaters t INNER JOIN showtimes s ON t.id = s.theaterId WHERE s.movieId = :movieId")
    List<Theater> getTheatersByMovie(int movieId);
    @Query("SELECT * FROM theaters WHERE id = :theaterId LIMIT 1")
    Theater getTheaterById(int theaterId);

    // ShowTime
    @Insert
    void insertShowTime(ShowTime showTime);
    @Query("SELECT * FROM showtimes WHERE movieId = :movieId AND theaterId = :theaterId")
    List<ShowTime> getShowTimesByMovieAndTheater(int movieId, int theaterId);

    // Ticket
    @Insert
    void insertTicket(Ticket ticket);
    @Query("SELECT * FROM tickets WHERE userId = :userId")
    List<Ticket> getTicketsByUser(int userId);
}
