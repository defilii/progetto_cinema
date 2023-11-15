package it.euris.javaacademy.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.javaacademy.cinema.dto.FilmDto;
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
@Table(name = "film")
public class Film implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titolo", nullable=false)
    private String titolo;
    @Column(name = "genere", nullable=false)
    private String genere;
    @Column(name = "autore", nullable=false)
    private String autore;
    @Column(name = "produttore", nullable=false)
    private String produttore;

    @Column(name = "eta_minima", nullable=false)
    private Integer etaMinima;
    @Column(name = "durata", nullable=false)
    private String durata;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SalaCinematografica> saleCinematografiche = new ArrayList<>();

    @Override
    public FilmDto toDto() {
        return FilmDto.builder().build();
    }
}
