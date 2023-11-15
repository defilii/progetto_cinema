package it.euris.javaacademy.cinema.service.impl;

import it.euris.javaacademy.cinema.entity.Spettatore;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.repository.SpettatoreRepository;
import it.euris.javaacademy.cinema.service.SpettatoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SpettatoreServiceImpl implements SpettatoreService {
    SpettatoreRepository spettatoreRepository;
    @Override
    public List<Spettatore> findAll() {
        return spettatoreRepository.findAll();
    }

    @Override
    public Spettatore insert(Spettatore spettatore) {
        if (spettatore.getId() != null && spettatore.getId() > 0) {
            throw new IdMustBeNullException();
        }
        return spettatoreRepository.save(spettatore);
    }

    @Override
    public Spettatore update(Spettatore spettatore) {
        if (spettatore.getId() == null || spettatore.getId() == 0) {
            throw new IdMustNotBeNullException();
        }
        return spettatoreRepository.save(spettatore);
    }

    @Override
    public Boolean deleteById(Integer idSpettatore) {
        spettatoreRepository.deleteById(idSpettatore);
        return spettatoreRepository.findById(idSpettatore).isEmpty();
    }

    @Override
    public Spettatore findById(Integer idSpettatore) {
        return spettatoreRepository.findById(idSpettatore).orElse(Spettatore.builder().build());
    }
}
