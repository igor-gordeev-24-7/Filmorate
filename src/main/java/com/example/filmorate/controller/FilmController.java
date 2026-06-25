package com.example.filmorate.controller;

import com.example.filmorate.model.Film;
import com.example.filmorate.repository.FilmRepository;
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
    public void addFilm(@RequestBody Film film) {
        filmRepository.addFilm(film);
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return filmRepository.getAllFilms();
    }

    @PutMapping
    public void updateFilm(Film film) {
        filmRepository.updateFilm(film);
    }
}
