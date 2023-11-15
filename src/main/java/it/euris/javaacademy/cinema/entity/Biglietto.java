package it.euris.javaacademy.cinema.entity;

import it.euris.javaacademy.cinema.dto.BigliettoDto;
import it.euris.javaacademy.cinema.dto.archetype.Dto;
import it.euris.javaacademy.cinema.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "biglietto")
public class Biglietto implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "posizione", nullable=false)
    private String posizione;
    @Column(name = "prezzo", nullable=false)
    private Double prezzo;

    @ManyToOne
    @JoinColumn(name="spettatore_id", nullable=false)
    private Spettatore spettatore;

    @ManyToOne
    @JoinColumn(name="film_id", nullable=false)
    private Film film;

    @Override
    public BigliettoDto toDto() {
        return BigliettoDto.builder()
                .prezzo(prezzo)
                .id(id)
                .posizione(posizione)
                .prezzo(prezzo)
                .spettatore(spettatore.toDto())
                .film(film.toDto())
                .build();
    }
}
