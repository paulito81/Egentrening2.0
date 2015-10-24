package model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Created by Paul on 21.10.2015.
 */
//@H2DAOQualifier
@Entity
@NamedQueries(value = {
        @NamedQuery(name = "User.getAllUsers", query = "select u from User u"),
        @NamedQuery(name = "User.deleteUser", query = "delete from User u where u.id = :id"),
})
@SequenceGenerator(name = "User.sequence", sequenceName = "SEQ_USER", initialValue = 50)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User.sequence")
    private int id;
    @NotNull @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Type workType;

    public User(String email, String password, Type workType) {
        this.email = email;
        this.password = password;
        this.workType = workType;
    }

    public User(int id, String email, String password, Type workType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.workType = workType;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getWorkType() {
        return workType;
    }

    public void setWorkType(Type workType) {
        this.workType = workType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", workType=" + workType +
                '}';
    }
}