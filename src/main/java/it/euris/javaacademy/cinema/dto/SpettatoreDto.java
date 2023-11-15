package it.euris.javaacademy.cinema.dto;

import it.euris.javaacademy.cinema.dto.archetype.Dto;
import it.euris.javaacademy.cinema.dto.archetype.Model;
import it.euris.javaacademy.cinema.entity.Spettatore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class SpettatoreDto implements Dto {
    Integer id;
    String nome;
    String cognome;
    LocalDateTime dataNascita;
    SalaCinematograficaDto salaCinematografica;

    @Override
    public Spettatore toModel() {
        return Spettatore.builder()
                .id(id)
                .dataNascita(dataNascita)
                .nome(nome)
                .cognome(cognome)
                .salaCinematografica(salaCinematografica.toModel())
                .build();
    }
}