package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class EmailServiceTests {


    @Autowired
    private EmailService emailService;

   @Test
    void testSendEmail(){
        emailService.sendEmail("furkanchisti786@gmail.com", "Testing java sender", "hi bro!!!");


    }


}
