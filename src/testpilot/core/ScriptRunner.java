/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpilot.core;

import org.jruby.embed.ScriptingContainer;

/**
 *
 * @author Mark Kremer
 */
public class ScriptRunner {

    public void run(String script) {
        ScriptingContainer container = new ScriptingContainer();
        container.runScriptlet(script);
    }
}
