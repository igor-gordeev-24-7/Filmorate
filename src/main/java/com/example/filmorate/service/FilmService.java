package com.example.filmorate.service;

import com.example.filmorate.model.Film;
import com.example.filmorate.model.User;
import com.example.filmorate.storage.film.FilmStorage;
import com.example.filmorate.storage.user.UserStorage;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film getFilmById(Long id) {
        return filmStorage.getFilmById(id);
    }

    public Film updateFilm(Long id, Film film) {
        return filmStorage.updateFilm(id, film);
    }

    public void deleteFilms() {
        filmStorage.deleteFilms();
    }

    public Film deleteFilmById(Long id) {
        return filmStorage.deleteFilmById(id);
    }

    // Добавление лайка
    public void addLike(Long filmId, Long userId) {
        Film film = filmStorage.getFilmById(filmId);

        User user = userStorage.getUserById(userId);

        if (film.getLikes() == null) {
            film.setLikes(new HashSet<>());
        }

        if (!film.getLikes().contains(userId)) {
            film.getLikes().add(userId);
        }
    }

    // Удаление лайка
    public void removeLike(Long filmId, Long userId) {
        Film film = filmStorage.getFilmById(filmId);

        User user = userStorage.getUserById(userId);

        if (film.getLikes() == null) {
            film.setLikes(new HashSet<>());
        }

        if (film.getLikes().contains(userId)) {
            film.getLikes().remove(userId);
        }

        throw new IllegalArgumentException(
                String.format("Пользователь с id %d не ставил лайк фильму с id %d", userId, filmId)
        );
    }

    // Получение топ-N популярных фильмов по количеству лайков
    public List<Film> getTopPopularFilms(int count) {
        return filmStorage.getAllFilms().stream()
                .map(film -> {
                    if (film.getLikes() == null) {
                        film.setLikes(new HashSet<>());
                    }
                    return film;
                })
                .sorted((f1, f2) -> Integer.compare(
                        f2.getLikes().size(),
                        f1.getLikes().size()
                ))
                .limit(count)
                .collect(Collectors.toList());
    }

}
