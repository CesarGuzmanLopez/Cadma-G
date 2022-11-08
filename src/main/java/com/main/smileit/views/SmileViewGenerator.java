package com.main.smileit.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.main.smileit.domain.models.Molecule;
import com.main.smileit.domain.models.MoleculesList;
import com.main.smileit.domain.models.Smiles;
import com.main.smileit.interfaces.EventInterface;
import com.main.smileit.interfaces.EventDescriptionInterface;
import com.main.smileit.interfaces.MoleculeDataFactoryInterface;
import com.main.smileit.interfaces.MoleculeGraphPainterInterface;
import com.main.smileit.interfaces.MoleculeListInterface;
import com.main.smileit.interfaces.SmileVerificationInterface;
import com.main.smileit.views.panels.MoleculePanel;
import com.main.smileit.views.panels.OptionPanel;
import com.main.smileit.views.panels.WindowsGenerate;

/**
 * @author Cesar G. Guzman Lopez
 * @version 1.0
 * @since 1.0
 */

@SuppressWarnings("java:S1948")
public final class SmileViewGenerator extends javax.swing.JFrame {
    private static final long serialVersionUID = 2L;
    private MoleculePanel moleculePanelPrincipal;
    private OptionPanel optionPanel;
    private MoleculePanel moleculePreviewPanel;
    private JButton generateButton;
    private boolean listGenerated;
    private MoleculeListInterface allMoleculesGenerated;
    // entry point for the program
    private JTextField textFieldSmile;
    private JCheckBox checkBoxHydrogenImplicit;
    private JButton drawSmileButton;
    private WindowsGenerate windowsGenerate;
    // Dependencies to inject
    private SmileVerificationInterface verifySmile;
    private MoleculeListInterface smilesList;
    private MoleculeGraphPainterInterface moleculeGraphPainter;
    private Deque<EventInterface> completeEvents;
    private MoleculeDataFactoryInterface moleculeFactory;
    private EventDescriptionInterface descriptionGenerator;
    // Check:OFF: MagicNumber
    /*
     * Constructor of the class
     * @author Cesar G. Guzman Lopez
     *
     * @version 1.0
     *
     * @since 1.0
     */

    public SmileViewGenerator(final MoleculeListInterface smilesList, final SmileVerificationInterface verifySmile,
            final MoleculeGraphPainterInterface moleculeGraphPainter,
            final MoleculeDataFactoryInterface moleculeFactory) {
        super("Smile-it");
        setSize(850, 550);
        setMinimumSize(new Dimension(750, 500));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.smilesList = smilesList;
        this.verifySmile = verifySmile;
        this.moleculeGraphPainter = moleculeGraphPainter;
        this.moleculeFactory = moleculeFactory;
        this.listGenerated = false;
        completeEvents = new LinkedList<>();

    }
    /**
        * Method to initialize the components of the view.
        * @param descriptionGenerated
    */
    public void setDescriptionGenerate(final EventDescriptionInterface descriptionGenerated) {
        this.descriptionGenerator = descriptionGenerated;
    }

