/*
 * The MIT License
 *
 * Copyright 2014 Mark Kremer.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package testpilot.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.ScriptingContainer;

/**
 *
 * @author Mark Kremer
 */
public class TestPilot {

    private static TestPilot instance;
    private File basePath;
    private File scriptsPath;
    private File functionsPath;
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

        if (this.functionsPath == null) {
            setFunctionsPath(new File(scriptsPath.getPath() + File.separator + "functions"));
        }
    }

    public File getScriptsPath() {
        return scriptsPath;
    }

    public void setScriptsPath(File scriptsPath) {
        guardIsValidDirectory(scriptsPath);
        this.scriptsPath = scriptsPath;
    }

    public File getFunctionsPath() {
        return functionsPath;
    }

    public void setFunctionsPath(File functionsPath) {
        guardIsValidDirectory(functionsPath);
        this.functionsPath = functionsPath;
    }

    public TestResultList getLastTestResults() {
        return lastTestResults;
    }

    public void run(String script) throws Exception {
        ScriptingContainer container = new ScriptingContainer(LocalContextScope.SINGLETON);
        container.runScriptlet("ENV['GEM_PATH']='" + getBasePath().getPath() + "/lib/rubygems/'");
        container.runScriptlet("require './lib/ruby/test_pilot.rb'");
        container.runScriptlet("$LOAD_PATH << '" + getScriptsPath().getPath() + "'");
        container.runScriptlet("TestPilot.functions_path='" + getFunctionsPath().getPath() + "'");
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
