package baw.tools.controllers;

import baw.tools.entities.TestCell;
import baw.tools.repositories.TestCellRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TestCellController {


    private final TestCellRepo repository;

    public TestCellController(TestCellRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/api/{id}/cell")
    List<TestCell> all(@PathVariable Long id) {
        return repository.findByTestCaseId(id);
    }

    @PostMapping("/api/{id}/cell")
    TestCell newTestCase(@PathVariable int id, @RequestBody TestCell newTestCase) {
        newTestCase.setTestCaseId(id);
        return repository.save(newTestCase);
    }

    @GetMapping("/api/cell/{id}")
    Optional<TestCell> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/api/cell/{id}")
    TestCell one(@PathVariable TestCell cell) {
        repository.save(cell);
        return repository.getById((long) cell.getId());
    }

    @DeleteMapping("/api/cell/{id}")
    void deleteTestCase(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
