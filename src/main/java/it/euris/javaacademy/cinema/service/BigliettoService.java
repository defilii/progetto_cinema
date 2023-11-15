package it.euris.javaacademy.cinema.service;

import it.euris.javaacademy.cinema.entity.Biglietto;

import java.util.List;

public interface BigliettoService {
    List<Biglietto> findAll();

    Biglietto insert(Biglietto biglietto);

    Biglietto update(Biglietto biglietto);

    Boolean deleteById(Integer idBiglietto);

    Biglietto findById(Integer idBiglietto);

}
