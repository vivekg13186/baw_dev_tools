package baw.tools.repositories;


import baw.tools.entities.TestScript;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TestScriptRepo extends JpaRepository<TestScript, String> {
}
