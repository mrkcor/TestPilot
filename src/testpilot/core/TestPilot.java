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
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.ScriptingContainer;

/**
 *
 * @author Mark Kremer
 */
public class TestPilot {

    private File basePath;
    private File scriptsPath;

    public TestPilot(File basePath) {
        if (!basePath.isDirectory()) {
            throw new IllegalArgumentException("TestPilot basepath must be a directory.");
        }

        if (!basePath.canRead()) {
            throw new IllegalArgumentException("TestPilot basepath is not readable.");
        }

        this.basePath = basePath;
        this.setScriptsPath(new File(basePath.getPath() + File.separator + "scripts"));
    }

    public File getBasePath() {
        return basePath;
    }

    public File getScriptsPath() {
        return scriptsPath;
    }

    public void setScriptsPath(File scriptsPath) {
        if (!scriptsPath.isDirectory()) {
            throw new IllegalArgumentException("TestPilot scriptspath must be a directory.");
        }

        if (!scriptsPath.canRead()) {
            throw new IllegalArgumentException("TestPilot scriptspath is not readable.");
        }

        this.scriptsPath = scriptsPath;
    }

    public void run(String script) throws Exception {
        ScriptingContainer container = new ScriptingContainer(LocalContextScope.SINGLETON);
        container.runScriptlet("ENV['GEM_PATH']='" + getBasePath().getPath() + "/lib/rubygems/'");
        container.runScriptlet("require './lib/ruby/test_pilot.rb'");
        container.runScriptlet("$LOAD_PATH << '" + getScriptsPath().getPath() + "'");
        container.runScriptlet("TestPilot.root='" + getScriptsPath().getPath() + "'");
        // TODO: Connect container STDOUT and STDERR to GUI
        container.runScriptlet("TestPilot.new('TestPilot').fly do; " + script + "; end");
    }

}