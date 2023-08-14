package baw.tools.repositories;

import baw.tools.entities.ServerConnection;
import baw.tools.entities.TestData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDataRepo extends JpaRepository<TestData, Long> {


     TestData findByName(String name);
     void deleteByName(String name);
}
