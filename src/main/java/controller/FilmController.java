package controller;

import model.Film;
import model.FilmStorage;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmStorage filmStorage;

    public FilmController(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        return filmStorage.addFilm(film);
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }
}
