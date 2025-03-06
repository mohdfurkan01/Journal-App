

package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.mockito.Mockito.when;

//@SpringBootTest    //just disable this to prevent the test
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    //    public UserDetailsServiceImplTests() {
//        MockitoAnnotations.openMocks(this); // Initialize mocks
//    }
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    // ✅ Helper method for creating mock users
    private User createMockUser() {
        return new User("Ahsan", "sdoifna", List.of("USER"));
    }

    // @Test  //just disable this to prevent the test
    void loadUserByUsernameTest() {
        User mockUser = createMockUser(); // ✅ Use the helper method

        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        UserDetails user = userDetailsService.loadUserByUsername("Ahsan");

        Assertions.assertNotNull(user);
        Assertions.assertEquals("Ahsan", user.getUsername());
        Assertions.assertEquals("sdoifna", user.getPassword());
    }
}



