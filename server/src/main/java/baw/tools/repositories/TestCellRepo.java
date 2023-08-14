package baw.tools.repositories;

import baw.tools.entities.TestCell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCellRepo extends JpaRepository<TestCell, Long> {


    List<TestCell> findByTestCaseId(Long testCaseId);
}
