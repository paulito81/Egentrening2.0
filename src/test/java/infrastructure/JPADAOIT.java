package infrastructure;

import junit.framework.Assert;
import model.Type;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Paul on 22.10.2015.
 */
public class JPADAOIT {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private JPADAO jpadao;

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("user");
        entityManager = factory.createEntityManager();
        jpadao = new JPADAO(entityManager);
    }

    @After
    public void tearDown(){
        entityManager.close();
        factory.close();

    }

    @Test
    public void testCreateUser() throws Exception {
        entityManager.getTransaction().begin();
        User user = jpadao.createUser(new User("ola@yahoo.no", "rfask21asd!S", Type.STUDENT));
        entityManager.getTransaction().commit();
        assertNotNull(user);

        System.out.println("Test create user: \tID:" + user.getId() + "\tEmail:" + user.getEmail() + "\tPassword:" + user.getPassword() + "\tWorktype:" + user.getWorkType());
        assertTrue(user.getId() > 0);
    }

   @Test
    public void testUpdateUser() {
       entityManager.getTransaction().begin();
       boolean isUpdated = jpadao.updateUser(new User("olaa@hotmail.com", "password1234", Type.TEACHER));
       System.out.println("Test create user is :  " + isUpdated);

       Assert.assertTrue(isUpdated);
    }

    @Test
    public void testGetUserById() {
        User user = new User("olaa@hotmail.com", "password1234", Type.TEACHER);
        entityManager.getTransaction().begin();
        jpadao.createUser(user);

        Optional<User> getUserById = jpadao.getUserById(1);
        System.out.println(Optional.ofNullable(getUserById));

        System.out.println("TEST: " + getUserById);
        Assert.assertNotNull(Optional.ofNullable(getUserById));
    }

    @Test
    public void testGetAllUsers() {
        entityManager.getTransaction().begin();
        User user = jpadao.createUser(new User("ola@yahoo.no", "rfask21asd!S", Type.STUDENT));
        User user2 = jpadao.createUser(new User("eik@yahoo.no", "rfask21asd!S", Type.TEACHER));

        entityManager.getTransaction().commit();
        List<User> users = jpadao.getAllUsers();
        System.out.println("TESTING 'size of JPA-TABLE': " + users.size() + " = 2");
        Assert.assertTrue(users.size() == 2);

    }

    @Test
    public void testDeleteById() {
        entityManager.getTransaction().begin();
        User user = jpadao.createUser(new User("okkk@yahoo.no", "rfa888sk21asd!S", Type.TEACHER));

        int id = user.getId();

        System.out.println("TEST DELETE BY ID JPA: " + id);
//        boolean isDeleted = jpadao.deleteUser(1);
     //   System.out.println(isDeleted);
        entityManager.getTransaction().commit();
        /*
        for(User u : users){
            if(u.getId() != 0 || u.getWorkType() !=null || u.getPassword() !=null || u.getEmail() !=null) {
                System.out.println("ID: " + u.getId() + "\tEmail: " + u.getEmail() + "\tPassord: " + u.getPassword() + "\tWorktype:" + u.getWorkType());
            }
        }
        */
       // Assert.assertTrue(isDeleted);

        //TODO

    }
}