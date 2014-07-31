package testpilot.ui.jtree;

import java.io.File;
import java.io.FileFilter;

public class FileTreeFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return !(pathname.isDirectory() || pathname.isHidden() || pathname.getName().startsWith("."));
    }

}
