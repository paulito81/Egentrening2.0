package infrastructure;

import model.User;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 21.10.2015.
 */
public class JPADAO implements UserDAO {
    @PersistenceContext(unitName = "user")
    private EntityManagerFactory factory;
    private EntityManager entityManager;


    public JPADAO() throws ClassNotFoundException, SQLException{
        factory = Persistence.createEntityManagerFactory("user");
        entityManager = factory.createEntityManager();
    }

    JPADAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    private void close(){
        entityManager.close();
        factory.close();
    }

    @Override
    public boolean updateUser(User user) {
        if(user.getEmail() !=null ) {
            entityManager.persist(user);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createNamedQuery("User.getAllUsers", User.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteUser(int id) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.deleteUser", User.class);
        query.setParameter("id", id);
        int rows = query.executeUpdate();
        return rows == 1;
    }

}
