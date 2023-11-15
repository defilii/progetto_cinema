package it.euris.javaacademy.cinema.dto;

import it.euris.javaacademy.cinema.dto.archetype.Dto;
import it.euris.javaacademy.cinema.entity.Film;
import it.euris.javaacademy.cinema.entity.SalaCinematografica;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SalaCinematograficaDto implements Dto {
    Integer id;
    Double incassi;
    Integer sedie;
    CinemaDto cinema;
    FilmDto film;

    @Override
    public SalaCinematografica toModel() {
        return SalaCinematografica.builder()
                .id(id)
                .incassi(incassi)
                .sedie(sedie)
                .film(null == film ? null : film.toModel())
                .cinema(cinema.toModel())
                .build();
    }
}