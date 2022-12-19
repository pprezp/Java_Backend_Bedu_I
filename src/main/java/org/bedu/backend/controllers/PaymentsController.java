package org.bedu.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsController {

    @GetMapping("/payments")
    public String getPayments(){
        return "Accede Pagos";
    }

}