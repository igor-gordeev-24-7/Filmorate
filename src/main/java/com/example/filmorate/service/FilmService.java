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
        List<Film> films = filmStorage.getAllFilms();
        if (films.isEmpty()) {
            throw new EntityNotFoundException(
                    "Фильмы не найдены",
                    List.of("Список фильмов пуст"));
        }
        return films;
    }

    public Film getFilmById(Long id) {
        Film film = filmStorage.getFilmById(id);
        if (film == null) {
            throw new EntityNotFoundException(
                    "Фильм с id " + id + " не найден",
                    List.of("Проверьте корректный ли id"));
        }
        return film;
    }

    public Film updateFilm(Long id, Film film) {
        Film updatedFilm = filmStorage.updateFilm(id, film);
        if (updatedFilm == null) {
            throw new EntityNotFoundException(
                    "Фильм с id " + id + " не найден",
                    List.of("Проверьте корректный ли id"));
        }
        return updatedFilm;
    }

    public void deleteFilms() {
        filmStorage.deleteFilms();
    }

    public Film deleteFilmById(Long id) {
        Film deletedFilm = filmStorage.deleteFilmById(id);
        if (deletedFilm == null) {
            throw new EntityNotFoundException(
                    "Фильм с id " + id + " не найден",
                    List.of("Проверьте корректный ли id"));
        }
        return deletedFilm;
    }

    // Добавление лайка
    public void addLike(Long filmId, Long userId) {
        Film film = getFilmById(filmId);
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
        Film film = getFilmById(filmId);
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
        List<Film> films = getAllFilms();

        return films.stream()
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
