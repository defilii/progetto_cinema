package it.euris.javaacademy.cinema.service.impl;

import it.euris.javaacademy.cinema.entity.SalaCinematografica;
import it.euris.javaacademy.cinema.entity.Spettatore;
import it.euris.javaacademy.cinema.exceptions.*;
import it.euris.javaacademy.cinema.repository.SalaCinematograficaRepository;
import it.euris.javaacademy.cinema.service.SalaCinematograficaService;
import it.euris.javaacademy.cinema.service.SpettatoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SalaCinematograficaServiceImpl implements SalaCinematograficaService {
    SpettatoreService spettatoreService;
    SalaCinematograficaRepository salaCinematograficaRepository;

    @Override
    public List<SalaCinematografica> findAll() {
        return salaCinematograficaRepository.findAll();
    }

    @Override
    public SalaCinematografica insert(SalaCinematografica salaCinematografica) {
        if (salaCinematografica.getId() != null && salaCinematografica.getId() > 0) {
            throw new IdMustBeNullException();
        }
        return salaCinematograficaRepository.save(salaCinematografica);
    }

    @Override
    public SalaCinematografica update(SalaCinematografica salaCinematografica) {
        if (salaCinematografica.getId() == null || salaCinematografica.getId() == 0) {
            throw new IdMustNotBeNullException();
        }
        return salaCinematograficaRepository.save(salaCinematografica);
    }

    @Override
    public Boolean deleteById(Integer idSalaCinematografica) {
        salaCinematograficaRepository.deleteById(idSalaCinematografica);
        return salaCinematograficaRepository.findById(idSalaCinematografica).isEmpty();
    }

    @Override
    public SalaCinematografica findById(Integer idSalaCinematografica) {
        return salaCinematograficaRepository.findById(idSalaCinematografica).orElse(SalaCinematografica.builder().build());
    }

    @Override
    public Boolean svuotaSala(Integer idSalaCinematografica) {
        SalaCinematografica salaCinematografica = salaCinematograficaRepository
                .findById(idSalaCinematografica).orElse(SalaCinematografica.builder().build());
        salaCinematografica.getSpettatori().forEach(
                spettatore ->
                {
                    spettatore.setSalaCinematografica(null);
                    spettatoreService.update(spettatore);
                }
        );
        salaCinematografica.setSpettatori(new ArrayList<Spettatore>());
        salaCinematograficaRepository.save(salaCinematografica);
        return salaCinematografica.getSpettatori().isEmpty();
    }

    @Override
    public Boolean aggiungiSpettatore(Integer idSalaCinematografica, Integer idSpettatore) {
        SalaCinematografica salaCinematografica = salaCinematograficaRepository
                .findById(idSalaCinematografica).orElse(SalaCinematografica.builder().build());
        List<Spettatore> spettatori = new ArrayList<>(salaCinematografica.getSpettatori());
        Spettatore spettatore = spettatoreService.findById(idSpettatore);
        if (salaCinematografica.getFilm() == null) {
            throw new SalaNonStaMostrandoFilm();
        }
        if (salaCinematografica.getFilm().getEtaMinima() >= spettatore.getAnni()) {
            throw new FilmVietatoAiMinori();
        }
        if (salaCinematografica.getSedie() == salaCinematografica.getSpettatori().size()) {
            throw new SalaAlCompleto();
        }
        spettatori.add(spettatore);
        salaCinematografica.setSpettatori(spettatori);
        salaCinematograficaRepository.save(salaCinematografica);

        spettatore.setSalaCinematografica(salaCinematografica);
        spettatoreService.update(spettatore);

        return salaCinematografica.getSpettatori().contains(spettatore);
    }
}
