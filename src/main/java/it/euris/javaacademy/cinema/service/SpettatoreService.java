package it.euris.javaacademy.cinema.service;

import it.euris.javaacademy.cinema.entity.Spettatore;

import java.util.List;

public interface SpettatoreService {
    List<Spettatore> findAll();

    Spettatore insert(Spettatore spettatore);

    Spettatore update(Spettatore spettatore);

    Boolean deleteById(Integer idSpettatore);

    Spettatore findById(Integer idSpettatore);

}
