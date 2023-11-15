package it.euris.javaacademy.cinema.repository;

import it.euris.javaacademy.cinema.entity.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpettatoreRepository extends JpaRepository<Spettatore, Integer> {
}
