package testpilot.core;

import java.io.File;
import java.util.ArrayList;

public class TestResultList {

    private ArrayList<TestResult> results;
    private File path;

    public TestResultList() {
    }

    public TestResultList(File path, ArrayList<TestResult> results) {
        this.path = path;
        this.results = results;
    }

    public ArrayList<TestResult> getResults() {
        return results;
    }

    public File getPath() {
        return path;
    }

}
