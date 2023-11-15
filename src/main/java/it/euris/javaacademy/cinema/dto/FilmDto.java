package it.euris.javaacademy.cinema.dto;

import it.euris.javaacademy.cinema.dto.archetype.Dto;
import it.euris.javaacademy.cinema.dto.archetype.Model;
import it.euris.javaacademy.cinema.entity.Film;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class FilmDto implements Dto {
    Integer id;
    String titolo;
    String genere;
    String autore;
    String produttore;
    Integer etaMinima;
    String durata;

    @Override
    public Film toModel() {
        return Film.builder()
                .id(id)
                .titolo(titolo)
                .genere(genere)
                .autore(autore)
                .produttore(produttore)
                .etaMinima(etaMinima)
                .durata(durata)
                .build();
    }
}