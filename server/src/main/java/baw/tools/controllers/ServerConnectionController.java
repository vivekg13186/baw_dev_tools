package baw.tools.controllers;

import baw.tools.entities.ServerConnection;
import baw.tools.repositories.ServerConnectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ServerConnectionController {

    @Autowired
    private  ServerConnectionRepo repository;


    @GetMapping("/api/connection")
    List<ServerConnection> all() {
        return repository.findAll();
    }

    @PostMapping("/api/connection")
    ServerConnection newServerConnection(@RequestBody ServerConnection newServerConnection) {
        return repository.save(newServerConnection);
    }

    @GetMapping("/api/connection/{id}")
    Optional<ServerConnection> one(@PathVariable Long id) {

        return repository.findById(id);
    }

    @DeleteMapping("/api/connection/{id}")
    void deleteServerConnection(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
