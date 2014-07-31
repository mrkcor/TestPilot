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
package testpilot.ui;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.tree.TreePath;
import testpilot.core.TestPilot;
import testpilot.core.TestResult;
import testpilot.ui.jtree.FileTreeCellRenderer;
import testpilot.ui.jtree.FileTreeModel;
import testpilot.ui.jtree.FileTreeNode;

/**
 *
 * @author Mark Kremer
 */
public class MainWindow extends javax.swing.JFrame {

    private final TestPilot testPilot;
    private File currentDirectory;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        testPilot = TestPilot.getInstance();
        testPilot.setBasePath(new File(System.getProperty("user.dir")));

        initComponents();

        openDirectory(new File(testPilot.getScriptsPath().getPath() + File.separator + "suites"));
        addEditorTab();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        testPilotStatus = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        currentDirectoryTree = new javax.swing.JTree();
        currentDirectoryLabel = new javax.swing.JLabel();
        editorsPane = new javax.swing.JTabbedPane();
        mainMenuBar = new javax.swing.JMenuBar();
        mainMenu = new javax.swing.JMenu();
        openDirectoryMenuItem = new javax.swing.JMenuItem();
        customizationsMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        newFileMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        runMenuItem = new javax.swing.JMenuItem();
        runDirectoryMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TestPilot");
        setPreferredSize(new java.awt.Dimension(800, 600));

        testPilotStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        testPilotStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpilot/resources/grey.png"))); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        currentDirectoryTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        currentDirectoryTree.setCellRenderer(new FileTreeCellRenderer());
        currentDirectoryTree.setRequestFocusEnabled(false);
        currentDirectoryTree.setRootVisible(false);
        currentDirectoryTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currentDirectoryTreeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(currentDirectoryTree);

        currentDirectoryLabel.setText("Directory");

        editorsPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        mainMenu.setText("File");

        openDirectoryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openDirectoryMenuItem.setText("Open directory");
        openDirectoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openDirectoryMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(openDirectoryMenuItem);

        customizationsMenuItem.setText("Customizations");
        customizationsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customizationsMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(customizationsMenuItem);
        mainMenu.add(jSeparator1);

        newFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFileMenuItem.setText("New");
        newFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(newFileMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(saveMenuItem);

        closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        closeMenuItem.setText("Close");
        closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(closeMenuItem);

        runMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        runMenuItem.setText("Run");
        runMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(runMenuItem);

        runDirectoryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        runDirectoryMenuItem.setText("Run directory");
        runDirectoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runDirectoryMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(runDirectoryMenuItem);
        mainMenu.add(jSeparator2);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(exitMenuItem);

        mainMenuBar.add(mainMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(testPilotStatus)
                    .addComponent(currentDirectoryLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editorsPane, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(testPilotStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentDirectoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                    .addComponent(editorsPane))
                .addContainerGap())
        );

        testPilotStatus.getAccessibleContext().setAccessibleName("testPilotStatus");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setActiveEditorTabIcon(String name) {
        ImageIcon tabIcon = new ImageIcon(getClass().getResource("/testpilot/resources/" + name + ".png"));
        ((JLabel) editorsPane.getTabComponentAt(editorsPane.getSelectedIndex())).setIcon(tabIcon);
    }

    private void runMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runMenuItemActionPerformed
        try {
            testPilot.run(getActiveEditor().getText());
            setActiveEditorTabIcon("green");
            testPilotStatus.setIcon(new ImageIcon(getClass().getResource("/testpilot/resources/green.png")));
            testPilotStatus.setText("OK");
        } catch (Exception exception) {
            setActiveEditorTabIcon("red");
            testPilotStatus.setIcon(new ImageIcon(getClass().getResource("/testpilot/resources/red.png")));
            testPilotStatus.setText("Failed");
        }
    }//GEN-LAST:event_runMenuItemActionPerformed

