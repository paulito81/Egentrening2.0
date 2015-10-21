package infrastructure;

import model.Type;
import model.User;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 21.10.2015.
 */
public class JPADAO implements UserDAO {
    private EntityManagerFactory factory;
    private EntityManager entityManager;


    public JPADAO() throws ClassNotFoundException, SQLException{

        factory = Persistence.createEntityManagerFactory("user");
        entityManager = factory.createEntityManager();
    }
    @Override
    public boolean createUser(User user) {
        user = new User();
        user.setId(2);
        user.setEmail("ola@yahoo.no");
        user.setPassword("1234jfdg!KLE");
        user.setWorkType(Type.STUDENT);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();

        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.id = 2", User.class);
        System.out.println("\nPERSISTED OBJECT: " + query.getResultList().get(0));

        return false;
    }

    private void close(){
        entityManager.close();
        factory.close();
    }

    @Override
    public boolean updateUser(User user) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.password like :password", User.class);
        query.setParameter("password", "kflwekf232dSW%");
        List<User> results = query.getResultList();
        System.out.println("\nPassword update QUERY 2: ");
        results.forEach(System.out::println);
        return false;
    }

    @Override
    public Optional<User> getUserById(int id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.id = 2", User.class);
        List<User> results = query.getResultList();
        System.out.println("\nNAMED QUERY 1: ");
        results.forEach(System.out::println);
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        //TODO!!
        TypedQuery<User> query = entityManager.createQuery("SELECT FROM USER WHERE user.id = 2", User.class);
        query.setParameter(1, "ola@yahoo.no");
        List list = query.getResultList();
        System.out.println("\nGET ALL QUERY: " + list.get(0));
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        TypedQuery<User> query = entityManager.createQuery("DELETE FROM User WHERE User.id = 2", User.class);
        List list = query.getResultList();
        System.out.println("\nDELETE QUERY: " + list.get(0));
        return false;
    }
}
