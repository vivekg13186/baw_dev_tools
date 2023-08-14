package baw.tools.repositories;

import baw.tools.entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepo extends JpaRepository<TestCase, Long> {
}
