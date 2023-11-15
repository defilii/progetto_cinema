package it.euris.javaacademy.cinema.repository;

import it.euris.javaacademy.cinema.entity.SalaCinematografica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaCinematograficaRepository extends JpaRepository<SalaCinematografica, Integer> {
}
