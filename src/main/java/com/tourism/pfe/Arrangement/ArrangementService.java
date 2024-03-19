package com.tourism.pfe.Arrangement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArrangementService {
    private final ArrangementRepository arrangementRepository;

    public Arrangement addArrangement(Arrangement arrangement){
        return arrangementRepository.save(arrangement);
    }

    public Arrangement getArrangement(int id){
        return arrangementRepository.findById(id).orElse(null);
    }

    public List<Arrangement> getArrangements(){
        return arrangementRepository.findAll();
    }

    public void deleteArrangement(int id){
        arrangementRepository.deleteById(id);
    }
    public void updateArrangement(Arrangement arrangement){
        arrangement.setActive(!arrangement.isActive());
        arrangementRepository.save(arrangement);
    }

}

