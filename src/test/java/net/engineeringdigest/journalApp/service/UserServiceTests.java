

package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    //private UserService userService;

//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentsProvider.class)
//    public void testSaveNewUser(User user){
//        assertTrue(userService.saveUser(user));
//    }

//    @BeforeEach
//    void setUp(){
//
//    }

    //@Disabled //now it won't be executed
    //@ParameterizedTest
    @ValueSource(strings={
            "furkan",
            "Ahsan",
            "Hamza"

    })
    public void testFindByUserName(String name){
        //assertEquals(4,2+2);
        //assertNotNull(userRepository.findByUserName("furkan"));
        //User user = userRepository.findByUserName("Ahsan");
        // assertFalse(user.getJournalEntries().isEmpty());
        assertNotNull(userRepository.findByUserName(name),"failed for:" +name); //testing for not null
    }

    // @ParameterizedTest
    @CsvSource({
            "1,1,2", //a's value, b's value and c's value
            "2,10,12",
            "3,3,6" //it will fail because 3+3=6 not 7
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}

//test ko run karne k liye manually iss file me aakr chalana h tab result dikhega
// otherwise sirf application start ho jayega test case nhi dikhenge
//(aur jis file me bhi test krna ussi file me ja kr run krna h: REMEMBER IT CAREFULLY
