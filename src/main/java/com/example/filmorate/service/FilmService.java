package com.example.filmorate.service;

import com.example.filmorate.model.Film;
import com.example.filmorate.model.User;
import com.example.filmorate.storage.film.FilmStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    public final FilmStorage filmStorage;

    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    // Добавление лайка
    public void addLike(Long filmId, Long userId) {
        Film film = filmStorage.getFilmById(filmId);

        if(!film.getLikes().contains(userId)) {
            film.getLikes().add(userId);
        }
    }

    // Удаление лайка
    public void removeLike(Long filmId, Long userId) {
        Film film = filmStorage.getFilmById(filmId);

        film.getLikes().remove(userId);
    }

    // Получение топ-10 популярных фильмов
    public List<Film> getTopPopularFilms(int count) {
        return filmStorage.getAllFilms().stream()
                .sorted((f1, f2) -> Integer.compare(
                        f2.getLikes().size(),
                        f1.getLikes().size()
                ))
                .limit(count)
                .collect(Collectors.toList());
    }

}
