package net.engineeringdigest.journalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection  = "config_journal_app")
@Data
@NoArgsConstructor

public class ConfigJournalAppEntity {

    @Id
    private String id;

    @Field("key")
    private String key;

    @Field("value")
    private String value;



}
