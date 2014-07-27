package testpilot.core;

import org.jruby.embed.LocalContextScope;
import org.jruby.embed.ScriptingContainer;

/**
 *
 * @author Mark Kremer
 */
public class ScriptRunner {

    public void run(String script) {
        // NOTE: With LocalContextScope.SINGLETHREAD as argument you get a clean interpreter, it will be slower though
        ScriptingContainer container = new ScriptingContainer(LocalContextScope.SINGLETON);
        // FIXME: A better way is needed to determine the working directory
        String basepath = System.getProperty("user.dir");
        container.runScriptlet("ENV['GEM_PATH']='" + basepath + "/lib/rubygems/'");
        container.runScriptlet("require './lib/ruby/init.rb'");
        // TODO: Connect container STDOUT and STDERR to GUI
        container.runScriptlet("TestPilot.new('TestPilot').fly do; " + script + "; end");
    }

}
