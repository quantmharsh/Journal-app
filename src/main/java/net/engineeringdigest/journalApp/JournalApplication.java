package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

//EnableTransactioNmanagement is used to check methods which have transaction annotation  on it and wrap that method in 
//1 container which either updates everywhere or rollback 
@EnableTransactionManagement
@SpringBootApplication
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

    // creating a bean by implementing PlatformTransactionManager interface 
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
        return new  MongoTransactionManager(dbFactory);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new   RestTemplate();
    }


}