package infrastructure.user;


import infrastructure.Qualifier.H2DAOQualifier;
import model.Type;
import model.User;

import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 21.10.2015.
 */
@H2DAOQualifier
public class H2UserDAO implements UserDAO {

    private static Connection connection;


    public H2UserDAO() {
        connectToH2();
    }

    private synchronized void connectToH2() {

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:userDB;INIT=runscript from 'init.sql'", "sa", "");


        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @PreDestroy
    public  void closeConnectionToH2() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized User createUser(User user) {


        int id = getNextId();
        String sqlInsert = "INSERT INTO USER VALUES(?, ?, ?, ?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getWorkType().name());
            preparedStatement.executeUpdate();

            user.setId(id);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public synchronized boolean updateUser(User user) {

        String sqlUpdate = "UPDATE user SET email =?, password =?, type =? WHERE id =?";


        if (user != null  ) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {

                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getWorkType().name());
                preparedStatement.setInt(4, user.getId());

                int rows = preparedStatement.executeUpdate();

                if (rows != 1) {
                    throw new IllegalStateException("Wrong number of updated rows! " + rows + "\nID: " + user.getId());
                }
                preparedStatement.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();

                return false;
            }
        }
        return false;
    }

    @Override
    public synchronized Optional<User> getUserById(int id) {
        User user = new User();

        String getUserById = "SELECT * FROM user WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserById)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {

                user.setId(resultSet.getInt("ID"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setWorkType(Type.valueOf(resultSet.getString("TYPE")));

            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(user);

    }

    @Override
    public synchronized List<User> getAllUsers() {
        String listOfUserSQL = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(listOfUserSQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setWorkType(Type.valueOf(resultSet.getString("type")));

                userList.add(user);
            }

            preparedStatement.close();
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public synchronized boolean deleteUser(int id) {
        String deleteUserSQL = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {

            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            if (rows != 1) {
                throw new IllegalStateException("Wrong number of updated rows! " + rows + "\nID: " + id);
            }
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean dropTable(String tableName) {
        String dropTable = "DROP TABLE IF EXISTS " + tableName;
        boolean rows = true;

        try (PreparedStatement preparedStatement = connection.prepareStatement(dropTable)) {

            int row = preparedStatement.executeUpdate();
            rows = preparedStatement.execute();

            if( rows) {
                System.out.printf("Table " + tableName + " where dropped.\t " + row + " where deleted");
            }
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!rows) {
            throw new IllegalStateException("No table found by the name of... ´" + tableName + "´\nTablename: " + dropTable);
        }
        return false;
    }

    private int getNextId() {
        try(PreparedStatement statement = connection.prepareStatement("select SEQ_USER.nextval from DUAL")) {
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException("Unable to generate new id for User");
    }

}