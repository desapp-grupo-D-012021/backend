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

    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinTable(name = "Role", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))

    @Column
    private String rol;

    public User() {
        setRole("USER");
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

    public String getRol() {
        return rol;
    }

    public void setRole(String rol) {
        this.rol = rol;
    }

    public void makeAdmin() {
        this.setRole("ADMIN");
    }
}
