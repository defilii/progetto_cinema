package it.euris.javaacademy.cinema.repository;

import it.euris.javaacademy.cinema.entity.Sconti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScontiRepository extends JpaRepository<Sconti, Integer> {
    @Query("SELECT s FROM Sconti s WHERE s.anniMinimi <= :anni AND s.anniMassimi >= :anni ")
    List<Sconti> findSaleByAge(@Param("anni") Integer anni);
}
