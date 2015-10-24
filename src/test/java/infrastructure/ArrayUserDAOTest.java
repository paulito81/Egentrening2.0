package infrastructure;

import console.Display;
import model.Type;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Paul on 21.10.2015.
 */
public class ArrayUserDAOTest {
    private ArrayUserDAO arrayUserDAO;


    @Before
    public void setUp() {
        arrayUserDAO = new ArrayUserDAO();

    }

    @Test
    public void tearDown() {
    }

    @Test
    public void testGetListOfAllUsers() throws Exception {

        System.out.println("\n1) Test skriv ut alle brukere: ");
        arrayUserDAO.createUser(new User(2, "per@yahoo.no", "passord123", Type.TEACHER));
        arrayUserDAO.createUser(new User(3, "kai@yahoo.no", "passord344", Type.STUDENT));
        arrayUserDAO.createUser(new User(4, "dia@yahoo.no", "passord555", Type.TEACHER));
        arrayUserDAO.createUser(new User(5, "pia@yahoo.no", "passord636", Type.STUDENT));

        int isNotNull = arrayUserDAO.getAllUsers().size();

        assertNotNull(isNotNull);
    }

    @Test
    public void testCreateUser() throws Exception {
        System.out.println("3) Test opprettet bruker: ");
        User user = arrayUserDAO.createUser(new User(2, "Fra@yahoo.no", "passord123", Type.TEACHER));
        assertTrue(user.getId() > 0);

    }

    @Test
    public void testUpdateUser() throws Exception {
        System.out.println("4) Teste oppdater/endre bruker: ");
        User user = new User("per@yahoo.no", "passord123", Type.TEACHER);
        User result = arrayUserDAO.createUser(user);
        assertTrue(result.getId() > 0);
    }

    @Test
    public void testGetUserById() throws Exception {
        User result = arrayUserDAO.createUser(new User("leo@yahoo.no", "passord1234", Type.TEACHER));

        Display display = new Display();
        Optional<User> user;
        user =  arrayUserDAO.getUserById(result.getId());
        display.getUserById(user);

        assertTrue(user.isPresent());
    }

    @Test
    public void testGetAllUsers() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testDeleteUser() throws Exception {
//TODO: Test goes here...
    }
}

