package net.engineeringdigest.journalApp.service;


import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Service
@Component
@Slf4j
public class UserService {


    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // "private static final" ek hi instance bane aur accidental reassignment na ho
    //To print the logs class name same hona chahiye (UserService.class)
    //private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public void saveNewUser(User user){
        try {
            //user.getPassword(passwordEncoder.encode(user.getPassword()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e){
            //log.error("Error occurred for {}:",user.getUserName() ,e); //by annotation
            //logger.error("Error occurred for {}:",user.getUserName() ,e); //printed Log: Error occurred for testUser:
            log.error("This is for error===>");
            log.warn("This is for waning");
            log.info("This is for information");
            log.debug("This is for debugging");
            log.trace("This is for tracing");

        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

    public boolean saveUser(User user){
        userRepository.save(user);
        return false;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
         userRepository.deleteById(id);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}


//Class and Interface names always follow PascalCase (UpperCamelCase).
//Example: JournalEntryRepository (Interface Name)
//Variable names follow camelCase (lowerCamelCase).
//Example: journalEntryRepository (Object reference variable)