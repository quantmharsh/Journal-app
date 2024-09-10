package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;

import java.util.Map;
import java.util.HashMap;
import java.util.*;
//RestController are the components that handle our HTTP request
@RestController
// requestmappingis used to create  end point
@RequestMapping("/journal")
public class  JournalEntryController{

    private Map<Long,JournalEntry> journalEntries= new HashMap<>();


    //localhost:8080/journal/get-journals
    @GetMapping("/get-journals")
    public  List<JournalEntry> getall()
    {
        return new ArrayList<>(journalEntries.values());
    }
    // {myId} is a pathvariable which changes /journals/id/1or2
@GetMapping("/id/{myId}")
public  JournalEntry getJournalEntry(@PathVariable  Long myId)
{
  return  journalEntries.get(myId);
}

    //localhost:8080/journal/create
    //if not using getmapping in both methods then when we will send get request to /journal then getall()
    //will be w=execurted if we send post request then createEntry
    @PostMapping("/create")
    public void createEntry( @RequestBody JournalEntry myEntry){
        journalEntries.put( myEntry.getId(), myEntry);

    }
    @DeleteMapping("/id/{myId}")
    public  JournalEntry deleteJournalEntryById(@PathVariable Long myId)
    {
        return journalEntries.remove(myId);
    }

    @PutMapping("/update/{myId}")
    public JournalEntry updateJournalById(@PathVariable Long myId  , @RequestBody JournalEntry myEntry )
    {
        return journalEntries.put(myId, myEntry);
    }



}
