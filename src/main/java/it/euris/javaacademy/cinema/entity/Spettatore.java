package it.euris.javaacademy.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.javaacademy.cinema.dto.SpettatoreDto;
import it.euris.javaacademy.cinema.dto.archetype.Dto;
import it.euris.javaacademy.cinema.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "spettatore")
public class Spettatore implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", nullable=false)
    private String nome;

    @Column(name = "cognome", nullable=false)
    private String cognome;

    @Column(name = "data_nascita", nullable=false)
    private LocalDateTime dataNascita;

    @ManyToOne
    @JoinColumn(name="sala_cinematografica_id", nullable=true)
    private SalaCinematografica salaCinematografica;

    @OneToMany(mappedBy = "spettatore", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Biglietto> biglietti = new ArrayList<>();

    @Override
    public SpettatoreDto toDto() {
        return SpettatoreDto.builder()
                .nome(nome)
                .cognome(cognome)
                .id(id)
                .dataNascita(dataNascita)
                .salaCinematografica(null == salaCinematografica ? null : salaCinematografica.toDto())
                .build();
    }

    public Integer getAnni() {
        return LocalDateTime.now().getYear() - dataNascita.getYear();
    }
}
