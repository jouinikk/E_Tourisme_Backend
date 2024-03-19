package com.tourism.pfe.Historique;

import com.tourism.pfe.Config.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriqueService {
    private final JWTService jwtService;
    private final HistoriqueRepository repository;

    public Historique addHistorique(Historique historique) {
        return repository.save(historique);
    }

    public List<Historique> getHistoriques() {
        return repository.findAll();
    }

    public void deleteHistorique(int id) {
        repository.deleteById(id);
    }

    public List<Historique> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public List<Historique> findByUserRole(String userRole) {
        return repository.findByUserRole(userRole);
    }

    public List<Historique> findByAction(String action) {
        return repository.findByAction(action);
    }
}
