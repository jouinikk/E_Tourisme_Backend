package com.tourism.pfe.Circuit.SortieCircuit;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tourism.pfe.Circuit.Circuit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class SortieCircuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int placesDispo;

    private float prix;

    @ManyToOne
    @JoinColumn(name = "circuit_id")
    private Circuit circuit;

    private boolean active;




}