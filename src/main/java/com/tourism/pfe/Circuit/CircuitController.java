package com.tourism.pfe.Circuit;



import com.tourism.pfe.Circuit.ImageCircuit.ImageCircuit;
import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuit;
import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuitService;
import com.tourism.pfe.Config.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/circuit")
@CrossOrigin(origins = "*")


public class CircuitController {
    private final CircuitService circuitService;

    @Autowired
    public CircuitController(CircuitService circuitService) {
        this.circuitService = circuitService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Circuit>> getCircuitsWithSortieCircuits() {
        List<Circuit> circuits = circuitService.getAllCircuits();
        return new ResponseEntity<>(circuits, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CircuitDTOResponse>getAll(){
        return circuitService.getAllCircuitsWithSortiesAndImages();
    }

    @GetMapping("/{id}")
    public CircuitDTOResponse getOne(@PathVariable int id){
        return circuitService.getOneWithSortiesAndImages(id);
    }


    @PostMapping(value = "/addCircuit",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addCircuit(@ModelAttribute CircuitDTO circuitDto) throws IOException {
         circuitService.createCircuit(circuitDto);
    }


}