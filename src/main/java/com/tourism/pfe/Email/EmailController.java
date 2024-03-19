package com.tourism.pfe.Email;

import com.tourism.pfe.Client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reservation")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendemail")
    public String sendEmail(@RequestBody Client user) {
        emailService.sendSimpleMessage(user.getEmail(), "Confirmation d'inscription une autre fois ", "Ceci est un email de confirmation de votre Réservation.");
        return "Email envoyé avec succès";
    }
}

