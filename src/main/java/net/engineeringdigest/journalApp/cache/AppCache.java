package net.engineeringdigest.journalApp.cache;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {


    @Autowired
    private ConfigJournalRepository configJournalRepository;

    public Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigJournalAppEntity> all = configJournalRepository.findAll();

        for (ConfigJournalAppEntity configJournalAppEntity : all){
            System.out.println("Loaded config: " + configJournalAppEntity.getKey() + " = " + configJournalAppEntity.getValue());
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }

        //List<ConfigJournalAppEntity> all = configJournalRepository.findAll();
//      System.out.println("----- CONFIG VALUES FROM DB -----");
//        for (ConfigJournalAppEntity config : all) {
//            System.out.println("Raw config: " + config);
//            APP_CACHE.put(config.getKey(), config.getValue());
//        }
//        System.out.println("----- APP_CACHE Loaded Keys -----");
//        APP_CACHE.forEach((k, v) -> System.out.println(k + " => " + v));


    }
}
