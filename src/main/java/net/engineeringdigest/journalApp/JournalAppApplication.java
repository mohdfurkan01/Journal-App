package net.engineeringdigest.journalApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


//@SpringBootApplication(scanBasePackages = "net.engineeringdigest.journalApp")
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "net.engineeringdigest.journalApp.repository") // Add this
public class JournalAppApplication {

	@Autowired
	private MongoTemplate mongoTemplate; // Add this for debugging

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JournalAppApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
//		String activeProfile = environment.getActiveProfiles()[0];
//		System.out.println("Getting the current ENV:"+ " " + activeProfile); //OR
		System.out.println("Getting the current ENV:"+ " " + environment.getActiveProfiles()[0]);
	}


	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
	//add can be any name


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

// PlatformTransactionManager
// MongoTransactionManager
//double shift press karne se search option aa jata h
