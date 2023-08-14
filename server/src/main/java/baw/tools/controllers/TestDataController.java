package baw.tools.controllers;

import baw.tools.entities.TestData;
import baw.tools.repositories.TestDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TestDataController {

    @Autowired
    private TestDataRepo repo;

    @GetMapping("/api/testData")
    public List<TestData> all(){
        return repo.findAll();
    }

    @GetMapping("/api/testData/{name}")
    public TestData findByName(@PathVariable String name){
        return repo.findByName(name);
    }
    @PostMapping("/api/testData")
    public TestData createNew(@RequestBody TestData data){
        return repo.save(data);
    }

    @DeleteMapping("/api/testData/{name}")
    public void delete(@PathVariable String name){
        TestData data = new TestData();
        data.setName(name);
        repo.delete(data);
    }
}
