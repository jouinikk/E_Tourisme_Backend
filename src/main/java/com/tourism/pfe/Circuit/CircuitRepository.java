package com.tourism.pfe.Circuit;

import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuit;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CircuitRepository extends JpaRepository<Circuit, Integer> {
    List<Circuit> findCircuitsByActiveIsTrue();
}

