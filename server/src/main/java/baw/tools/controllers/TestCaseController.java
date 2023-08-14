package baw.tools.controllers;

import baw.tools.entities.TestCase;
import baw.tools.entities.TestCell;
import baw.tools.repositories.TestCaseRepo;
import baw.tools.repositories.TestCellRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TestCaseController {
    private final TestCaseRepo repository;

    private final TestCellRepo repository2;

    public TestCaseController(TestCaseRepo repository, TestCellRepo repository2) {
        this.repository = repository;
        this.repository2 = repository2;
    }

    @GetMapping("/api/testcase")
    List<TestCase> all() {
        return repository.findAll();
    }

    @PostMapping("/api/testcase")
    TestCase newTestCase(@RequestBody TestCase newTestCase) {
        return repository.save(newTestCase);
    }

    @GetMapping("/api/testcase/{id}")
    Optional<TestCase> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    @DeleteMapping("/api/testcase/{id}")
    void deleteTestCase(@PathVariable Long id) {
        List<TestCell> result = repository2.findByTestCaseId(id);
        repository2.deleteAll(result);
        repository.deleteById(id);
    }
}
