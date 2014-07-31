package testpilot.ui.jfilechooser;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileChooserFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return !(f.isDirectory() || f.isHidden() || f.getName().startsWith("."));
    }

    @Override
    public String getDescription() {
        return "TestPilot scripts";
    }

}
