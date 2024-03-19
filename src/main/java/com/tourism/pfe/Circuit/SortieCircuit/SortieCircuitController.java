package com.tourism.pfe.Circuit.SortieCircuit;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sortieCircuit")
@CrossOrigin(origins = "*")
public class SortieCircuitController {

    public SortieCircuitController(SortieCircuitService service) {
        this.service = service;
    }

    SortieCircuitService service;

    @GetMapping("/")
    public List<SortieCircuit> getSortieCircuit(){
        return service.getAllSortieCircuits();
    }


    @PostMapping("/add")
    public SortieCircuit add(@RequestBody SortieCircuit sortieCircuit){
        return service.addSortieCircuit(sortieCircuit);
    }
}
