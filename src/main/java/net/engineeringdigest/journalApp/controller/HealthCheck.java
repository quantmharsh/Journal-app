package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {


    //getMapping is used to send get request to the server
    @GetMapping("/health-check")
    public  String  healthCheck(){
        return "OK";
    }

    
}
