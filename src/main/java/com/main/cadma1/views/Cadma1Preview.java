package com.main.cadma1.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.main.cadma1.interfaces.EventSelectedMolecules;
import com.main.common.Constant;
import com.main.common.methods.ImageGenerator;
import com.main.shared.domain.Molecule;

import net.miginfocom.swing.MigLayout;


public class Cadma1Preview extends JFrame{

	private JTextField idField;
	private JTextField NameField;
	private JTextField smileField;
	private JTextField SmartFielld;
	private JTextField namePrincipalField;
	private JTextField smilePrincipalField;
	private JTextField noSubst;
    private Molecule moleculePrincipal;
    private List<Molecule> substitutes;
    private List<Molecule> generate;
    private Molecule moleculeSelected;
    private String path;
    private List<Molecule> selectedMolecules;
    private List<EventSelectedMolecules> eventsComplete;
    JPanel panelImg;
    public Cadma1Preview(){
        super("Generator");

        eventsComplete = new LinkedList<>();
        selectedMolecules = new ArrayList<>();
        moleculeSelected = null;
    }
    public void addEventSelectedMolecules(EventSelectedMolecules a){
        eventsComplete.add(a);
    }
    public void initialize(Molecule molecule, List<Molecule> substitutes, List<Molecule> generate, String path){
        if(molecule == null || substitutes == null || generate == null ){
            throw new IllegalArgumentException("Molecule, substitutes, generate and path can't be null");
        }
        this.moleculePrincipal = molecule;
        this.substitutes = substitutes;
        this.generate = generate;
        this.path = path;
        Molecule.deleteAllID();
        for(Molecule mol : generate){
            mol.setSmart(mol.getSmile());
            mol.setID();
            mol.setPathImage(System.getProperty("file.separator") + Constant.PATH_IMG + System.getProperty("file.separator") + mol.getName() + ".png");
        }
        showSelection();
    }

    public void showSelection(){
		setBounds(102, 100, 727, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("", "[70px][grow][grow][grow][]", "[][15px,grow][15px][][]"));

		JLabel lblNewLabel_2 = new JLabel("Principal");
		panel.add(lblNewLabel_2, "flowx,cell 1 0");

		JLabel lblNewLabel_4 = new JLabel("sustituents");
		panel.add(lblNewLabel_4, "cell 2 0,alignx left");

		JLabel lblNewLabel_1 = new JLabel("Name: ");
		panel.add(lblNewLabel_1, "cell 0 1,alignx trailing,aligny center");

		namePrincipalField = new JTextField();
        namePrincipalField.setText(moleculePrincipal.getName());
		panel.add(namePrincipalField, "cell 1 1,alignx left");
		namePrincipalField.setColumns(10);

        JPanel substitutesPanel = new JPanel();
        substitutesPanel.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPaneSubs = new JScrollPane(substitutesPanel);
        scrollPaneSubs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneSubs.setPreferredSize(new Dimension(100, 100));

        for (Molecule mol : substitutes) {
            JLabel labelText = new JLabel(mol.getName() + " ");
            substitutesPanel.add(labelText);
        }

        panel.add(scrollPaneSubs, "cell 2 1 1 3,grow");

		JLabel lblNewLabel = new JLabel("Smile");
		panel.add(lblNewLabel, "cell 0 2,alignx trailing,aligny center");

		smilePrincipalField = new JTextField();
        smilePrincipalField.setEditable(false);
        smilePrincipalField.setText(moleculePrincipal.getSmile());
		panel.add(smilePrincipalField, "cell 1 2,alignx left");
		smilePrincipalField.setColumns(10);

		JLabel lblNoSustituents = new JLabel("No. substituents");
		panel.add(lblNoSustituents, "cell 0 3,alignx trailing");

		noSubst = new JTextField();
		noSubst.setText(" " + substitutes.size() + " ");
        panel.add(noSubst, "cell 1 3,alignx left");
        noSubst.setEditable(false);
		noSubst.setColumns(10);

		JButton btnAccept = new JButton("Accept");
		panel.add(btnAccept, "cell 3 3 1 2,alignx center,aligny center");
        btnAccept.addActionListener(e -> accept());

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(8, 12, 12, 8));
		getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{49, 312, 0, 0, 0};

