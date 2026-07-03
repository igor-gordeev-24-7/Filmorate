package com.example.filmorate.controller;

import com.example.filmorate.model.Film;
import com.example.filmorate.repository.FilmRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @PostMapping
    public ResponseEntity<Film> addFilm(@Valid @RequestBody Film film) {
        Film savedFilm = filmRepository.addFilm(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return filmRepository.getAllFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable int id) {
        return filmRepository.getFilmById(id);
    }

    @PutMapping("/{id}")
    public Film updateFilm(
            @PathVariable int id,
            @Valid @RequestBody Film film) {
        return filmRepository.updateFilm(id, film);
    }

    @DeleteMapping()
    public ResponseEntity deleteFilms() {
        return inMemoryFilmStorage.deleteFilms();
    }

    @DeleteMapping("/{id}")
    public Film deleteFilmById(@PathVariable int id) {
        return inMemoryFilmStorage.deleteFilmById(id);
    }
}
