package it.euris.javaacademy.cinema.service;

import it.euris.javaacademy.cinema.entity.Sconti;

import java.util.List;

public interface ScontoService {
    List<Sconti> findAll();

    Sconti insert(Sconti sconti);

    Sconti update(Sconti sconti);

    Boolean deleteById(Integer idSconti);

    Sconti findById(Integer idSconti);
}