    private ScriptEditor getActiveEditor() {
        return ((ScriptEditor) editorsPane.getSelectedComponent());
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openDirectoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDirectoryMenuItemActionPerformed
        JFileChooser fileChooser = new JFileChooser(currentDirectory);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            openDirectory(fileChooser.getSelectedFile());
        }
    }//GEN-LAST:event_openDirectoryMenuItemActionPerformed

    private void newFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileMenuItemActionPerformed
        addEditorTab();
    }//GEN-LAST:event_newFileMenuItemActionPerformed

    private void addEditorTab() {
        ScriptEditor editor = new ScriptEditor(this);
        ImageIcon editorIcon = new ImageIcon(getClass().getResource("/testpilot/resources/grey.png"));
        JLabel label = new JLabel(editor.getTitle());
        label.setIcon(editorIcon);
        editorsPane.addTab(null, editor);
        editorsPane.setTabComponentAt(editorsPane.getTabCount() - 1, label);
        editorsPane.setSelectedIndex(editorsPane.getTabCount() - 1);
    }

    private void addEditorTab(File file) throws IOException {
        ScriptEditor editor = new ScriptEditor(this, file);
        ImageIcon editorIcon = new ImageIcon(getClass().getResource("/testpilot/resources/grey.png"));
        JLabel label = new JLabel(editor.getTitle());
        label.setIcon(editorIcon);
        editorsPane.addTab(null, editor);
        editorsPane.setTabComponentAt(editorsPane.getTabCount() - 1, label);
        editorsPane.setSelectedIndex(editorsPane.getTabCount() - 1);
    }

    protected void setEditorTabChanged(ScriptEditor editor, boolean changed) {
        int idx = editorsPane.indexOfComponent(editor);

        if (idx != -1) {
            JLabel tab = (JLabel) editorsPane.getTabComponentAt(idx);
            tab.setFont(tab.getFont().deriveFont(changed ? Font.ITALIC : Font.PLAIN));
            tab.setText(editor.getTitle());
        }
    }

    private void setEditorTabIcon(File file, String name) {
        for (int idx = 0; idx < editorsPane.getTabCount(); idx++) {
            ScriptEditor editor = (ScriptEditor) editorsPane.getComponentAt(idx);
            if (editor.isForFile(file)) {
                JLabel tab = (JLabel) editorsPane.getTabComponentAt(idx);
                tab.setIcon(new ImageIcon(getClass().getResource("/testpilot/resources/" + name + ".png")));
                break;
            }
        }
    }

    private void openEditorTab(File file) throws IOException {
        boolean found = false;
        for (int idx = 0; idx < editorsPane.getTabCount(); idx++) {
            ScriptEditor editor = (ScriptEditor) editorsPane.getComponentAt(idx);

            if (editor.isForFile(file)) {
                editorsPane.setSelectedIndex(idx);
                found = true;
                break;
            }
        }

        if (!found) {
            addEditorTab(file);
        }
    }

    private void openDirectory(File directory) {
        currentDirectory = directory;
        refreshCurrentDirectoryTree();
    }

    private void runDirectoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runDirectoryMenuItemActionPerformed
        if (currentDirectory != null) {
            testPilot.runDirectory(currentDirectory);
            List<TestResult> results = testPilot.getLastTestResults().getResults();

            testPilotStatus.setIcon(new ImageIcon(getClass().getResource("/testpilot/resources/grey.png")));
            testPilotStatus.setText("");

            int passed = 0;
            int failed = 0;

            for (TestResult result : results) {
                if (result.isPass()) {
                    passed += 1;
                    setEditorTabIcon(result.getFile(), "green");
                } else {
                    failed += 1;
                    setEditorTabIcon(result.getFile(), "red");
                }
            }

            if (failed == 0) {
                testPilotStatus.setIcon(new ImageIcon(getClass().getResource("/testpilot/resources/green.png")));
                testPilotStatus.setText("All passed");
            } else if (passed == 0) {
                testPilotStatus.setIcon(new ImageIcon(getClass().getResource("/testpilot/resources/red.png")));
                testPilotStatus.setText("All failed");
            } else {
                testPilotStatus.setIcon(new ImageIcon(getClass().getResource("/testpilot/resources/yellow.png")));
                testPilotStatus.setText(passed + " passed, " + failed + " failed");
            }

            refreshCurrentDirectoryTree();
        }
    }//GEN-LAST:event_runDirectoryMenuItemActionPerformed

    private void currentDirectoryTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_currentDirectoryTreeMouseClicked
        if (evt.getClickCount() == 2) {
            TreePath path = currentDirectoryTree.getSelectionPath();
            if (path != null) {
                try {
                    File scriptFile = ((FileTreeNode) path.getLastPathComponent()).getUserObject();
                    openEditorTab(scriptFile);
                } catch (IOException exception) {
                    // FIXME: Error handling!
                }
            }
        }
    }//GEN-LAST:event_currentDirectoryTreeMouseClicked

    public File getCurrentDirectory() {
        return currentDirectory;
    }

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        try {
            getActiveEditor().save();
        } catch (IOException exception) {
            // FIXME: Error handling
        }

        refreshCurrentDirectoryTree();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void refreshCurrentDirectoryTree() {
        if (currentDirectory != null) {
            currentDirectoryTree.setModel(new FileTreeModel(currentDirectory));
        }
    }

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed
        if (editorsPane.getTabCount() > 0) {
            editorsPane.remove(editorsPane.getSelectedIndex());
        }

        if (editorsPane.getTabCount() == 0) {
            addEditorTab();
        }
    }//GEN-LAST:event_closeMenuItemActionPerformed

    private void customizationsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customizationsMenuItemActionPerformed
        File customizationsFile = new File(testPilot.getScriptsPath() + File.separator + "test_pilot.rb");

        try {
            if (!customizationsFile.exists()) {
                customizationsFile.createNewFile();
            }

            openEditorTab(customizationsFile);
        } catch (IOException exception) {
            // FIXME: Error handling
        }
    }//GEN-LAST:event_customizationsMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JLabel currentDirectoryLabel;
    private javax.swing.JTree currentDirectoryTree;
    private javax.swing.JMenuItem customizationsMenuItem;
    private javax.swing.JTabbedPane editorsPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu mainMenu;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem newFileMenuItem;
    private javax.swing.JMenuItem openDirectoryMenuItem;
    private javax.swing.JMenuItem runDirectoryMenuItem;
    private javax.swing.JMenuItem runMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JLabel testPilotStatus;
    // End of variables declaration//GEN-END:variables

}
