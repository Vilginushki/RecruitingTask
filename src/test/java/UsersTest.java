import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.vilginushki.User;
import io.github.vilginushki.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UsersTest {

    @Test
    @DisplayName("Check output size")
    void testOutput(){
        assertEquals(UserClient.users().size(), 5, "Size of list should be 5");
    }

    @Test
    @DisplayName("Check attributes")
    void checkOutput(){
        List<User> usersList = UserClient.users();
        assert usersList != null;
        for (User u :
                usersList) {
            assertNotNull(u.getGender());
            assertNotNull(u.getLoginUUID());
            assertNotNull(u.getRegistrationDate());
            assertNotNull(u.getLastName());
            assertNotNull(u.getFirstName());
            assertNotNull(u.getCity());
        }
    }
}
