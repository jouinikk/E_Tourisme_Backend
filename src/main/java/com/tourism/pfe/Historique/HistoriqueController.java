package com.tourism.pfe.Historique;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/historique")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class HistoriqueController {
    private final HistoriqueService historiqueService;

    @GetMapping("/all")
    public List<Historique> getHistoriques() {
        return historiqueService.getHistoriques();
    }

    @GetMapping("/byUserId/{id}")
    public List<Historique> findByUserId(@PathVariable String id) {
        return historiqueService.findByUserId(id);
    }

    @GetMapping("/byUserRole/{role}")
    public List<Historique> findByUserRole(@PathVariable String role) {
        return historiqueService.findByUserRole(role);
    }

    @PostMapping("/add")
    public Historique addHistorique(@RequestBody Historique historique) {
        return historiqueService.addHistorique(historique);
    }

    @GetMapping("/ByAction/{action}")
    public List<Historique> findByAction(@PathVariable String action) {
        return historiqueService.findByAction(action);
    }
}
