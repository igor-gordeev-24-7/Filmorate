package model;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private int currentId = 1;

    public Film addFilm(Film film) {
        film.setId(currentId++);
        films.put(film.getId(), film);
        return film;
    }

    public Collection<Film> getAllFilms() {
        return films.values();
    }

    public Optional<Film> getFilmById(int id) {
        return Optional.ofNullable(films.get(id));
    }
}
