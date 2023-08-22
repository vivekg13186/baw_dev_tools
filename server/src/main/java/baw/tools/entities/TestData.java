package baw.tools.entities;

import jakarta.persistence.*;

@Entity(name = "test_data")
public class TestData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false,unique = true)
    private String name;
    @Column(name="data",length = 5000)
    private String data;

    @Column(name = "LAST_MODIFIED", nullable = false)
    private String lastModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
}
