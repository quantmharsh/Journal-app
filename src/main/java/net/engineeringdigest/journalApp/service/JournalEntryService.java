package net.engineeringdigest.journalApp.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;

// In services we write all the logic . whereas controller acts as end points 
@Component
public class JournalEntryService {

    // This is knoiwn as dependency injection
    // we are injectinf journalentryrepository in this class or service so that we
    // can use it
    // spring will automatically implement the interface and that will be injected
    // inside this service or class

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    // creating a method to save journalentry in db
    // getting .save method from mongodb with help of journalentryrepository

    // Transactional wraps this method for each request into container and updates
    // data when every statement executes coreectly
    // if any lines fail it rollback all the changes that it had made


    // logger for  logging 
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            // 1. find the user
            User user = userService.findByuserName(userName);
            // s 2. save the journalEntry in journalentries collection
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            // 3. now add that saved journalentry in user's journalentries list
            user.getJournalEntries().add(saved);
            // if this line execurtes then journal will be saved in journalEntries but not
            // in user
            // so to overcome this issue we use Transactional annotation
            // user.setUserName(null);
            // 4. at last save the user in user collectionss
            userService.saveExistingUser(user);
            logger.info("Saved  journal to user ");
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("Error while savin journal entry");
            throw new RuntimeException("An error occured while saving journal entry");
        }
    }

    public void saveEntry(JournalEntry journalEntry) {

        // save the journalEntry in journalentries collection
        journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getall() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            // find the user
            User user = userService.findByuserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveExistingUser(user);
                journalEntryRepository.deleteById(id);
            }
            return removed;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("an error occured while deleting journal entry", e);
        }

    }
}
