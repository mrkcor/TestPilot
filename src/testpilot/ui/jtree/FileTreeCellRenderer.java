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
package testpilot.ui.jtree;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import testpilot.core.TestPilot;
import testpilot.core.TestResult;
import testpilot.core.TestResultList;

/**
 *
 * @author Mark Kremer
 */
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
