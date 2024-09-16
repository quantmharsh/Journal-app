package net.engineeringdigest.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;

//sprinboottest annotation  is used to run test cases as well as 
//to run or main journalApplication
//if we will not mention this then our main server will not run

//JUnit Testing 
@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void  testFindByUserName()
    {
        User user = userRepository.findByuserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
        
    }
    @ParameterizedTest
    @CsvSource(
        {
        "1,2,3",
        "3,4,7",
         "3,3,6"
        })
        public void  addtest(int a , int b , int expected)
        {
            assertEquals(expected, a+b);
        }
    
}
