package baw.tools.entities;

import jakarta.persistence.*;

@Entity(name = "connections")
public class Connection {


    @Column(name = "HOST", nullable = false)
    private String host;

    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