    private void createAndDrawSmile() {
        final String smile = textFieldSmile.getText();
        if (!verifySmile.isValid(smile)) {
            moleculePanelPrincipal.setMolecule(null);
            generateButton.setEnabled(false);
        }
        final boolean implicitHydrogen = checkBoxHydrogenImplicit.isSelected();
        try {
            final Smiles smileH = new Smiles(smile, smile, "Select sites over molecule to substitute", implicitHydrogen,
                    verifySmile);
            moleculePanelPrincipal.setMolecule(new Molecule(smileH, moleculeFactory));
        } catch (Exception e) { // NOSONAR
            generateButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        generateButton.setEnabled(true);
    }

    private void generate() {
        final MoleculesList selected = MoleculesList.createMoleculesList(verifySmile, moleculeFactory,
                optionPanel.getListMolecule());
        windowsGenerate = new WindowsGenerate(moleculePanelPrincipal.getMolecule(), selected);
        windowsGenerate.setEventDescription(descriptionGenerator);
        windowsGenerate.addCompleteEvent(() -> {
            listGenerated = true;
            windowsGenerate.dispose();
            setVisible(false);
            allMoleculesGenerated = windowsGenerate.getAllMoleculesGenerated();

        });
        for (EventInterface completeEvent : completeEvents) {
            windowsGenerate.addCompleteEvent(completeEvent);
        }
    }
    /**
     * Method to add a complete event.
     * @param event
     */
    public void addGenerateEvent(final EventInterface event) {
        completeEvents.add(event);

    }

    /**
     * Method to get the list of molecules generated.
     * @return the list of molecules generated
     */
    public MoleculeListInterface getAllMoleculesGenerated() {
        return allMoleculesGenerated;
    }

    /** initialize all JPanels. */
    public void initialize() {

        setLayout(new GridBagLayout());
        // the order is important in the initialization of the components
        initializeEntrySmile(0, 0, 1, 1);
        initializeMoleculePanelPrincipal(0, 1, 1, 1);
        initializeMoleculePreviewPanel(2, 1, 1, 1);
        initializeOptionPanel(1, 1, 0, 1);
        initializeActionGeneratorPanel(0, 3, 3, 1);
        drawSmileButton.addActionListener(e -> createAndDrawSmile());
        generateButton.addActionListener(e -> generate());
        drawSmileButton.setEnabled(true);
        generateButton.setEnabled(false);
        checkBoxHydrogenImplicit.setSelected(true);
        setVisible(true);
    }

    private void initializeEntrySmile(final int gridx, final int gridy, final double weightx, final double weighty) {
        final JPanel panelSmile = new JPanel();
        panelSmile.setLayout(new GridBagLayout());
        EmptyBorder margin = new EmptyBorder(10, 10, 10, 10);

        panelSmile.setBorder(new CompoundBorder(javax.swing.BorderFactory.createTitledBorder(""), margin));

        final GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.ipadx = 10;
        final JLabel labelSmile = new JLabel("Smile: ");
        panelSmile.add(labelSmile, gbcPanel);
        textFieldSmile = new JTextField();
        textFieldSmile.setPreferredSize(new Dimension(200, 30));
        textFieldSmile.setMinimumSize(new Dimension(100, 30));
        gbcPanel.gridx = 1;
        gbcPanel.gridy = 0;
        panelSmile.add(textFieldSmile, gbcPanel);
        checkBoxHydrogenImplicit = new JCheckBox("Implicit hydrogen");

        gbcPanel.gridx = 4;
        gbcPanel.gridy = 0;
        gbcPanel.anchor = GridBagConstraints.EAST;
        panelSmile.add(checkBoxHydrogenImplicit, gbcPanel);
        gbcPanel.ipadx = 0;
        gbcPanel.anchor = GridBagConstraints.EAST;

        gbcPanel.gridx = 5;
        gbcPanel.gridy = 0;
        drawSmileButton = new JButton("Draw");
        panelSmile.add(drawSmileButton, gbcPanel);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.gridwidth = 3;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        gbc.insets = new java.awt.Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(panelSmile, gbc);
    }

    private void initializeMoleculePanelPrincipal(final int gridx, final int gridy, final double weightx,
            final double weighty) {
        moleculePanelPrincipal = new MoleculePanel(moleculeGraphPainter);
        moleculePanelPrincipal.setPreferredSize(new Dimension(300, 300));
        moleculePanelPrincipal.setMaximumSize(new Dimension(400, 400));
        moleculePanelPrincipal.setMinimumSize(new Dimension(200, 200));
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.anchor = GridBagConstraints.CENTER;
        add(moleculePanelPrincipal, gbc);
    }

    private void initializeOptionPanel(final int gridx, final int gridy, final double weightx, final double weighty) {
        optionPanel = new OptionPanel(smilesList, verifySmile, moleculePreviewPanel);
        optionPanel.setPreferredSize(new Dimension(200, 300));
        optionPanel.setMaximumSize(new Dimension(250, 400));
        optionPanel.setMinimumSize(new Dimension(190, 200));
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.anchor = GridBagConstraints.CENTER;
        add(optionPanel, gbc);
    }
    /** return true if the list of molecules generated.
     * @return if the list is generated
     */
    public boolean isListGenerated() {
        return listGenerated;
    }

    private void initializeMoleculePreviewPanel(final int gridx, final int gridy, final double weightx,
            final double weighty) {
        moleculePreviewPanel = new MoleculePanel(moleculeGraphPainter);
        moleculePreviewPanel.setPreferredSize(new Dimension(200, 200));
        moleculePreviewPanel.setMaximumSize(new Dimension(300, 300));
        moleculePreviewPanel.setMinimumSize(new Dimension(180, 180));
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.anchor = GridBagConstraints.CENTER;
        add(moleculePreviewPanel, gbc);
    }

    private void initializeActionGeneratorPanel(final int gridx, final int gridy, final double weightx,
            final double weighty) {
        final JPanel panelAction = new JPanel();
        panelAction.setLayout(new GridBagLayout());
        panelAction.setBorder(javax.swing.BorderFactory.createTitledBorder("Action"));
        final GridBagConstraints gbcPanel = new GridBagConstraints();

        generateButton = new JButton("Generate");
        panelAction.add(generateButton, gbcPanel);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(panelAction, gbc);
    }

    /**
     *
     * @return the Molecule principal.
     */
    public Molecule getPrincipal() {
        return moleculePanelPrincipal.getMolecule();
    }

    /**
     *  @return get the path parent of all files
     */
    public String getParentPath() {
        if (windowsGenerate != null) {
            return windowsGenerate.getParentPath();
        }
        throw new IllegalStateException("The path is not defined");
    }

}
