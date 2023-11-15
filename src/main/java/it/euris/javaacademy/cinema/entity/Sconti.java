package it.euris.javaacademy.cinema.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sconti")
public class Sconti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "anni_minimi", nullable=false)
    private Integer anniMinimi;

    @Column(name = "anni_massimi", nullable=false)
    private Integer anniMassimi;

    @Column(name = "sconto", nullable=false)
    private Double sconto;

}
