package domain;

import infrastructure.UserDAO;
import model.User;

import java.util.List;

/**
 * Created by Paul on 22.10.2015.
 */
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserById(int i) {
        return userDAO.getUserById(i).get();
    }
}
