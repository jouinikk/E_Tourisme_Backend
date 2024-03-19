package com.tourism.pfe.Arrangement;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/arrangement")
@RequiredArgsConstructor
public class ArrangementController {
    private final ArrangementService arrangementService;

    @PostMapping("/add")
    public Arrangement addArrangement(@RequestBody Arrangement arrangement){
        return arrangementService.addArrangement(arrangement);
    }

    @GetMapping
    public List<Arrangement> getArrangements(){
        return arrangementService.getArrangements();
    }

    @GetMapping("/{id}")
    public Arrangement getArrangement(@PathVariable int id){
        return arrangementService.getArrangement(id);
    }

    @PatchMapping("/update/{id}")
    public Arrangement updateArrangement(@PathVariable int id){
        Arrangement arrangement = arrangementService.getArrangement(id);
        arrangementService.updateArrangement(arrangement);
        return arrangement;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArrangement(@PathVariable int id){
        arrangementService.deleteArrangement(id);
    }
}
