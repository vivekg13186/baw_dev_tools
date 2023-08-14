package baw.tools.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "test_data")
public class TestData {

    @Column(name = "name", nullable = false,unique = true)
    @Id
    private String name;
    @Column(name="data",length = 5000)
    private String data;

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
}
