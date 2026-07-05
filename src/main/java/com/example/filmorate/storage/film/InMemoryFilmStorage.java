package com.example.filmorate.storage.film;

import com.example.filmorate.exeption.EntityNotFoundException;
import com.example.filmorate.model.Film;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public Film addFilm(Film film) {
        film.setId(currentId++);
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public List<Film> getAllFilms() {
        if (!films.isEmpty()) {
            return new ArrayList<>(films.values());
        }
        throw new EntityNotFoundException(
                "Фильмы не найдены",
                List.of("Cписок пуст"));
    }

    @Override
    public Film getFilmById(Long id) {
        if (films.containsKey(id)) {
            return films.get(id);
        }
        throw new EntityNotFoundException(
                "Фильм с id " + id + " не найден",
                List.of("Проверьте корректный ли id"));
    }

    @Override
    public Film updateFilm(Long id, Film film) {
        if (films.containsKey(id)){
            film.setId(id);
            films.put(id, film);
            return film;
        }
        throw new EntityNotFoundException(
                "Фильм с id " + id + " не найден",
                List.of("Проверьте корректный ли id"));
    }

    @Override
    public void deleteFilms() {
        if (films.isEmpty()) {
            throw new EntityNotFoundException(
                    "Записи не найдены",
                    List.of("Удаление невозможно"));
        }
        films.clear();
    }

    @Override
    public Film deleteFilmById(Long id) {
        if (films.containsKey(id)) {
            return films.remove(id);
        }
        throw new EntityNotFoundException(
                "Фильм с id " + id + " не найден",
                List.of("Проверьте корректный ли id"));
    }
}
