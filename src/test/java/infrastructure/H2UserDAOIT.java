package infrastructure;

import model.Type;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by Paul on 21.10.2015.
 */
public class H2UserDAOIT {
    private H2UserDAO h2UserDAO;


    @Before
    public void setUp() {

        h2UserDAO = new H2UserDAO();

    }

    @After
    public void tearDown() {
        h2UserDAO.closeConnectionToH2();
    }

    @Test
    public void createANewUser() {
        User result = h2UserDAO.createUser(new User(2, "ole@yahoo.no", "passord", Type.STUDENT));
        Optional<User> user = h2UserDAO.getUserById(2);
        System.out.println("1) Test opprettelse av ´ny bruker: \tID: " + user.get().getId() + "\tEpost: " + user.get().getEmail() + "\tPassord: " + user.get().getPassword() + "\tJobb: " + user.get().getWorkType());
        assertTrue(result.getId() > 0);
    }

    @Test
    public void updateAUser() {
        Optional<User> user = h2UserDAO.getUserById(1);
        if (user.isPresent()) {
            System.out.println("2) Test oppdatering av ´bruker X´: \tID: " + user.get().getId() + "\tEpost: " + user.get().getEmail() + "\tPassord: " + user.get().getPassword() + "\tJobb: " + user.get().getWorkType());
        }

        boolean isUpdated = h2UserDAO.updateUser(new User(1, "fredrik@yahoo.no", "gutt1234", Type.STUDENT));
        assertTrue(isUpdated);
    }

    @Test
    public void getAUserById() {
        Optional<User> user = h2UserDAO.getUserById(1);
        if (user.isPresent()) {
            System.out.println("3) Test hente bruker med ´ID X´: \tID: " + user.get().getId() + "\tEpost: " + user.get().getEmail() + "\tPassord: " + user.get().getPassword() + "\tJobb: " + user.get().getWorkType());
        }
        user = h2UserDAO.getUserById(1);
        assertNotNull(user);
    }

    @Test
    public void getAllUsers() {

        h2UserDAO.createUser(new User(2, "ole@yahoo.no", "passord0", Type.STUDENT));
        h2UserDAO.createUser(new User(3, "per@yahoo.no", "passord2", Type.TEACHER));
        h2UserDAO.createUser(new User(4, "knu@yahoo.no", "passord3", Type.TEACHER));
        h2UserDAO.createUser(new User(5, "rut@yahoo.no", "passord4", Type.STUDENT));

        for (User user : h2UserDAO.getAllUsers()) {
            System.out.println("4) Teste hente alle brukere´ \t\tID: " + user.getId() + "\tEpost: " + user.getEmail() + " Passord: " + user.getPassword() + "\tJobb: " + user.getWorkType());
        }
        assertTrue(!h2UserDAO.getAllUsers().isEmpty());


    }

    @Test
    public void getAllUsersIsCopied() {
        List<User> listOfUsers = new ArrayList<>();
        h2UserDAO.createUser(new User(2, "ole@yahoo.no", "passord0", Type.STUDENT));
        h2UserDAO.createUser(new User(3, "per@yahoo.no", "passord2", Type.TEACHER));
        h2UserDAO.createUser(new User(4, "knu@yahoo.no", "passord3", Type.TEACHER));
        h2UserDAO.createUser(new User(5, "rut@yahoo.no", "passord4", Type.STUDENT));

        listOfUsers.addAll(h2UserDAO.getAllUsers().stream().collect(Collectors.toList()));
        System.out.println("5) Teste at størrelsen på fil er like stor som den som ble kopiert\t\tH2-DB:  " + h2UserDAO.getAllUsers().size() + "\tLokal List<User>: " + listOfUsers.size());

        assertEquals(h2UserDAO.getAllUsers().size(), listOfUsers.size());

    }

    @Test
    public void deleteAUser() {
        Optional<User> user;
        user = h2UserDAO.getUserById(1);
        boolean isDeleted = h2UserDAO.deleteUser(1);
        System.out.println("6) Sletter bruker med id: \t\t\tID: " + user.get().getId() + "\tEpost: " + user.get().getEmail() + "\tPassord: " + user.get().getPassword() + "\tJobb: " + user.get().getWorkType() + "\t -> :H2 størrelse: " + h2UserDAO.getAllUsers().size());

        assertTrue(isDeleted);
    }

    @Test
    public void deleteTable() {

        h2UserDAO.createUser(new User(20, "ole@yahoo.no", "passord0", Type.STUDENT));
        h2UserDAO.createUser(new User(21, "per@yahoo.no", "passord2", Type.TEACHER));
        h2UserDAO.createUser(new User(22, "knu@yahoo.no", "passord3", Type.TEACHER));
        h2UserDAO.createUser(new User(23, "rut@yahoo.no", "passord4", Type.STUDENT));

        boolean isDeletedTable = h2UserDAO.dropTable("User");
        assertTrue(isDeletedTable);


    }
}