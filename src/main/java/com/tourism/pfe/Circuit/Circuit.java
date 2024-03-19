package com.tourism.pfe.Circuit;

import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Circuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String libelle;
    private String courtDescription;

    private String ville;

    private TypeCircuit type;

    private boolean active;

    private String description;

}