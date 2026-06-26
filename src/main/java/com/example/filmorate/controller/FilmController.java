package com.example.filmorate.controller;

import com.example.filmorate.model.Film;
import com.example.filmorate.repository.FilmRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @PostMapping
    public void addFilm(@Valid @RequestBody Film film) {
        filmRepository.addFilm(film);
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

        film.setId(id);

        Optional<Film> existingFilm = filmRepository.getFilmById(id);

        if (existingFilm.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Film updatedFilm = filmRepository.updateFilm(film);
        return ResponseEntity.ok(updatedFilm);
    }
}
