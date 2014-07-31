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

    public String getTitle() {
        if (file == null) {
            return "new file";
        } else {
            return file.getName();
        }
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
