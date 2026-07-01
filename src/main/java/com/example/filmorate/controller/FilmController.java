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
    public ResponseEntity<Film> getFilmById(@PathVariable int id) {
        return filmRepository.getFilmById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(
            @PathVariable int id,
            @Valid @RequestBody Film film) {

        Film updatedFilm = filmRepository.updateFilm(film, id);
        return ResponseEntity.ok(updatedFilm);
    }
}
