package net.engineeringdigest.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.*;

//RestController are the components that handle our HTTP request
@RestController
// requestmappingis used to
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;
    

    // localhost:8080/journal/get-journals
    //it calls journalEntryService.getall method which with the help og mongorepository gets all the data
    @GetMapping("/get-journals")
    public List<JournalEntry> getall() {
          return  journalEntryService.getall();
       
    }

    // {myId} is a pathvariable which changes /journals/id/1or2
    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntry(@PathVariable ObjectId myId) {
        return  journalEntryService.getById(myId).orElse(null);
        
    }

    // localhost:8080/journal/create
    // if not using getmapping in both methods then when we will send get request to
    // /journal then getall()
    // will be w=execurted if we send post request then createEntry
    @PostMapping("/create")
    public boolean  createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
      

    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return true;
       
        
    }

    @PutMapping("/update/{myId}")
    public JournalEntry updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        JournalEntry  old = journalEntryService.getById(myId).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent():old.getContent());


    }
    journalEntryService.saveEntry(old);
    return old;

}
}
