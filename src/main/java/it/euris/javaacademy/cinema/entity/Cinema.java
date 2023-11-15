package it.euris.javaacademy.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.javaacademy.cinema.dto.CinemaDto;
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
@Table(name = "cinema")
public class Cinema implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome", nullable=false)
    private String nome;
    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SalaCinematografica> saleCinematografiche = new ArrayList<>();

    @Override
    public CinemaDto toDto() {
        return CinemaDto.builder()
                .id(id)
                .nome(nome)
                .build();
    }

    public Double incassiTotali(){
        return saleCinematografiche.stream()
                .map(SalaCinematografica::getIncassi)
                .reduce(0.0, Double::sum);
    }
}
