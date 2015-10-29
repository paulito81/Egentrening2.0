package infrastructure;

import console.Display;
import infrastructure.user.ArrayUserDAO;
import model.Type;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        arrayUserDAO = null;
    }

    @Test
    public void testGetListOfAllUsers() throws Exception {

        System.out.println("\n1) Test skriv ut alle brukere: ");
        arrayUserDAO.createUser(new User(2, "per@yahoo.no", "passord123", Type.TEACHER));
        arrayUserDAO.createUser(new User(3, "kai@yahoo.no", "passord344", Type.STUDENT));
        arrayUserDAO.createUser(new User(4, "dia@yahoo.no", "passord555", Type.TEACHER));
        arrayUserDAO.createUser(new User(5, "pia@yahoo.no", "passord636", Type.STUDENT));

        int isNotNull = arrayUserDAO.getAllUsers().size();

        Assert.assertNotNull(isNotNull);
    }

    @Test
    public void testCreateUser() throws Exception {
        System.out.println("3) Test opprettet bruker: ");
        User user = arrayUserDAO.createUser(new User(2, "Fra@yahoo.no", "passord123", Type.TEACHER));
        Assert.assertTrue(user.getId() > 0);

    }

    @Test
    public void testUpdateUser() throws Exception {
        System.out.println("4) Teste oppdater/endre bruker: ");
        User user = new User("per@yahoo.no", "passord123", Type.TEACHER);
        User result = arrayUserDAO.createUser(user);
        Assert.assertTrue(result.getId() > 0);
    }

    @Test
    public void testGetUserById() throws Exception {
        User result = arrayUserDAO.createUser(new User("leo@yahoo.no", "passord1234", Type.TEACHER));

        Display display = new Display();
        Optional<User> user;
        user =  arrayUserDAO.getUserById(result.getId());
       // display.getUserById(user);
        System.out.println("5) Test get user-by-Id; -->\t " + user.get().toString());
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        User user  = new User(2, "per@yahoo.no", "passord123", Type.TEACHER);
        arrayUserDAO.createUser(user);
        userList.add(user);
        User user2 = new User(3, "kai@yahoo.no", "passord344", Type.STUDENT);
        userList.add(user2);
        arrayUserDAO.createUser(user2);
        User user3 = new User(4, "dia@yahoo.no", "passord555", Type.TEACHER);
        userList.add(user3);
        arrayUserDAO.createUser(user3);
        System.out.println("6) Test get-all-users ArrayDAO: " + arrayUserDAO.getAllUsers().size()+ " is equal to " + userList.size() );
        Assert.assertEquals(arrayUserDAO.getAllUsers(), userList );
    }

    @Test
    public void testDeleteUser() throws Exception {
//TODO: Test goes here...
    }
}

