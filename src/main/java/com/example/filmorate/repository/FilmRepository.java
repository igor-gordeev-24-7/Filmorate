package com.example.filmorate.repository;

import com.example.filmorate.exeption.EntityNotFoundException;
import com.example.filmorate.model.Film;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FilmRepository {
    private final Map<Integer, Film> films = new HashMap<>();
    private int currentId = 1;

    public Film addFilm(Film film) {
        film.setId(currentId++);
        films.put(film.getId(), film);
        return film;
    }

    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    public Optional<Film> getFilmById(int id) {
        return Optional.ofNullable(films.get(id));
    }

    public Film updateFilm(Film film, int id) {
        if (films.containsKey(id)){
            films.put(id, film);
            return film;
        }
        throw new EntityNotFoundException("Фильм с id " + film.getId() + " не найден", List.of("Проверьте корректный ли id"));
    }
}
