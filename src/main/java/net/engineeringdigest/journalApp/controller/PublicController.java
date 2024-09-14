package net.engineeringdigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
        @Autowired
    private UserService userService;


    //getMapping is used to send get request to the server
    @GetMapping("/health-check")
    public  String  healthCheck(){
        return "OK";
    }
    //we have brought  create user method from userrcontroller to here
    //because we want this route to be public and all other usser related route to be authenticated 
    
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    
}
