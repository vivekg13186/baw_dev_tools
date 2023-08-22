package baw.tools.core;

import groovy.json.JsonOutput;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.PrintWriter;

public class BawUtil {

    private final PrintWriter out;

    public BawUtil(PrintWriter out){
        this.out = out;
    }
    public   void sayHello(String message){
        out.println(message);
    }
    public HttpResponse<String> callService(String host, String username, String password, String app, String service, Object input){
        return Unirest.post(String.format("%s/rest/bpm/wle/v1/service/%s@%s",host,app,service))
                .basicAuth(username,password)
                .queryString("action","start")
                .queryString("params", JsonOutput.toJson(input))
                .queryString("createTask","false")
                .queryString("parts","all")
                .body(input).asString();
    }
}
