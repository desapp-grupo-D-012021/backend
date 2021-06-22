package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String user;

    @Column
    private String password;

    @Transient
    private boolean isCritic;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getCritic() {
        return isCritic;
    }

    public void setCritic(boolean isCritic) {
        this.isCritic = isCritic;
    }
}
