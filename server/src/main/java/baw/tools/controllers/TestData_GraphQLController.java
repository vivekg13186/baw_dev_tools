package baw.tools.controllers;


import baw.tools.entities.TestData;
import baw.tools.repositories.TestDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TestData_GraphQLController {

    @Autowired
    private TestDataRepo repo;



    public TestData_GraphQLController(){}


    @QueryMapping
    public List<TestData> allTestData(){
        return repo.findAll();
    }
    @QueryMapping
    public  TestData getTestData(@Argument int id){
        return repo.findById((long) id).orElse(new TestData());
    }


    @MutationMapping
    public TestData createTestData(@Argument String name){
        TestData  tcc =new TestData();
        tcc.setName(name);
        tcc.setLastModified(String.valueOf(System.currentTimeMillis()));
        return repo.save(tcc);
    }

    @MutationMapping
    public TestData updateTestData(@Argument int id,@Argument String name,@Argument String data){
        TestData  tcc =new TestData();
        tcc.setName(name);
        tcc.setId(id);
        if(data!=null){
            tcc.setData(data);
        }
        tcc.setLastModified(String.valueOf(System.currentTimeMillis()));
        return  repo.save(tcc);
    }

    @MutationMapping
    public Boolean deleteTestData(@Argument int id){

        repo.deleteById((long) id);
        return  true;
    }

}
