package net.engineeringdigest.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;

//RestController are the components that handle our HTTP request
@RestController
// requestmappingis used to
@RequestMapping("/admin")
public class AdminController {

 @Autowired
    private UserService userService;
@GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all= userService.getall();
        if(all!=null && !all.isEmpty())
        {
                   return new  ResponseEntity<>(all , HttpStatus.OK);
        }
        return new  ResponseEntity<>(all , HttpStatus.NOT_FOUND);



    }
    @PostMapping("/create-admin-user")
    public void  createUser(@RequestBody User user)
    {
         userService.saveAdmin(user);
    }
    
}
