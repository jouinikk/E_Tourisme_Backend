package com.tourism.pfe.Circuit.SortieCircuit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SortieCircuitService {

    private final SortieCircuitRepository sortieCircuitRepository;

    public SortieCircuit addSortieCircuit(SortieCircuit sortieCircuit) {
        return sortieCircuitRepository.save(sortieCircuit);
    }

    public List<SortieCircuit> getAllSortieCircuits() {
        return sortieCircuitRepository.findAll();
    }
}
