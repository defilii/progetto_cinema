package it.euris.javaacademy.cinema.service;

import it.euris.javaacademy.cinema.entity.Cinema;

import java.util.List;

public interface CinemaService {
    List<Cinema> findAll();

    Cinema insert(Cinema cinema);

    Cinema update(Cinema cinema);

    Boolean deleteById(Integer idCinema);

    Cinema findById(Integer idCinema);

}
