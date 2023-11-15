package it.euris.javaacademy.cinema.service;

import it.euris.javaacademy.cinema.entity.SalaCinematografica;

import java.util.List;

public interface SalaCinematograficaService {
    List<SalaCinematografica> findAll();

    SalaCinematografica insert(SalaCinematografica salaCinematografica);

    SalaCinematografica update(SalaCinematografica salaCinematografica);

    Boolean deleteById(Integer idSalaCinematografica);

    SalaCinematografica findById(Integer idSalaCinematografica);

    Boolean svuotaSala(Integer idSalaCinematografica);
    Boolean aggiungiSpettatore(Integer idSalaCinematografica, Integer idSpettatore);

}
