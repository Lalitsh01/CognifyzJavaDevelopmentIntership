package NotePad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/*
*
* Task: Build a Desktop Application : I create NotePad UI

Develop a desktop application using
JavaFX or Swing. The application could
be a calculator, file manager, or any other
tool that showcases your user interface
design skills and event handling.
*
*
*/

public class Editor extends JFrame implements ActionListener {

    JTextArea textArea;
    JLabel statusBar;
    JFileChooser fileChooser;
    float zoomFactor = 1.0f;

    public Editor() {
        setTitle("NotepadUI © Dev.Lalit");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                updateStatusBar();
            }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        statusBar = new JLabel(   "Ln 1, Col 1    |    0 Chars    |    100%    |    UTF-8");
        add(statusBar, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "New Tab", this);
        addMenuItem(fileMenu, "New Window", this);
        addMenuItem(fileMenu, "Open", this);
        addMenuItem(fileMenu, "Save", this);
        addMenuItem(fileMenu, "Save As", this);
        addMenuItem(fileMenu, "Save All", this);
        addMenuItem(fileMenu, "Page Setup", this);
        addMenuItem(fileMenu, "Print", this);
        addMenuItem(fileMenu, "Close Tab", this);
        addMenuItem(fileMenu, "Close Window", this);
        addMenuItem(fileMenu, "Exit", this);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        addMenuItem(editMenu, "Undo", this);
        addMenuItem(editMenu, "Cut", this);
        addMenuItem(editMenu, "Copy", this);
        addMenuItem(editMenu, "Paste", this);
        addMenuItem(editMenu, "Delete", this);
        addMenuItem(editMenu, "Find", this);
        addMenuItem(editMenu, "Find Next", this);
        addMenuItem(editMenu, "Find Previous", this);
        addMenuItem(editMenu, "Replace", this);
        addMenuItem(editMenu, "Go To", this);
        addMenuItem(editMenu, "Select All", this);
        addMenuItem(editMenu, "Time/Date", this);
        addMenuItem(editMenu, "Font", this);
        menuBar.add(editMenu);

        JMenu viewMenu = new JMenu("View");
        JMenu zoomMenu = new JMenu("Zoom");
        addMenuItem(zoomMenu, "Zoom In", this);
        addMenuItem(zoomMenu, "Zoom Out", this);
        addMenuItem(zoomMenu, "Restore Default Zoom", this);
        viewMenu.add(zoomMenu);
        addMenuItem(viewMenu, "Status Bar", this);
        addMenuItem(viewMenu, "Word Wrap", this);
        menuBar.add(viewMenu);

        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();

        setVisible(true);
    }

    private void addMenuItem(JMenu menu, String title, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    private void updateStatusBar() {
        int caretPos = textArea.getCaretPosition();
        Element root = textArea.getDocument().getDefaultRootElement();
        int row = root.getElementIndex(caretPos) + 1;
        int col = caretPos - root.getElement(row - 1).getStartOffset() + 1;
        int totalChars = textArea.getText().length();
        statusBar.setText(String.format("Ln %d, Col %d    |    %d Chars    |    %.0f%%    |    UTF-8", row, col, totalChars, zoomFactor * 100));
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New Tab":
                new Editor();
                break;
            case "New Window":
                new Editor();
                break;
            case "Open":
                openFile();
                break;
            case "Save":
                saveFile();
                break;
            case "Save As":
                saveFileAs();
                break;
            case "Save All":
                saveFile();
                break;
            case "Page Setup":
                // Page setup logic
                break;
            case "Print":
                try {
                    textArea.print();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
                break;
            case "Close Tab":
                this.dispose();
                break;
            case "Close Window":
                this.dispose();
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Undo":
                // Undo logic
                break;
            case "Cut":
                textArea.cut();
                break;
            case "Copy":
                textArea.copy();
                break;
            case "Paste":
                textArea.paste();
                break;
            case "Delete":
                textArea.replaceSelection("");
                break;
            case "Find":
                findText();
                break;
            case "Find Next":
                // Find next logic
                break;
            case "Find Previous":
                // Find previous logic
                break;
            case "Replace":
                // Replace logic
                break;
            case "Go To":
                // Go to logic
                break;
            case "Select All":
                textArea.selectAll();
                break;
            case "Time/Date":
                textArea.insert(new java.util.Date().toString(), textArea.getCaretPosition());
                break;
            case "Font":
                // Font logic
                break;
            case "Zoom In":
                zoomFactor += 0.1f;
                textArea.setFont(textArea.getFont().deriveFont(textArea.getFont().getSize() * zoomFactor));
                updateStatusBar();
                break;
            case "Zoom Out":
                zoomFactor -= 0.1f;
                textArea.setFont(textArea.getFont().deriveFont(textArea.getFont().getSize() * zoomFactor));
                updateStatusBar();
                break;
            case "Restore Default Zoom":
                zoomFactor = 1.0f;
                textArea.setFont(new Font("Arial", Font.PLAIN, 12));
                updateStatusBar();
                break;
            case "Status Bar":
                statusBar.setVisible(!statusBar.isVisible());
                break;
            case "Word Wrap":
                textArea.setLineWrap(!textArea.getLineWrap());
                break;
        }
    }

    private void openFile() {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
            }
        }
    }

    private void saveFileAs() {
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
            }
        }
    }

    private void findText() {
        String find = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (find != null && !find.isEmpty()) {
            String content = textArea.getText();
            int index = content.indexOf(find);
            if (index != -1) {
                textArea.setCaretPosition(index);
                textArea.setSelectionStart(index);
                textArea.setSelectionEnd(index + find.length());
            } else {
                JOptionPane.showMessageDialog(this, "Text not found");
            }
        }
    }

    public static void main(String[] args) {
        new Editor();
    }
}
