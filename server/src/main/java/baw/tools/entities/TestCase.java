package baw.tools.entities;

import jakarta.persistence.*;

@Entity(name = "test_case")
public class TestCase {
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "LAST_MODIFIED", nullable = false)
    private String lastModified;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
