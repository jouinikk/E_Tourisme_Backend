package com.tourism.pfe.Circuit.SortieCircuit;

import com.tourism.pfe.Circuit.Circuit;
import com.tourism.pfe.Historique.Historique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SortieCircuitRepository extends JpaRepository<SortieCircuit, Integer>{
  // List<SortieCircuit> getByCircuitId(int id );
  List<SortieCircuit> findByCircuitId(int idCircuit);

}
