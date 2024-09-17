package net.engineeringdigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;

import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;


//RestController are the components that handle our HTTP request
@RestController
// requestmappingis used to
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
        @Autowired
    private UserRepository  userRepository;
@Autowired
private WeatherService weatherService;
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getall();
    }
        //MOVED IT TO PUBLIC CONTROLLER 
    // @PostMapping
    // public void createUser(@RequestBody User user) {
    //     userService.saveEntry(user);
    // }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user  ) {
        //if user hits this route it means he have authenticated 
        //there  username (which they are using to login)  is stored in context just we have to take username from their
        //no ned to paass it in pathvariable(/{username})
       Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
       String userName=authentication.getName();
        User userInDb = userService.findByuserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById()
    {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }
    @GetMapping("/greetings")
  public ResponseEntity<?> greeting(){
    Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
     WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
     String greeting="";
     if(weatherResponse!=null)
     {
        greeting="Weather feels like"+weatherResponse.getCurrent().getFeelslike();
     }
    return new ResponseEntity<>( "  Hello " + authentication.getName()+greeting  ,  HttpStatus.OK);

  }
    
}
