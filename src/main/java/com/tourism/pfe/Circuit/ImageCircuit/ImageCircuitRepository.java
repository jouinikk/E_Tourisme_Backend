package com.tourism.pfe.Circuit.ImageCircuit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageCircuitRepository extends JpaRepository<ImageCircuit,Integer> {
    List<ImageCircuit> findImageCircuitByCircuitId(int id);
}
