package baw.tools.controllers;

import baw.tools.entities.TestCase;
import baw.tools.entities.TestCell;
import baw.tools.repositories.TestCaseRepo;
import baw.tools.repositories.TestCellRepo;
import org.aspectj.weaver.ast.Test;
import org.hibernate.sql.ast.tree.predicate.BooleanExpressionPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class G_TestCaseController {

    @Autowired
    private TestCaseRepo testCaseRepo;

    @Autowired
    private TestCellRepo testCellRepo;


    public G_TestCaseController(){}
    @QueryMapping
    public List<TestCase> allTestCases(){
        return testCaseRepo.findAll();
    }
    @QueryMapping
    public  TestCase getTestCase(@Argument int id){
        return testCaseRepo.findById(Long.valueOf(id)).orElse(new TestCase());
    }
    @QueryMapping
    public List<TestCell> getTestCells(@Argument int testCaseId){
        return testCellRepo.findByTestCaseId(Long.valueOf(testCaseId));
    }
    @QueryMapping
   public TestCell getTestCell(@Argument int id){
        return  testCellRepo.getReferenceById(Long.valueOf(id));
   }


    @MutationMapping
    public TestCase createTestCase(@Argument String name){
        TestCase  tcc =new TestCase();
        tcc.setName(name);
        tcc.setLastModified(String.valueOf(System.currentTimeMillis()));
        return testCaseRepo.save(tcc);
    }

    @MutationMapping
    public TestCase updateTestCase(@Argument int id,@Argument String name){
        TestCase  tcc =new TestCase();
        tcc.setName(name);
        tcc.setId(id);
        tcc.setLastModified(String.valueOf(System.currentTimeMillis()));
        return  testCaseRepo.save(tcc);
    }

    @MutationMapping
    public Boolean deleteTestCase(@Argument int id){
        List<TestCell> list = testCellRepo.findByTestCaseId(Long.valueOf(id));
        testCellRepo.deleteAll(list);
        testCaseRepo.deleteById(Long.valueOf(id));
        return  true;
    }
    @MutationMapping
    public TestCell createTestCell(@Argument String name,@Argument int testCaseId){
        TestCell  tcc =new TestCell();
        tcc.setName(name);
        tcc.setLastModified(String.valueOf(System.currentTimeMillis()));
        tcc.setTestCaseId(testCaseId);
        return testCellRepo.save(tcc);
    }


    @MutationMapping
    public TestCell updateTestCell(@Argument int id,@Argument String name,@Argument String data){
        TestCell  tcc =new TestCell();
        tcc.setName(name);
        tcc.setLastModified(String.valueOf(System.currentTimeMillis()));
        tcc.setId(id);
        if(data!=null)
        tcc.setData(data);
        return testCellRepo.save(tcc);
    }
    @MutationMapping
    public Boolean deleteTestCell(@Argument int id){
        testCellRepo.deleteById(Long.valueOf(id));
        return  true;
    }
}
