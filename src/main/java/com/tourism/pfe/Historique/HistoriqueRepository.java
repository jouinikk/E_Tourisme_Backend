package com.tourism.pfe.Historique;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Integer>{

    List<Historique> findByUserId(String userId);

    List<Historique> findByUserRole(String userRole);

    List<Historique> findByAction(String action);
}
