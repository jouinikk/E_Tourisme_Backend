package com.tourism.pfe.Circuit.ReservationCircuit;

import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuit;
import com.tourism.pfe.Client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class ReservationCircuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nbAdulte ;
    private  int nbEnfant ;
    private Date date ;

    private Float Totale ;
    @ManyToOne
    @JoinColumn(name = "idClient")

    private Client idClient ;
    @ManyToOne
    @JoinColumn(name = "idSC")

    private SortieCircuit idSc;


}
