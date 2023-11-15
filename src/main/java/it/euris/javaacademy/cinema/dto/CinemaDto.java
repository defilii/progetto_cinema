package it.euris.javaacademy.cinema.dto;

import it.euris.javaacademy.cinema.dto.archetype.Dto;
import it.euris.javaacademy.cinema.dto.archetype.Model;
import it.euris.javaacademy.cinema.entity.Cinema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class CinemaDto implements Dto {
    Integer id;
    String nome;

    @Override
    public Cinema toModel() {
        return Cinema.builder()
                .id(id)
                .nome(nome)
                .build();
    }
}