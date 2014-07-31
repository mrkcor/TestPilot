package testpilot.ui.jtree;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTreeNode extends DefaultMutableTreeNode {

    File file;

    public FileTreeNode(File file) {
        this.file = file;
    }

    @Override
    public File getUserObject() {
        return file;
    }

    @Override
    public String toString() {
        return file.getName();
    }
}
