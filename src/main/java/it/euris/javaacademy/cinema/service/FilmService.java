package it.euris.javaacademy.cinema.service;

import it.euris.javaacademy.cinema.entity.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAll();

    Film insert(Film film);

    Film update(Film film);

    Boolean deleteById(Integer idFilm);

    Film findById(Integer idFilm);

}
