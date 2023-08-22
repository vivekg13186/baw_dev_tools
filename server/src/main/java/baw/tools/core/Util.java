package baw.tools.core;

import groovy.json.JsonSlurper;

import java.io.PrintWriter;

public class Util {

    private final PrintWriter out;

    public Util(PrintWriter out){
        this.out = out;
    }

    public Object toJSON(String code){
        JsonSlurper jsonSlurper = new JsonSlurper();
        return jsonSlurper.parse(code.getBytes());
    }
}
