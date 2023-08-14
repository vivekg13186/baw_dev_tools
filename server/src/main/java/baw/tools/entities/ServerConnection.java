package baw.tools.entities;

import jakarta.persistence.*;

@Entity(name = "server_connections")
public class ServerConnection {


    @Column(name = "HOST", nullable = false)
    private String host;

    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ALIAS", nullable = false, unique = true)
    private String alias;


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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
