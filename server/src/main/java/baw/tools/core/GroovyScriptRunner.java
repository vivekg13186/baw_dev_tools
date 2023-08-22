package baw.tools.core;

import groovy.json.JsonSlurper;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class GroovyScriptRunner {

    private GroovyShell shell;
    private String output;

    private String error;

    private String code;
    private String input;




    public GroovyScriptRunner(String code,String input){
        this.code=code;
        this.input = input;


    }

    private Object createContext(String data){

        JsonSlurper jsonSlurper  =new JsonSlurper();
        if(data==null){
            return jsonSlurper.parse("{}".getBytes());
        }else{
            return  jsonSlurper.parse(data.getBytes());
        }
    }

    public void eval(){
        StringWriter stringWriter= new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        try {
            Binding binding = new Binding();
            binding.setProperty("out", out);
            binding.setProperty("baw",new BawUtil(out));
            binding.setProperty("util",new Util(out));
            binding.setProperty("ctx",createContext(input));
            shell = new GroovyShell(binding);
            shell.evaluate(code);
        }catch (Exception | Error w){
            error = w.getMessage();
        }finally {
            output = stringWriter.getBuffer().toString();
            out.close();
        }

    }

    public String getOutput() {
        return output;
    }

    public String getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    public String getInput() {
        return input;
    }

    public static void main(String[] arg){
        String code = new Scanner(GroovyScriptRunner.class.getResourceAsStream("test.groovy"), "UTF-8").useDelimiter("\\A").next();

        GroovyScriptRunner runner  = new GroovyScriptRunner(code,null);
        runner.eval();
        System.out.println("Output");
        System.out.println(runner.getOutput());
        System.out.println("Error");
        System.out.println(runner.getError());

    }
}
