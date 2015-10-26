package model;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Paul on 21.10.2015.
 */
public class UserTest {

    private User user;

    @Before
    public void before() throws Exception {
         user = new User(1, "ola@yahoo.no", "passrod1232!!", Type.STUDENT);
    }

    @After
    public void after() throws Exception {
        user = null;
    }

    /**
     * Method: getId()
     */
    @Test
    public void testGetId() throws Exception {
//
            int id = user.getId();
        System.out.println("Test ID: " + id + " = " + user.getId());
        Assert.assertEquals(id, 1);
    }

    /**
     * Method: setId(int id)
     */
    @Test
    public void testSetId() throws Exception {
        int id = 2;
        System.out.println("TEST 2(ID): Før endring   \t " + user.toString() );
        user.setId(id);
        System.out.println("TEST 2(ID): Etter endring \t " + user.toString());
        Assert.assertTrue(user.getId() == 2);
    }

    /**
     * Method: getEmail()
     */
    @Test
    public void testGetEmail() throws Exception {
        String getEmail =    user.getEmail();
        System.out.println("Test 3: " + getEmail + " = " + user.getEmail());
        Assert.assertEquals(getEmail, "ola@yahoo.no");

    }

    /**
     * Method: setEmail(String email)
     */
    @Test
    public void testSetEmail() throws Exception {
        String s = user.getEmail();
        System.out.println("TEST 4: Før endring av (email): " + user.getEmail());
        user.setEmail("fredrik@yhaoo.no");
        System.out.println("TEST 4: Etter endring av (email): " + user.getEmail());

        Assert.assertNotSame(s, user.getEmail());

    }

    /**
     * Method: getPassword()
     */
    @Test
    public void testGetPassword() throws Exception {

        String getPass = user.getPassword();
        System.out.println("TEST 5: " + getPass + " = " + user.getPassword());
        Assert.assertEquals(user.getPassword(), "passrod1232!!");


    }

    /**
     * Method: setPassword(String password)
     */
    @Test
    public void testSetPassword() throws Exception {

        String s = user.getPassword();
        System.out.println("TEST 6: before (Password): " + user.getPassword());
        user.setPassword("fdsfksdk!FERE3432");
        System.out.println("TEST 6: after (Password): " + user.getPassword());
        Assert.assertNotSame(s, user.getPassword());
    }

    /**
     * Method: getWorkType()
     */
    @Test
    public void testGetWorkType() throws Exception {
            Type getWork = user.getWorkType();
        System.out.println("TEST 7: NotNull (getWork) :" + getWork);

        Assert.assertNotNull(getWork);

    }

    /**
     * Method: setWorkType(Type workType)
     */
    @Test
    public void testSetWorkType() throws Exception {
        Type setWork = user.getWorkType();
        System.out.println("TEST 8: Before (setWork): " +setWork);
        user.setWorkType(Type.TEACHER);
        System.out.println("TEST 8: After (setWork) " + user.getWorkType());
        Assert.assertNotSame(setWork, user.getWorkType());

    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
            String s = user.toString();
            System.out.println("TEST 9: NotNull: \t\t" + s);
            Assert.assertNotNull(s);
    }
}