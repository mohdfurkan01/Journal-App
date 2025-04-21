package net.engineeringdigest.journalApp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Document(collection  = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true) //userName will be unique
    @NonNull //userName can't be null
    private String userName;
    private String email;
    private boolean sentimentAnalysis;
    @NonNull //pwd can't be null
    private String password;

    @DBRef
    private List<JournalEntry>journalEntries = new ArrayList<>();
    private List<String> roles;




    /**
     *  ✅ Custom constructor without 'id' for testing purposes for this file "UserDetailsServiceImplTests"
     * Constructor without ID for testing purposes.
     * Avoid using in production if ID is required.
     * ❌ Remove It if: it will create a problem.
     * You don't want anyone to accidentally create a User object without an id in production.
     */
    public User(String userName, String password, List<String> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }



}
