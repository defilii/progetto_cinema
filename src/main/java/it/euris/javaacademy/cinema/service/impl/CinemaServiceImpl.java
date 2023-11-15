package it.euris.javaacademy.cinema.service.impl;

import it.euris.javaacademy.cinema.entity.Cinema;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.repository.CinemaRepository;
import it.euris.javaacademy.cinema.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CinemaServiceImpl implements CinemaService {
    CinemaRepository cinemaRepository;
    @Override
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema insert(Cinema cinema) {
        if (cinema.getId() != null && cinema.getId() > 0) {
            throw new IdMustBeNullException();
        }
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema update(Cinema cinema) {
        if (cinema.getId() == null || cinema.getId() == 0) {
            throw new IdMustNotBeNullException();
        }
        return cinemaRepository.save(cinema);
    }

    @Override
    public Boolean deleteById(Integer idCinema) {
        cinemaRepository.deleteById(idCinema);
        return cinemaRepository.findById(idCinema).isEmpty();
    }

    @Override
    public Cinema findById(Integer idCinema) {
        return cinemaRepository.findById(idCinema).orElse(Cinema.builder().build());
    }
}
