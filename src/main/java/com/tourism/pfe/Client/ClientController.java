package com.tourism.pfe.Client;


import com.tourism.pfe.Circuit.CircuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/Client")
@CrossOrigin(origins = "*")


public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(CircuitService circuitService, ClientService clientService) {
        this.clientService = clientService;

    }
    @PostMapping("/add")
    public Client addHistorique(@RequestBody Client cl) {
        return clientService.saveClient(cl);
    }






}