package com.main.cadma1.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.main.cadma1.domain.DataCadma;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Cadma1view extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DataCadma dataCadma;
    private String path;
    private JTextField txtNameprocess;
    private JTextField txtNumberofmolecules;
    public Cadma1view() {
        super("CADMA 1");
        setSize(656, 462);
        setMinimumSize(new java.awt.Dimension(400, 180));
        contentPane = new JPanel();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gblContentPane = new GridBagLayout();
        gblContentPane.columnWidths = new int[] { 0, 0 };
        gblContentPane.rowHeights = new int[] { 0, 0, 0, 0 };
        gblContentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gblContentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
        contentPane.setLayout(gblContentPane);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        contentPane.add(panel, gbc_panel);
        panel.setLayout(new MigLayout("", "[][grow][grow]", "[][][][][]"));

        JLabel lblProcessName = new JLabel("Process name: ");
        panel.add(lblProcessName, "cell 0 1,alignx trailing");

        txtNameprocess = new JTextField();
        panel.add(txtNameprocess, "cell 1 1,growx");
        txtNameprocess.setColumns(10);

        JLabel lblNumberOfMolecules = new JLabel("Number of molecules: ");
        panel.add(lblNumberOfMolecules, "cell 0 2,alignx trailing");

        txtNumberofmolecules = new JTextField();
        panel.add(txtNumberofmolecules, "cell 1 2,growx");
        txtNumberofmolecules.setColumns(10);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"General attributes", "Implemented descriptors"}));
        panel.add(comboBox, "flowx,cell 2 3,growx");

        JButton btnNewButton = new JButton("Generate");
        panel.add(btnNewButton, "cell 2 3");

        JButton btnGetSmiles = new JButton("Get list of smiles");
        panel.add(btnGetSmiles, "cell 1 4,alignx center");

    }
    public void initialize(DataCadma dataCadma, String path) {
        this.dataCadma = dataCadma;
        this.path = path;
        
        setVisible(true);
    }

}
