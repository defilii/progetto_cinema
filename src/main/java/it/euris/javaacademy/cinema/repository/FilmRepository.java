package it.euris.javaacademy.cinema.repository;

import it.euris.javaacademy.cinema.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
