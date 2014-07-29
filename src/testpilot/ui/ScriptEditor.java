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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import testpilot.ui.jfilechooser.FileChooserFilter;

/**
 *
 * @author Mark Kremer
 */
public class ScriptEditor extends JEditorPane {

    private File file;
    private MainWindow application;
    private String originalText;

    public ScriptEditor(MainWindow application) {
        super();
        this.application = application;
        this.originalText = "";
        addChangeListener();
    }

    public ScriptEditor(MainWindow application, File file) throws IOException {
        super();
        this.application = application;
        this.file = file;
        byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        this.originalText = new String(encoded);
        this.setText(originalText);
        addChangeListener();
    }

    private void addChangeListener() {
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMainWindow();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMainWindow();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMainWindow();
            }
        });
    }

    private void updateMainWindow() {
        application.setEditorTabChanged(this, textChanged());
    }

    public boolean textChanged() {
        return !(originalText.equals(getText()));
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isForFile(File file) {
        return (this.file != null && this.file.getAbsolutePath().equals(file.getAbsolutePath()));
    }

    public void save() throws IOException {
        if (file == null) {
            JFileChooser fileChooser = new JFileChooser(application.getCurrentDirectory());
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileChooserFilter());

            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            }
        }

        if (file != null) {
            Files.write(Paths.get(file.getPath()), getText().getBytes());
            originalText = getText();
            updateMainWindow();
        }
    }
}
