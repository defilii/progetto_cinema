package it.euris.javaacademy.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.javaacademy.cinema.dto.SalaCinematograficaDto;
import it.euris.javaacademy.cinema.dto.archetype.Dto;
import it.euris.javaacademy.cinema.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sala_cinematografica")
public class SalaCinematografica implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "incassi", nullable=false)
    private Double incassi;

    @Column(name = "sedie", nullable=false)
    private Integer sedie;

    @ManyToOne
    @JoinColumn(name="cinema_id", nullable=false)
    private Cinema cinema;

    @ManyToOne
    @JoinColumn(name="film_id", nullable=true)
    private Film film;

    @OneToMany(mappedBy = "salaCinematografica", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Spettatore> spettatori = new ArrayList<>();

    public void aggiungiIncasso(Double incasso) {
        incassi = incassi + incasso;
    }

    @Override
    public SalaCinematograficaDto toDto() {
        return SalaCinematograficaDto.builder()
                .id(id)
                .sedie(sedie)
                .incassi(incassi)
                .film(null == film ? null  : film.toDto())
                .cinema(cinema.toDto())
                .build();
    }
}
