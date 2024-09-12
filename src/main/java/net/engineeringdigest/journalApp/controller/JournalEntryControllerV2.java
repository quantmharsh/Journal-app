package net.engineeringdigest.journalApp.controller;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

//RestController are the components that handle our HTTP request
@RestController
// requestmappingis used to
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;
    

    // localhost:8080/journal/get-journals
    // //it calls journalEntryService.getall method which with the help og mongorepository gets all the data
    // @GetMapping("/get-journals")
    // public List<JournalEntry> getall() {
    //       return  journalEntryService.getall();
       
    // }
    
    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesofUser(@PathVariable String userName) {
        //finding the user in db
        User user = userService.findByuserName(userName);
        //with that user getting all its journalentries
        //getting this getjournalEntries method from  getter method in User entity which is  created by lombok
         List<JournalEntry> all= user.getJournalEntries();
         if(all!=null  && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
         }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
              
         }
       
    }

    // {myId} is a pathvariable which changes /journals/id/1or2
    // @GetMapping("/id/{myId}")
    // public JournalEntry getJournalEntry(@PathVariable ObjectId myId) {
    //     return  journalEntryService.getById(myId).orElse(null);
        
    // }
    @GetMapping("/id/{myId}")
    public ResponseEntity< JournalEntry> getJournalEntry(@PathVariable ObjectId myId) {
          Optional<JournalEntry> journalEntry=  journalEntryService.getById(myId);
         if(journalEntry.isPresent()){
             return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
         }
         return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        
    }

    // localhost:8080/journal/create
    // if not using getmapping in both methods then when we will send get request to
    // /journal then getall()
    // will be w=execurted if we send post request then createEntry
    // @PostMapping("/create")
    // public boolean  createEntry(@RequestBody JournalEntry myEntry) {
    //     myEntry.setDate(LocalDateTime.now());
    //     journalEntryService.saveEntry(myEntry);
    //     return true;
      

    // }
    //ResponseEntity is used to send status code with reponse body
    //it consists of data that we are returning and status code 
    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry>  createEntry(@RequestBody JournalEntry myEntry  , @PathVariable String userName)  {
       try {
        
         myEntry.setDate(LocalDateTime.now());
         journalEntryService.saveEntry(myEntry , userName);
         return new  ResponseEntity<>(myEntry , HttpStatus.CREATED);    
       } catch (Exception e) {
        // TODO: handle exception
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

       }
       
      

    }

    // @DeleteMapping("/id/{myId}")
    // public boolean deleteJournalEntryById(@PathVariable ObjectId myId) {
    //     journalEntryService.deleteById(myId);
    //     return true;
       
        
    //  ? means wildcard we can return any other object also instead   of journalEntry
    @DeleteMapping("/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId  ,@PathVariable String userName) {
        journalEntryService.deleteById(myId , userName);
        return  new  ResponseEntity<>(HttpStatus.NO_CONTENT);
       
        
    }

//     @PutMapping("/update/{myId}")
//     public JournalEntry updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
//         JournalEntry  old = journalEntryService.getById(myId).orElse(null);
//         if(old!=null){
//             old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle():old.getTitle());
//             old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent():old.getContent());


//     }
//     journalEntryService.saveEntry(old);
//     return old;

// }
@PutMapping("/update/{userName}/{myId}")
public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId myId,@PathVariable String userName, @RequestBody JournalEntry newEntry) {
    JournalEntry  old = journalEntryService.getById(myId).orElse(null);
    if(old!=null){
        old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle():old.getTitle());
        old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent():old.getContent());
         //if we have got the entrty that we have to update then  update it and send response as ok
        journalEntryService.saveEntry(old);
        return   new ResponseEntity<>(old , HttpStatus.OK);
}
//if we not found the  entry then send not found status code.
return new ResponseEntity<>(HttpStatus.NOT_FOUND);


}
}
