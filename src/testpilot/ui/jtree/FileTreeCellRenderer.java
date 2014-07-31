package testpilot.ui.jtree;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import testpilot.core.TestPilot;
import testpilot.core.TestResult;
import testpilot.core.TestResultList;

public class FileTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean isLeaf, int row, boolean focused) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, isLeaf, row, focused);

        if (isLeaf) {
            TestResultList testResultList = TestPilot.getInstance().getLastTestResults();
            ImageIcon icon = new ImageIcon(getClass().getResource("/testpilot/resources/grey.png"));

            if (testResultList != null && testResultList.getPath().getPath().equals(((FileTreeNode) value).getUserObject().getParent())) {
                for (TestResult testResult : testResultList.getResults()) {
                    if (testResult.getName().equals(value.toString())) {
                        if (testResult.isPass()) {
                            icon = new ImageIcon(getClass().getResource("/testpilot/resources/green.png"));
                        } else {
                            icon = new ImageIcon(getClass().getResource("/testpilot/resources/red.png"));
                        }
                        break;
                    }
                }
            }

            setIcon(icon);
        }

        return component;
    }
}
