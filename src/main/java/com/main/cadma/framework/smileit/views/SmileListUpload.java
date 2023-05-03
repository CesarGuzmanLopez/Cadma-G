package com.main.cadma.framework.smileit.views;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.main.cadma.interfaces.SmilesUploadInterface;
import com.main.shared.domain.cadma.interfaces.EventComplete;
import com.main.smileit.interfaces.MoleculeListInterface;

import java.awt.GridBagLayout;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.awt.GridBagConstraints;

@SuppressWarnings("java:S1948")
public class SmileListUpload extends JFrame implements SmilesUploadInterface {
    private JLabel selectedFolderUpload;
    private JLabel selectedFolderPath;
    private JFileChooser savePath;
    private JFileChooser uploadFile;
    private String path;
    private JTextField nameField;
    private MoleculeListInterface allMoleculesGenerated;
    private Deque<EventComplete> completeEvents;

    public SmileListUpload() {
        setTitle("Upload smiles");
        setSize(300, 200);
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
        nameLabel.setPreferredSize(new java.awt.Dimension(70, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        nameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, gbc);
        final JLabel labelSmileName = new JLabel("Select path to save files:  ");
        labelSmileName.setPreferredSize(new java.awt.Dimension(70, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelSmileName, gbc);
        final JButton selectSaveFolder = new JButton("Select path");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        selectSaveFolder.addActionListener(e -> selectSave());
        add(selectSaveFolder, gbc);
        selectedFolderPath = new JLabel();
        selectedFolderPath.setPreferredSize(new java.awt.Dimension(210, 30));
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(selectedFolderPath, gbc);
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
        selectedFolderUpload = new JLabel();
        selectedFolderUpload.setPreferredSize(new java.awt.Dimension(210, 30));
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(selectedFolderUpload, gbc);
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
        dispose();
    }

    private void importSmiles() {
        try {
            final String name = nameField.getText();
            if (name.isEmpty()) {
                throw new IllegalStateException("Error, the name is empty");
            }
            final String namePath = selectedFolderPath.getText();
            if (namePath.isEmpty()) {
                throw new IllegalStateException("Error, the path is empty");
            }
            for (final EventComplete event : completeEvents) {
                event.execute();
            }
        } catch (final IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return ;
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
            selectedFolderPath.setText("Selected");
        } else {
            savePath = null;
            selectedFolderPath.setText("");
        }
    }

    private void selectUpload() {
        uploadFile = new JFileChooser();
        uploadFile.setCurrentDirectory(new File(path));
        int outPut = uploadFile.showSaveDialog(this);
        uploadFile.setAcceptAllFileFilterUsed(false);
        path = uploadFile.getCurrentDirectory().getPath();
        if (outPut == JFileChooser.APPROVE_OPTION) {
            selectedFolderUpload.setText("Selected");
        } else {
            uploadFile = null;
            selectedFolderUpload.setText("");
        }
    }

    @Override
    public void showUpload() {
        repaint();
        setVisible(true);

    }

    @Override
    public void addUploadEvent(EventComplete completeEvent) {
        completeEvents.add(completeEvent);
    }

    @Override
    public String getPathPrincipal() {
        if (savePath == null || savePath.getSelectedFile().getPath().isEmpty()) {
            throw new IllegalStateException("Error, the path is empty");
        }
        return savePath.getSelectedFile().getPath();
    }

    @Override
    public String getNamePrincipalMolecule() {
        if (nameField.getText().isEmpty()) {
            throw new IllegalStateException("Error, the name is empty");
        }
        return nameField.getText();
    }

    @Override
    public String getFileToUpload() {
        if (uploadFile == null || uploadFile.getSelectedFile().getPath().isEmpty()) {
            throw new IllegalStateException("Error, the path is empty");
        }
        return uploadFile.getSelectedFile().getPath();
    }

}
