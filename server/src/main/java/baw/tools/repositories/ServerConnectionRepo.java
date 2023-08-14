package baw.tools.repositories;

import baw.tools.entities.ServerConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ServerConnectionRepo extends JpaRepository<ServerConnection, Long> {

    List<ServerConnection> findByAlias(String alias);

}
