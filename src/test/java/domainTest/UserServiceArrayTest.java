package domainTest;

import model.Type;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Paul on 21.10.2015.
 */
public class UserServiceArrayTest {
    // user tests
    private User user1;
    private User user2;
    private User user3;
    private User user4;

    @Before
    public void setUp(){
        // id, mail, password, worktype
        user1 = new User(300,"peterpan@yahoo.no","123456789", Type.STUDENT );
        user2 = new User(301,"wendy@yahoo.no","abcdefgh", Type.STUDENT );
        user3 = new User(302,"james@yahoo.no","ijklmnopqr", Type.TEACHER );
        user4 = new User(303,"olav@yahoo.no","awsdjiklm", Type.TEACHER );

    }

    @After
    public void tearDown(){
        // delete all users.
        user1 = null;
        user2 = null;
        user3 = null;
        user4 = null;

    }


    @Test
    public void createUser() throws Exception {
        user1 = new User(300,"peterpan@yahoo.no","123456789", Type.STUDENT );
        Assert.assertEquals(String.valueOf(user1), "User{id=300, email='peterpan@yahoo.no', password='123456789', workType=STUDENT}");
    }
    @Test
    public void updateUser(){
        //TODO
    }
    @Test
    public void getUserByID(){
        //TODO get a user with id  -return user true

    }
    @Test
    public void getAllUsers(){
        //TODO get all users return a value not null
    }

    @Test
    public void deleteAUser(){
        //TODO return true if user id == 0 || notExist
    }


}