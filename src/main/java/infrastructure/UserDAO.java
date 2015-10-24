package infrastructure;

import model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 21.10.2015.
 */
public  interface UserDAO {
    User createUser(User user);
    boolean updateUser(User user);
    Optional<User> getUserById(int id);
    List<User> getAllUsers();
    boolean deleteUser(int id);
}