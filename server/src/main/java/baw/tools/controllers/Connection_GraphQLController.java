package baw.tools.controllers;

import baw.tools.entities.Connection;
import baw.tools.repositories.ConnectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Connection_GraphQLController {

    @Autowired
    private ConnectionRepo repo;



    public Connection_GraphQLController(){}


    @QueryMapping
    public List<Connection> allConnections(){
        return repo.findAll();
    }
    @QueryMapping
    public  Connection getConnection(@Argument int id){
        return repo.findById(Long.valueOf(id)).orElse(new Connection());
    }

    @MutationMapping
    public Connection createConnection(@Argument String name,@Argument String host,@Argument String username,@Argument String password){
        Connection  tcc =new Connection();
        tcc.setHost(host);
        tcc.setName(name);
        tcc.setUsername(username);
        tcc.setPassword(password);
        return repo.save(tcc);
    }

    @MutationMapping
    public Connection updateConnection(@Argument int id,@Argument String name,@Argument String host,@Argument String username,@Argument String password){
        Connection  tcc =new Connection();
        tcc.setHost(host);
        tcc.setName(name);
        tcc.setUsername(username);
        tcc.setPassword(password);
        tcc.setId(Long.valueOf(id));
        return repo.save(tcc);
    }

    @MutationMapping
    public Boolean deleteConnection(@Argument int id){

        repo.deleteById(Long.valueOf(id));
        return  true;
    }

}
