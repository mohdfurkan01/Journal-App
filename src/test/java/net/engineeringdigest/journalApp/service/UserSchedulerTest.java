package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.schedular.UserSchedular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserSchedular userSchedular;


    @Test
    public void testFetchUsersAndSendMail() {
        userSchedular.fetchUsersAndSendMail();
    }
}
