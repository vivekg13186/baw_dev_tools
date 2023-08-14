package baw.tools.entities;

import jakarta.persistence.*;

@Entity(name = "test_cell")
public class TestCell {

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DATA")
    private String data;
    @Column(name = "TESTCASE_ID", nullable = false)
    private Long testCaseId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
