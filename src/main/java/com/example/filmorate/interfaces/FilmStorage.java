package com.example.filmorate.interfaces;

import com.example.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    public Film addFilm(Film film);
    public List<Film> getAllFilms();
    public Film getFilmById(int id);
    public Film updateFilm( int id, Film film);
    public Film deleteFilms();
    public Film deleteFilmById();
}
