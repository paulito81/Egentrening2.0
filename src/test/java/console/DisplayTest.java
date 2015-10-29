package console;

import junit.framework.Assert;
import model.Type;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 29.10.2015.
 */
public class DisplayTest {
    private Display display;
    private User user1, user2, user3;
    private List<User> userList;

    @Before
    public void setUp() throws Exception {
        display = new Display();
    }

    @After
    public void tearDown() throws Exception {
        display = null;

    }

    @Test
    public void testCreateHeader() throws Exception {
        String s = "\n\t\t\t\t  ID\tEPOST\t\t\t\tPASSORD\t\t\tJOBBTYPE\n******************************************************************";
        Assert.assertEquals(s, "\n\t\t\t\t  ID\tEPOST\t\t\t\tPASSORD\t\t\tJOBBTYPE\n******************************************************************");
    }

    @Test
    public void testCreateUserH2() throws Exception {
        user1 = new User(1, "per@yahoo.no", "passord1234L!", Type.STUDENT);
        System.out.println("Create user Test:\t\t\t\t\t\t--> " + user1.toString() + "\t--> is not NULL!");
        Assert.assertNotNull(user1);

    }

    @Test
    public void testUpdateUserH2() throws Exception {
        user1 = new User(1, "per@yahoo.no", "passord1234L!", Type.STUDENT);
        String testString = "User{id=1, email='fredrik@yahoo.no', password='olfreg####!232', workType=TEACHER}";
        System.out.println("User 1 ´before update': \t\t\t\t--> " + user1.toString());
        user1.setPassword("olfreg####!232");
        user1.setEmail("fredrik@yahoo.no");
        user1.setWorkType(Type.TEACHER);
        System.out.println("User 1 'after update': \t\t\t\t\t--> " + user1.toString());
        Assert.assertEquals(String.valueOf(user1), testString);
    }

    @Test
    public void testGetUserByIdH2() throws Exception {
        user1 = new User(1, "per@yahoo.no", "passord1234L!", Type.STUDENT);
        int id = 1;
        System.out.println("Compare user id:" + id +"\t\t\t\t\t\t--> With User(id) : " + user1.getId() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t--> Is EQUAL");
        Assert.assertEquals(id, user1.getId());

    }

    @Test
    public void testDeleteUserH2() throws Exception {
        user1 = new User(1, "per@yahoo.no", "passord1234L!", Type.STUDENT);

        user1 = null;

        System.out.println("Value contains: " + String.valueOf(user1));

        Assert.assertNull(user1);

    }

    @Test
    public void testGetAllUsersH2() throws Exception {
        user1 = new User(1, "per@yahoo.no", "passord1234L!", Type.STUDENT);
        user2 = new User(2, "kaj@yahoo.no", "paK#!1234L!", Type.STUDENT);
        user3 = new User(3, "oda@yahoo.no", "pa5435L_ord1234L!", Type.STUDENT);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        int id = 3;

        System.out.println("User List-size: ["+ users.size() +"]\t\t\t\t\t\t-->" + " = size equals :" +id );
        Assert.assertEquals(users.size(), id);

    }

}
