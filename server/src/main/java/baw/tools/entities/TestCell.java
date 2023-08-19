package baw.tools.entities;

import jakarta.persistence.*;

@Entity(name = "test_cell")
public class TestCell {

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DATA")
    private String data;

    @Column(name = "LAST_MODIFIED", nullable = false)
    private String lastModified;


    @Column(name = "TESTCASE_ID", nullable = false)
    private int testCaseId;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
