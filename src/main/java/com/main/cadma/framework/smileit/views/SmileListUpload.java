package com.main.cadma.framework.smileit.views;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.main.smileit.interfaces.EventInterface;
import com.main.smileit.interfaces.MoleculeListInterface;

import java.awt.GridBagLayout;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.awt.GridBagConstraints;

@SuppressWarnings("java:S1948")
public class SmileListUpload extends JFrame {

    private JFileChooser savePath;
    private JFileChooser uploadFile;
    private String path;
    private String parentPath;
    private JLabel selectedFolder;
    private JTextField nameField;
    private MoleculeListInterface allMoleculesGenerated;
    private Deque<EventInterface> completeEvents;

    public SmileListUpload() {
        setTitle("Generate");
        setSize(550, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        initialize();
        completeEvents = new LinkedList<>();
        path = System.getProperty("user.dir");
    }

    /**
     * @return allMoleculesGenerated
     */
    public MoleculeListInterface getAllMoleculesGenerated() {
        if (allMoleculesGenerated == null) {
            throw new IllegalStateException("Error, The molecules have not been generated");
        }
        return allMoleculesGenerated;
    }

    private void initialize() {
        final GridBagConstraints gbc = new GridBagConstraints();

        final JLabel nameLabel = new JLabel("Name ");
        nameLabel.setPreferredSize(new java.awt.Dimension(210, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        nameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, gbc);

        final JLabel labelSmileName = new JLabel("Select path to save files:  ");
        labelSmileName.setPreferredSize(new java.awt.Dimension(210, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelSmileName, gbc);
        final JButton selectSaveFolder = new JButton("Select path");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        selectSaveFolder.addActionListener(e -> selectSave());
        add(selectSaveFolder, gbc);

        final JLabel labelUpload = new JLabel("Select file to upload:  ");
        labelUpload.setPreferredSize(new java.awt.Dimension(210, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelUpload, gbc);
        final JButton buttonUpload = new JButton("Select file");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonUpload.addActionListener(e -> selectUpload());
        add(buttonUpload, gbc);
        final JButton cancelButton = new JButton("Cancel");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton, gbc);
        final JButton importSmiles = new JButton("Import Smiles");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        importSmiles.addActionListener(e -> importSmiles());
        add(importSmiles, gbc);
    }

    private void importSmiles() {
        final String name = nameField.getText();
        if (name.isEmpty()) {
            throw new IllegalStateException("Error, the name is empty");
        }
        if (path == null) {
            throw new IllegalStateException("Error, the path is empty");
        }
        if (parentPath == null) {
            throw new IllegalStateException("Error, the parent path is empty");
        }
        final File file = new File(path);
        if (!file.exists()) {
            throw new IllegalStateException("Error, the file does not exist");
        }
        if (!file.isFile()) {
            throw new IllegalStateException("Error, the file is not a file");
        }
        final String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (!extension.equals("txt")) {
            throw new IllegalStateException("Error, the file is not a txt file");
        }
        dispose();
    }

    private void selectSave() {
        savePath = new JFileChooser();
        savePath.setCurrentDirectory(new File(path));
        savePath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int outPut = savePath.showSaveDialog(this);
        savePath.setAcceptAllFileFilterUsed(false);
        path = savePath.getCurrentDirectory().getPath();
        if (outPut == JFileChooser.APPROVE_OPTION) {
            selectedFolder.setText("Selected");
        } else {
            savePath = null;
            selectedFolder.setText("");
        }
    }

    private void selectUpload() {
        uploadFile = new JFileChooser();
        uploadFile.setCurrentDirectory(new File(path));
        int outPut = uploadFile.showSaveDialog(this);
        uploadFile.setAcceptAllFileFilterUsed(false);
        path = uploadFile.getCurrentDirectory().getPath();
        if (outPut == JFileChooser.APPROVE_OPTION) {
            selectedFolder.setText("Selected");
        } else {
            uploadFile = null;
            selectedFolder.setText("");
        }
    }

    /**
     * @return parent path
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * @param completeEvent
     */
    public void addEventUpload(final EventInterface completeEvent) {
        completeEvents.add(completeEvent);
    }
}
