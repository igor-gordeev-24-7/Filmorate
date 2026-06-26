package com.example.filmorate.controller;

import com.example.filmorate.model.Film;
import com.example.filmorate.repository.FilmRepository;
import jakarta.validation.Valid;
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

    @PutMapping
    public void updateFilm(Film film) {
        filmRepository.updateFilm(film);
    }
}
