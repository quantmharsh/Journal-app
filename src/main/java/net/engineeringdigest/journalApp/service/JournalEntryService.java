package net.engineeringdigest.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;

// In services we write all the logic . whereas controller acts as end points 
@Component
public class JournalEntryService {


    //This is knoiwn as dependency injection 
    //we are injectinf journalentryrepository in this class or service  so that we  can use it 
    //spring will automatically implement the interface and that will be injected inside this service or class

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    //creating a method to save  journalentry in db
    //getting .save method from mongodb with help of journalentryrepository
    public  void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getall(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(ObjectId id)
    {
        return  journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id)
    {
         journalEntryRepository.deleteById(id);
    }
}
