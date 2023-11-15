package it.euris.javaacademy.cinema.repository;

import it.euris.javaacademy.cinema.entity.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BigliettoRepository extends JpaRepository<Biglietto, Integer> {
}
