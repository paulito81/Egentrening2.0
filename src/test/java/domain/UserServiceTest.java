package domain;

import infrastructure.user.UserDAO;
import model.Type;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Paul on 22.10.2015.
 */
public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(new UserDAO() {
            @Override
            public User createUser(User user) {
                return new User(1, "per@test.com", "123", Type.STUDENT);
            }

            @Override
            public boolean updateUser(User user) {
                return true;
            }

            @Override
            public Optional<User> getUserById(int id) {
                return Optional.of(new User(1, "per@test.com", "123", Type.STUDENT));
            }

            @Override
            public List<User> getAllUsers() {
                return null;
            }

            @Override
            public boolean deleteUser(int id) {
                return false;
            }
        });
    }

    @Test
    public void getUserById() throws Exception {
        User user = userService.getUserById(1);
        assertEquals("per@test.com", user.getEmail());

    }
}