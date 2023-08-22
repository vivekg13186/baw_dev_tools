package baw.tools.controllers;

import baw.tools.core.TestResult;
import baw.tools.core.TestScriptRunner;
import baw.tools.entities.TestData;
import baw.tools.entities.TestScript;
import baw.tools.repositories.TestDataRepo;
import baw.tools.repositories.TestScriptRepo;
import baw.tools.core.SecureRandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TestScript_GraphQLController {

    @Autowired
    private TestScriptRepo repo;

    @Autowired
    private TestDataRepo testDataRepo;



    public TestScript_GraphQLController(){}
    @QueryMapping
    public List<TestScript> allTestScript(){
        return repo.findAll();
    }
    @QueryMapping
    public  TestScript getTestScript(@Argument String id){
        return repo.findById(id).orElse(new TestScript());
    }

    @MutationMapping
    public TestScript createTestScript(@Argument String name){
        TestScript  tcc =new TestScript();
        tcc.setName(name);
        tcc.setLastModified(String.valueOf(System.currentTimeMillis()));
        tcc.setId(SecureRandomString.generate());
        return repo.save(tcc);
    }

    @MutationMapping
    public TestScript updateTestScript(@Argument String id,@Argument String name,@Argument String code){
        TestScript  tcc =new TestScript();
        tcc.setName(name);
        tcc.setId(id);
        tcc.setCode(code);
        tcc.setLastModified(String.valueOf(System.currentTimeMillis()));
        return  repo.save(tcc);
    }



    @MutationMapping
    public Boolean deleteTestScript(@Argument String id){
        repo.deleteById(id);
        return  true;
    }

    @QueryMapping
    public TestResult evalTestScript(@Argument String id, @Argument Long tid){
        TestScript cell = repo.findById(id).orElse(new TestScript());
        TestData td = testDataRepo.findById(tid).orElse(new TestData());
        TestScriptRunner runner = new TestScriptRunner(cell,td);
        runner.run();
        return runner.getResult();
    }
}
