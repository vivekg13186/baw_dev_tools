package baw.tools.core;

import baw.tools.entities.TestData;
import baw.tools.entities.TestScript;

public class TestScriptRunner {

    private final TestScript testScript;
    private final TestResult result;
    private final TestData td;

    public TestScriptRunner(TestScript testScript, TestData td){
    this.testScript = testScript;
    this.td = td;
    this.result = new TestResult();



    }

    public void run() {
        long startTime  = System.currentTimeMillis();
        result.setId(SecureRandomString.generate());
        result.setStatus("running");
        if(testScript==null &&testScript.getCode()==null){
            result.setError("Test script is empty");
            result.setResult("failed");

        }else{
            this.result.setScriptID(testScript.getId());

             String code  = testScript.getCode();
                GroovyScriptRunner runner = new GroovyScriptRunner(code,td.getData());
                runner.eval();
                result.setOutput(runner.getOutput());
                if(runner.getError()==null){
                    result.setResult("passed");
                }else{
                    result.setError(runner.getError());
                    result.setResult("failed");
                }

            long endTime  = System.currentTimeMillis();
                result.setElapsedTime((int) (endTime-startTime));
            result.setStatus("completed");
        }
    }

    public TestResult getResult(){
        return result;
    }
}
