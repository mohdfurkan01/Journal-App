package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@Service
@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    //Atomicity agr sare ho rhe ho wo to successful ho sare agr ek bhi fail ho jaye to sare fail honge
    @Transactional //means execute the whole method and if anything fails then it will roll back
    public void saveEntry(JournalEntry journalEntry, String userName){
       try {
           User user = userService.findByUserName(userName); //find user
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved = journalEntryRepository.save(journalEntry); //save entry
           user.getJournalEntries().add(saved); //user's entry added
           //user.setUserName(null);
           userService.saveUser(user); //user saved in db with new journal entry
       } catch (Exception e){
           //System.out.println(e);
           log.error("e: ", e);
           throw new RuntimeException("An error occurred while saving the entry", e);

       }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
       try {
           User user = userService.findByUserName(userName); //find user
           removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
           if (removed) {
               userService.saveUser(user);
               journalEntryRepository.deleteById(id);
           }

       } catch (Exception e){
           log.error("Error",e);
           throw new RuntimeException("An error occurred while deleting the entry");
       }
       return removed;
    }
//    public List<JournalEntry> findByUserName(String userName){
//
//    }

}


//Class and Interface names always follow PascalCase (UpperCamelCase).
//Example: JournalEntryRepository (Interface Name)
//Variable names follow camelCase (lowerCamelCase).
//Example: journalEntryRepository (Object reference variable)