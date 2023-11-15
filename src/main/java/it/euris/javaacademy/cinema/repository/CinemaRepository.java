package it.euris.javaacademy.cinema.repository;

import it.euris.javaacademy.cinema.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
