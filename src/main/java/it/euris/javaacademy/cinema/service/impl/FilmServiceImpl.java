package it.euris.javaacademy.cinema.service.impl;

import it.euris.javaacademy.cinema.entity.Film;
import it.euris.javaacademy.cinema.exceptions.IdMustBeNullException;
import it.euris.javaacademy.cinema.exceptions.IdMustNotBeNullException;
import it.euris.javaacademy.cinema.repository.FilmRepository;
import it.euris.javaacademy.cinema.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {
    FilmRepository filmRepository;
    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film insert(Film film) {
        if (film.getId() != null && film.getId() > 0) {
            throw new IdMustBeNullException();
        }
        return filmRepository.save(film);
    }

    @Override
    public Film update(Film film) {
        if (film.getId() == null || film.getId() == 0) {
            throw new IdMustNotBeNullException();
        }
        return filmRepository.save(film);
    }

    @Override
    public Boolean deleteById(Integer idFilm) {
        filmRepository.deleteById(idFilm);
        return filmRepository.findById(idFilm).isEmpty();
    }

    @Override
    public Film findById(Integer idFilm) {
        return filmRepository.findById(idFilm).orElse(Film.builder().build());
    }
}
