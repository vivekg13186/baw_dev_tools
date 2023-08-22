package baw.tools.entities;

import jakarta.persistence.*;

@Entity(name = "test_script")
public class TestScript {

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "CODE",length = 100000)
    private String code;

    @Column(name = "LAST_MODIFIED", nullable = false)
    private String lastModified;


    @Id
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
