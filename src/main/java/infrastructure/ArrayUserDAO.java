package infrastructure;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 21.10.2015.
 */
@ArrayListQualifier
public class ArrayUserDAO implements UserDAO {
    private final List<User> users = new ArrayList<>();

    public ArrayUserDAO() {
    }

    @Override
    public User createUser(User user) {
        if (user != null) {
            int id = users.size() + 1;
            user.setId(id);
            users.add(user);
            return user;
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        users.set(user.getId() - 1, user);
        return true;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(users.get(id - 1));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean deleteUser(int id) {
        if (id >= 0) {
            users.remove(id - 1);
            return true;
        } else {
            return false;
        }
    }
}