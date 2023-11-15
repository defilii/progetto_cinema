package it.euris.javaacademy.cinema.service.impl;

import it.euris.javaacademy.cinema.entity.SalaCinematografica;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.repository.SalaCinematograficaRepository;
import it.euris.javaacademy.cinema.service.SalaCinematograficaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SalaCinematograficaServiceImpl implements SalaCinematograficaService {
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
}
