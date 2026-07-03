package com.example.filmorate.storage;

import com.example.filmorate.model.Film;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmStorage {
    Film addFilm(Film film);
    List<Film> getAllFilms();
    Film getFilmById(int id);
    Film updateFilm( int id, Film film);
    void deleteFilms();
    Film deleteFilmById(int id);
}
