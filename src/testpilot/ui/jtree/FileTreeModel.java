package testpilot.ui.jtree;

import java.io.File;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

public class FileTreeModel implements javax.swing.tree.TreeModel {

    private FileTreeNode root;
    private FileTreeFileFilter filter;

    public FileTreeModel(File root) throws IllegalArgumentException {
        if (!root.isDirectory()) {
            throw new IllegalArgumentException("root must be a directory.");
        }

        if (!root.canRead()) {
            throw new IllegalArgumentException("root is not readable.");
        }

        this.root = new FileTreeNode(root);
        this.filter = new FileTreeFileFilter();
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        File directory = ((FileTreeNode) parent).getUserObject();
        File[] directoryMembers = directory.listFiles(this.filter);
        return (new FileTreeNode(new File(directory, directoryMembers[index].getName())));
    }

    @Override
    public int getChildCount(Object parent) {
        File fileSystemMember = ((FileTreeNode) parent).getUserObject();
        if (fileSystemMember.isDirectory()) {
            File[] directoryMembers = fileSystemMember.listFiles(this.filter);
            return directoryMembers.length;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((FileTreeNode) node).getUserObject().isFile();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File directory = ((FileTreeNode) parent).getUserObject();
        File directoryMember = ((FileTreeNode) child).getUserObject();
        String[] directoryMemberNames = directory.list();
        int result = -1;

        for (int i = 0; i < directoryMemberNames.length; ++i) {
            if (directoryMember.getName().equals(directoryMemberNames[i])) {
                result = i;
                break;
            }
        }

        return result;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }
}
