package testpilot.core;

import java.io.File;

public class TestResult {

    private File file;
    private boolean pass;
    private Exception exception;

    public TestResult(File file, boolean pass, Exception exception) {
        this.file = file;
        this.pass = pass;
        this.exception = exception;
    }

    public TestResult(File file, boolean pass) {
        this.file = file;
        this.pass = pass;
    }

    public String getName() {
        return file.getName();
    }

    public File getFile() {
        return file;
    }

    public boolean isPass() {
        return pass;
    }

    public Exception getException() {
        return exception;
    }

}
