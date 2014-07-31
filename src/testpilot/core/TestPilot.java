package testpilot.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.ScriptingContainer;

public class TestPilot {

    private static TestPilot instance;
    private File basePath;
    private File scriptsPath;
    private TestResultList lastTestResults;

    private TestPilot() {

    }

    public static TestPilot getInstance() {
        if (instance == null) {
            instance = new TestPilot();
        }

        return instance;
    }

    public File getBasePath() {
        return basePath;
    }

    public void setBasePath(File basePath) {
        guardIsValidDirectory(basePath);

        this.basePath = basePath;

        if (this.scriptsPath == null) {
            setScriptsPath(new File(basePath.getPath() + File.separator + "scripts"));
        }
    }

    public File getScriptsPath() {
        return scriptsPath;
    }

    public void setScriptsPath(File scriptsPath) {
        guardIsValidDirectory(scriptsPath);
        this.scriptsPath = scriptsPath;
    }

    public TestResultList getLastTestResults() {
        return lastTestResults;
    }

    public void run(String script) throws Exception {
        ScriptingContainer container = new ScriptingContainer(LocalContextScope.SINGLETON);
        container.runScriptlet("ENV['GEM_PATH']='" + getBasePath().getPath() + "/lib/rubygems/'");
        container.runScriptlet("require './lib/ruby/test_pilot.rb'");
        container.runScriptlet("$LOAD_PATH << '" + getScriptsPath().getPath() + "'");
        container.runScriptlet("TestPilot.scripts_path='" + getScriptsPath().getPath() + "'");
        // TODO: Connect container STDOUT and STDERR to GUI
        container.runScriptlet("TestPilot.new('TestPilot').fly do; " + script + "; end");
    }

    public void runDirectory(File path) {
        guardIsValidDirectory(path);

        File[] files = path.listFiles();

        ArrayList<TestResult> results = new ArrayList<>();

        for (File file : files) {
            try {
                byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
                run(new String(encoded));
                results.add(new TestResult(file, true));
            } catch (Exception exception) {
                results.add(new TestResult(file, false, exception));
            }
        }

        lastTestResults = new TestResultList(path, results);
    }

    private void guardIsValidDirectory(File path) {
        if (!path.isDirectory()) {
            throw new IllegalArgumentException("Not a directory.");
        }

        if (!path.canRead()) {
            throw new IllegalArgumentException("Not readable.");
        }
    }
}
