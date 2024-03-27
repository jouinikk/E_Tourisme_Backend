package com.tourism.pfe.Circuit.ReservationCircuit;

import com.tourism.pfe.Circuit.SortieCircuit.SortieCircuit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationCircuitService {
private final ReservationCircuitRepository reservationCircuitRepository ;

public ReservationCircuit addReservation(ReservationCircuit reservationCircuit){
    return reservationCircuitRepository.save(reservationCircuit);
}
public void deleteReservation (int id ){
    reservationCircuitRepository.deleteById(id);
}
public List<ReservationCircuit> getAllReservation(){
    return reservationCircuitRepository.findAll();
}

    public ReservationCircuit getOne(int id) {
        return reservationCircuitRepository.findById(id).get();
    }
}
