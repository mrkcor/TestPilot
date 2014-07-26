package testpilot.core;

import org.jruby.embed.ScriptingContainer;

/**
 *
 * @author Mark Kremer
 */
public class ScriptRunner {

    public void run(String script) {
        ScriptingContainer container = new ScriptingContainer();
        // FIXME: Beter manier om het basepath te bepalen
        String basepath = System.getProperty("user.dir");
        container.runScriptlet("ENV['GEM_PATH']='" + basepath + "/lib/rubygems/'");
        // TODO: Connect container STDOUT and STDERR to GUI
        container.runScriptlet(script);
    }

}