		gbl_panel_2.rowHeights = new int[]{19, 19, 19, 19, 183, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		JLabel lblId = new JLabel("ID");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		panel_2.add(lblId, gbc_lblId);

		idField = new JTextField();
		idField.setEditable(false);
		idField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_2.add(idField, gbc_textField);

		JLabel lblName = new JLabel("Name: ");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel_2.add(lblName, gbc_lblName);

		NameField = new JTextField();
		NameField.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.NORTH;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_2.add(NameField, gbc_textField_1);

		JLabel lblSmile = new JLabel("Smile: ");
		GridBagConstraints gbc_lblSmile = new GridBagConstraints();
		gbc_lblSmile.anchor = GridBagConstraints.EAST;
		gbc_lblSmile.insets = new Insets(0, 0, 5, 5);
		gbc_lblSmile.gridx = 0;
		gbc_lblSmile.gridy = 2;
		panel_2.add(lblSmile, gbc_lblSmile);

		smileField = new JTextField();
		smileField.setEditable(false);
		smileField.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.NORTH;
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel_2.add(smileField, gbc_textField_2);

		JLabel lblInchi = new JLabel("Smart:");
		GridBagConstraints gbc_lblInchi = new GridBagConstraints();
		gbc_lblInchi.anchor = GridBagConstraints.EAST;
		gbc_lblInchi.insets = new Insets(0, 0, 5, 5);
		gbc_lblInchi.gridx = 0;
		gbc_lblInchi.gridy = 3;
		panel_2.add(lblInchi, gbc_lblInchi);

		SmartFielld = new JTextField();
		SmartFielld.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.NORTH;
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		panel_2.add(SmartFielld, gbc_textField_3);

		JLabel lblImg = new JLabel("Img");
		GridBagConstraints gbc_lblImg = new GridBagConstraints();
		gbc_lblImg.insets = new Insets(0, 0, 5, 5);
		gbc_lblImg.gridx = 0;
		gbc_lblImg.gridy = 4;
		panel_2.add(lblImg, gbc_lblImg);

		panelImg = new JPanel();
		panelImg.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2_1 = new GridBagConstraints();
		gbc_panel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2_1.fill = GridBagConstraints.BOTH;
		gbc_panel_2_1.gridx = 1;
		panel_2.add(panelImg, gbc_panel_2_1);

		JButton btnModify = new JButton("Modify");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 5;
        btnModify.addActionListener(e -> modifyMolecule());
		panel_2.add(btnModify, gbc_btnNewButton);

		JPanel panel_Elementos = new JPanel();
		panel_Elementos.setBorder(new EmptyBorder(10, 20, 10, 10));
		panel_Elementos.setLayout(new GridLayout(0, 1));


        List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
        for(Molecule moleculeTemp : generate) {
            final JCheckBox checkTemporal = new JCheckBox(moleculeTemp.getName());
            selectedMolecules.add(moleculeTemp);
            checkTemporal.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getX() > checkTemporal.getBounds().x) {
                        checkTemporal.setSelected(!checkTemporal.isSelected());
                    }
                    if(checkTemporal.isSelected()){
                        selectedMolecules.add(moleculeTemp);
                    } else {
                        selectedMolecules.remove(moleculeTemp);
                    }
                    moleculeSelected = moleculeTemp;
                    updateFields();

                }
            });
            checkTemporal.setSelected(true);
            checkBoxes.add(checkTemporal);
            panel_Elementos.add(checkTemporal);
        }

		JScrollPane scrollPane = new JScrollPane(panel_Elementos);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		getContentPane().add(scrollPane, BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		scrollPane.setColumnHeaderView(panel_1);

		JCheckBox chckbxSelectAll = new JCheckBox("Select all");
		panel_1.add(chckbxSelectAll);
        chckbxSelectAll.setSelected(true);
        setVisible(true);
        chckbxSelectAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (chckbxSelectAll.isSelected()) {
                    chckbxSelectAll.setText("Deremoselect all");
                    selectedMolecules.removeAll(selectedMolecules);
                    selectedMolecules.addAll(generate);

                } else {
                    chckbxSelectAll.setText("Select all");
                    selectedMolecules.removeAll(selectedMolecules);

                }
                for (JCheckBox checkBox : checkBoxes) {
                    checkBox.setSelected(chckbxSelectAll.isSelected());
                }


                updateFields();
            }
        });
    }
    private void updateFields() {
        if (moleculeSelected == null) {
            idField.setText("");
            NameField.setText("");
            smileField.setText("");
            SmartFielld.setText("");
            panelImg.removeAll();
            return;
        }
        idField.setText(Integer.toString(moleculeSelected.getID()));
        NameField.setText(moleculeSelected.getName());
        smileField.setText(moleculeSelected.getSmile());
        SmartFielld.setText(moleculeSelected.getSmart());
        panelImg.removeAll();
        panelImg.setBackground(Color.WHITE);
        try {
            InputStream imageInput = new BufferedInputStream(new FileInputStream(path + moleculeSelected.getPathImage()));
            BufferedImage imageMolecule = ImageIO.read(imageInput);
            imageMolecule = ImageGenerator.resize(imageMolecule, 250, 250);
            JLabel picLabel2 = new JLabel(new ImageIcon(imageMolecule));
            panelImg.add(picLabel2);
            panelImg.revalidate();
            panelImg.repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void modifyMolecule(){
        moleculeSelected.setName(NameField.getText());
        moleculeSelected.setSmart(SmartFielld.getText());
    }
    private void accept(){
        moleculePrincipal.setName(NameField.getText());
        for(EventSelectedMolecules a : eventsComplete){
            a.runSelected( selectedMolecules, moleculePrincipal);
        }
        this.dispose();

    }
}
