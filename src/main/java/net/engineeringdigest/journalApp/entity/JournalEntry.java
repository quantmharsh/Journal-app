package net.engineeringdigest.journalApp.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
    // for ORM we need to map  it to our database  so that we can perform crud operatiosn
@Document(collection = "journal_entries")
//getting  from lombok which generates all getters , setters and constructor automatically
@Data
@NoArgsConstructor
public class JournalEntry {

  //generating all getter and setter methods using  LOMBOK which we have installed using maven dependencies
 


 
    //id will be unique key in mongodb which we  will get difrrent datas
    // @ID  is used to map id field to mongoDb id 
@Id
    private ObjectId id ;
    private String content;
    private String title;
    private LocalDateTime date;
    // public ObjectId getId() {
    //     return id;
    // }

    // public void setId(ObjectId id) {
    //     this.id = id;
    // }
    // public LocalDateTime getDate(){
    //     return date;
    // }
    // public void setDate(LocalDateTime date)
    // {
    //     this.date=date;
    // }

    // public String getContent() {
    //     return content;
    // }

    // public void setContent(String content) {
    //     this.content = content;
    // }

    // public String getTitle() {
    //     return title;
    // }

    // public void setTitle(String title) {
    //     this.title = title;
    // }


    
}
