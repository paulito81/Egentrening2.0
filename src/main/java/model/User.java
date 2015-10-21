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
        @NamedQuery(name = "emailQuery", query = "SELECT user FROM User user WHERE user.email = 'ola@yahoo.no'"),
        @NamedQuery(name = "idQuery", query = "SELECT user FROM User user WHERE user.id = 2"),
        @NamedQuery(name = "passwordQuery", query = "SELECT user FROM User user WHERE user.password like :password"),
        @NamedQuery(name = "deleteUserQuery", query = "DELETE FROM User WHERE User.id = 2")

})
@NamedNativeQuery(name = "workType", query = "SELECT * FROM User WHERE worktype=?", resultClass = User.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @Column(name = "email")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    @Column(name = "password")
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "worktype")
    private Type workType;

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