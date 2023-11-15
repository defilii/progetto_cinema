package it.euris.javaacademy.cinema.dto;

import it.euris.javaacademy.cinema.dto.archetype.Dto;
import it.euris.javaacademy.cinema.dto.archetype.Model;
import it.euris.javaacademy.cinema.entity.Biglietto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class BigliettoDto implements Dto {
    Integer id;
    String posizione;
    Double prezzo;
    SpettatoreDto spettatore;

    @Override
    public Biglietto toModel() {
        return Biglietto.builder()
                .id(id)
                .posizione(posizione)
                .prezzo(prezzo)
                .spettatore(spettatore.toModel())
                .build();
    }
}