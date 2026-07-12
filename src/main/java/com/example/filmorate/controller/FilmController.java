package com.example.filmorate.controller;

import com.example.filmorate.model.Film;
import com.example.filmorate.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
public class FilmController {

    public final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseEntity<Film> addFilm(@Valid @RequestBody Film film) {
        Film savedFilm = filmService.addFilm(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }

    @PutMapping()
    public Film updateFilm(@Valid @RequestBody Film film) {
        return filmService.updateFilm(film.getId(), film);
    }

    @DeleteMapping()
    public ResponseEntity<Map<String, String>> deleteFilms() {
        filmService.deleteFilms();
        return ResponseEntity.ok(Map.of("message", "Все записи успешно удалены"));
    }

    @DeleteMapping("/{id}")
    public Film deleteFilmById(@PathVariable Long id) {
        return filmService.deleteFilmById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable Long id, @PathVariable Long userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable Long id, @PathVariable Long userId) {
        filmService.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getTopPopularFilms(
            @RequestParam(defaultValue = "10") Integer count) {
        return filmService.getTopPopularFilms(count);
    }
}
