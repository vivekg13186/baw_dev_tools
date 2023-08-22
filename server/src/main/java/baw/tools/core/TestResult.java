package baw.tools.core;

public class TestResult {

    private String id;
    private String scriptID;
    private String  output;
    private String  error;
    private String  status;
    private String  result;
    private int  elapsedTime;

    public TestResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScriptID() {
        return scriptID;
    }

    public void setScriptID(String scriptID) {
        this.scriptID = scriptID;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
