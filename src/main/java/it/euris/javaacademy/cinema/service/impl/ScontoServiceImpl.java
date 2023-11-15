package it.euris.javaacademy.cinema.service.impl;

import it.euris.javaacademy.cinema.entity.Sconti;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.repository.ScontiRepository;
import it.euris.javaacademy.cinema.service.ScontoService;

import java.util.List;

public class ScontoServiceImpl implements ScontoService {
    ScontiRepository scontiRepository;
    @Override
    public List<Sconti> findAll() {
        return scontiRepository.findAll();
    }

    @Override
    public Sconti insert(Sconti sconti) {
        if (sconti.getId() != null && sconti.getId() > 0) {
            throw new IdMustBeNullException();
        }
        return scontiRepository.save(sconti);
    }

    @Override
    public Sconti update(Sconti sconti) {
        if (sconti.getId() == null || sconti.getId() == 0) {
            throw new IdMustNotBeNullException();
        }
        return scontiRepository.save(sconti);
    }

    @Override
    public Boolean deleteById(Integer idSconti) {
        scontiRepository.deleteById(idSconti);
        return scontiRepository.findById(idSconti).isEmpty();
    }

    @Override
    public Sconti findById(Integer idSconti) {
        return scontiRepository.findById(idSconti).orElse(Sconti.builder().build());
    }
}
