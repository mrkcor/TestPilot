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
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Mark Kremer
 */
public class ScriptsFileSystemView extends FileSystemView {

    private final File[] rootDirectories;

    public ScriptsFileSystemView() {
        File rootDirectory = new File(System.getProperty("user.dir") + File.separator + "scripts");
        this.rootDirectories = new File[]{rootDirectory};
    }

    @Override
    public File createNewFolder(File containingDir) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File[] getRoots() {
        return rootDirectories;
    }

    @Override
    public boolean isRoot(File file) {
        for (File root : rootDirectories) {
            if (root.equals(file)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public File getHomeDirectory() {
        return rootDirectories[0];
    }
}
