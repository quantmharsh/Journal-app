package net.engineeringdigest.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;

// In services we write all the logic . whereas controller acts as end points 
@Component
public class JournalEntryService {


    //This is knoiwn as dependency injection 
    //we are injectinf journalentryrepository in this class or service  so that we  can use it 
    //spring will automatically implement the interface and that will be injected inside this service or class

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    //creating a method to save  journalentry in db
    //getting .save method from mongodb with help of journalentryrepository
    public  void saveEntry(JournalEntry journalEntry , String userName){
        // 1. find the user
        User user= userService.findByuserName(userName);
        //s 2. save the journalEntry in journalentries collection
       JournalEntry saved=  journalEntryRepository.save(journalEntry);
       // 3. now add that saved journalentry in user's journalentries list 
       user.getJournalEntries().add(saved);
       // 4. at last save the user in user collectionss
       userService.saveEntry(user);
    }
    public  void saveEntry(JournalEntry journalEntry ){
       
        //save the journalEntry in journalentries collection
    journalEntryRepository.save(journalEntry);
     
    }
    public List<JournalEntry> getall(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(ObjectId id)
    {
        return  journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id  , String userName)
    {
         //find the user
         User user=userService.findByuserName(userName);
         user.getJournalEntries().removeIf(x -> x.getId().equals(id));
         userService.saveEntry(user);
         journalEntryRepository.deleteById(id);
    }
}
