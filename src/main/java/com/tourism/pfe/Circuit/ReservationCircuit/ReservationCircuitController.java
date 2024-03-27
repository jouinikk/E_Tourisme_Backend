package com.tourism.pfe.Circuit.ReservationCircuit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservationCircuit")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ReservationCircuitController {
    private final ReservationCircuitService reservationCircuitService;
    @GetMapping("/all")
    public List<ReservationCircuit>getAll(){
        return reservationCircuitService.getAllReservation();
    }
    @PostMapping("/add")
    public void addResrevation(@RequestBody  ReservationCircuit reservationCircuit){
        reservationCircuitService.addReservation(reservationCircuit);
    }
    @GetMapping("/{id}")
    public ReservationCircuit getOne(@PathVariable int id){
        return reservationCircuitService.getOne(id);
    }

    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable int id){
        reservationCircuitService.deleteReservation(id);
    }
}
