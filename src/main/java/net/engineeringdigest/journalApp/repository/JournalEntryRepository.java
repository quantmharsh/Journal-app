package net.engineeringdigest.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.JournalEntry;

//Controller --> Services-->Repository

//Mongorepository is a interface  which helps us  to use  mongodb in spring boot 
//it contains all the required methods to connect  and work with database
//first parameter contains data(class)  on which we are doing operations
//second parameter is Type of Id that we have in that data(class)
//MongoRepository contains all the methods to perform crud operations
@Component
public interface JournalEntryRepository extends  MongoRepository<JournalEntry  , ObjectId> {
    
}
