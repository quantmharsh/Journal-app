package net.engineeringdigest.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;

// In services we write all the logic . whereas controller acts as end points 
@Component
public class  UserService {


    //This is knoiwn as dependency injection 
    //we are injectinf journalentryrepository in this class or service  so that we  can use it 
    //spring will automatically implement the interface and that will be injected inside this service or class

    @Autowired
    private UserRepository  userRepository;
    //creating a method to save  journalentry in db
    //getting .save method from mongodb with help of journalentryrepository

    public static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    public  void saveEntry( User  user){
        //incrypt the password and then store it in database 
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public List<User> getall(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id)
    {
        return  userRepository.findById(id);
    }
    public void deleteById(ObjectId id)
    {
        userRepository.deleteById(id);
    }
    public User  findByuserName(String username)
    {
         return userRepository.findByuserName(username);
    }
}
