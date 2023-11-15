package it.euris.javaacademy.cinema.controller;

import it.euris.javaacademy.cinema.dto.FilmDto;
import it.euris.javaacademy.cinema.entity.Film;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/film")
public class FilmController {

    FilmService filmService;
    @GetMapping("/getAll")
    public List<FilmDto> getAllFilms() {
        return filmService.findAll().stream().map(Film::toDto).toList();
    }

    @PostMapping("/insert")
    public FilmDto saveFilm(@RequestBody FilmDto filmDto) {
        try {
            Film film = filmDto.toModel();
            return filmService.insert(film).toDto();
        } catch (IdMustBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public FilmDto updateFilm(@RequestBody FilmDto filmDto) {
        try {Film film = filmDto.toModel();
            return filmService.update(film).toDto();
        } catch (IdMustNotBeNullException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteFilm(@PathVariable("id") Integer idFilm) {
        return filmService.deleteById(idFilm);
    }

    @GetMapping("/getById/{id}")
    public FilmDto getFilmById(@PathVariable("id") Integer idFilm) {
        return filmService.findById(idFilm).toDto();
    }
}