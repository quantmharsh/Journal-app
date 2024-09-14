package net.engineeringdigest.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;
import java.util.*;
@Document(collection = "users")
//generating all getters and setters using Lombok Data
@Data

public class User {
    
  //mapping id with mongodb id
     @Id
    private ObjectId   id ;
    //to make username unique  and getting NonNull  from lombok which while setter check that username , password 
    //is not null . if null throws null pointer exception
    @Indexed(unique = true)
    @NonNull
    private String  userName ;
    @NonNull
    private String password;
    //using Dbref to store list of journal entries OF  user in mongodb which is stored ion separate collection journalentries
    @DBRef
    private List<JournalEntry>  journalEntries= new ArrayList<>();
    private List<String> roles;
}
