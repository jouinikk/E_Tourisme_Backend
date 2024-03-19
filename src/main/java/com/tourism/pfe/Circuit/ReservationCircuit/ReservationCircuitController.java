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
    public void addResrevation(ReservationCircuit reservationCircuit){
        reservationCircuitService.addReservation(reservationCircuit);
    }
    @DeleteMapping("/delete/{id}")
    public void Delete(int id){
        reservationCircuitService.deleteReservation(id);
    }
}
