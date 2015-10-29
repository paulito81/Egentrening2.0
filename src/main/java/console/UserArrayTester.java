package console;

import infrastructure.qualifier.ArrayListQualifier;
import infrastructure.user.UserDAO;
import model.Type;
import model.User;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 21.10.2015.
 */

public class UserArrayTester {

    @Inject @ArrayListQualifier
    private UserDAO userDAO;

    //PRINT OUT
    private Display display = new Display();


    UserArrayTester() {
        display.createHeader();
    }

    public void execute() {
        System.out.println("--------ARRAYLIST PRINTOUT:-------------------------------------");
        createUser(1, "ola@yahoo.no", "passord8", Type.STUDENT);
        createUser(2, "Gun@yahoo.no", "passord2", Type.TEACHER);
        createUser(3, "kei@yahoo.no", "passord3", Type.STUDENT);
        createUser(4, "lars@yahoo.no", "passord4", Type.TEACHER);
        createUser(5, "silje@yahoo.no", "passord5", Type.STUDENT);
        getAllUsers();
        System.out.println("\n");
        getUserByID(4);
        updateUser(4, "erik@yahoo.no", "passord4", Type.STUDENT);
        deleteAUser(4);
        getAllUsers();
        System.out.println("---------------------------------------------------------------------\n");

    }


    /*
        ARRAYLIST
        CRUD OPPERATIONS

     */
    public void createUser(int id, String email, String password, Type workType) {
        if (id != 0 || email != null || password != null || workType != null) {

            User user = new User(id, email, password, workType);
            display.createUser(user);
            userDAO.createUser(user);
        } else
            System.out.println("Your value is invalid it contains 0!");
    }

    public boolean updateUser(User user) {
        if (user != null) {
            userDAO.updateUser(user);
            return true;
        }

        return false;

    }

    public boolean updateUser(int id, String email, String password, Type workType) {
        if (id != 0 || email != null || password != null || workType != null) {

            User user = new User(id, email, password, workType);

            updateUser(user);
            return true;
        } else
            display.updateUser(null);
        return false;
    }

    public Optional<User> getUserByID(int id) {
        Optional<User> user;
        if (id != 0) {

            user = userDAO.getUserById(id);
            display.getUserById(user);

            return userDAO.getUserById(id);
        } else
            return null;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean deleteAUser(int id) {
        return userDAO.deleteUser(id);
    }
}