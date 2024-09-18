package net.engineeringdigest.journalApp.repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
@Component
public interface ConfigJournalAppRepository extends  MongoRepository<ConfigJournalAppEntity , ObjectId> {
    
    
}