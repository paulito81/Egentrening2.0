package console;

import infrastructure.H2DAOQualifier;
import infrastructure.UserDAO;
import model.Type;
import model.User;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 21.10.2015.
 */
public class UserH2Tester {
    //PRINT OUT
    private Display display = new Display();

    @Inject @H2DAOQualifier
    private UserDAO userDAO;

    UserH2Tester(){
    }

    public void execute(){
        System.out.println("-----H2 PRINTOUT:-----------------------------------------------");
        createUserH2(3, "ola@yahoo.no", "passord8", Type.STUDENT);
        createUserH2(4, "Gun@yahoo.no", "passord2", Type.TEACHER);
        createUserH2(5, "kei@yahoo.no", "passord3", Type.STUDENT);
        createUserH2(6, "lars@yahoo.no", "passord4", Type.TEACHER);
        createUserH2(7, "silje@yahoo.no", "passord5", Type.STUDENT);
        System.out.println("\n");
        getAllUsersH2();
        updateUserH2(new User(2, "Gun@yahoo.no", "passordrr", Type.STUDENT));
        getUserByIDH2(4);
        System.out.println("\n");
        deleteAUserH2(4);
        getAllUsersH2();
        dropTable("User");

    }

    /*
        H2DATABASE
        CRUD OPERATIONS

     */
    public boolean createUserH2(int id, String email, String password, Type workType) {

        if (id != 0 || email != null || password != null || workType != null) {
            User user = new User(id, email, password, workType);

            userDAO.createUser(user);
            if(!userDAO.getAllUsers().isEmpty()) {
                display.createUserH2(user);
            }

            return true;

        } else
            return false;
    }

    public boolean updateUserH2(User user) {
        if (user != null) {

            userDAO.updateUser(user);
            display.updateUserH2(user);
            return true;
        } else
            return false;
    }

    public Optional<User> getUserByIDH2(int id) {
        if (id != 0) {
            display.getUserByIdH2(userDAO.getUserById(id));
            return userDAO.getUserById(id);
        } else
            return null;
    }

    public List<User> getAllUsersH2() {

        display.getAllUsersH2(userDAO.getAllUsers());

        return userDAO.getAllUsers();

    }

    public boolean deleteAUserH2(int id) {
        display.deleteUserH2(id);
        return id != 0 && userDAO.deleteUser(id);
    }

    public void dropTable(String tableName) {

        display.dropTable(tableName);

    }

}
