package baw.tools.repositories;

import baw.tools.entities.Connection;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ConnectionRepo extends JpaRepository<Connection, Long> {



}
