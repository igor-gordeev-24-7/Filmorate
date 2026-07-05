package com.example.filmorate.storage.film;

import com.example.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film addFilm(Film film);
    List<Film> getAllFilms();
    Film getFilmById(Long id);
    Film updateFilm( Long id, Film film);
    void deleteFilms();
    Film deleteFilmById(Long id);
}
