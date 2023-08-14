package baw.tools.entities;

import jakarta.persistence.*;

@Entity(name = "test_case")
public class TestCase {
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "LAST_MODIFIED", nullable = false)
    private Long lastModified;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
