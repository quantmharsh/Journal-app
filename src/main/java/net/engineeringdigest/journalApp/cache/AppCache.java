package net.engineeringdigest.journalApp.cache;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import java.util.*;
@Component
public class AppCache {
     
    public Map<String , String> APP_CACHE= new HashMap<>();
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository ;

//PostConstruct is used to call init method at the time of object creation 
    @PostConstruct
    public void init()
    {
        List<ConfigJournalAppEntity>all = configJournalAppRepository.findAll();
        //store all key  value pair in our appCache
        for(ConfigJournalAppEntity  configJournalAppEntity:all)
        {
            APP_CACHE.put(configJournalAppEntity.getKey() , configJournalAppEntity.getValue());
            
        }
        
    }
    
}
