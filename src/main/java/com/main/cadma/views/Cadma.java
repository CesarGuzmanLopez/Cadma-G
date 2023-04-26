package com.main.cadma.views;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.cadma.domain.CadmaProcess;
import com.main.cadma.interfaces.EventUpdateData;
import com.main.cadma.interfaces.StatusProcess;
import com.main.cadma.views.panels.RequiredPanel;
import com.main.cadma.views.panels.Menu;

@SuppressWarnings("java:S1948")
public class Cadma extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CadmaProcess cadmaProcess;
    private RequiredPanel panelSmileIt;
    private RequiredPanel panelCadma1;
    private RequiredPanel panelCadma2;
    private JLabel nameOfPath;
    private JLabel nameOfProcess;
    private JLabel numberOfSubstituents;
    private JLabel numberOfMoleculesGenerated;

    /**
     * @param cadmaProcess
     * @since 1.0
     * Esta fimcopm crea la ventana principal de la aplicacion
     * aqui se mostrara toda la informacion previa si ya se tienen avances
     */
    public Cadma(final CadmaProcess cadmaProcess) {
        super("CADMA - Computer Aided Design for M Applications");
        setSize(550, 450);
        setMinimumSize(new Dimension(400, 180));
        contentPane = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gblContentPane = new GridBagLayout();
        gblContentPane.columnWidths = new int[] { 0, 0 };
        gblContentPane.rowHeights = new int[] { 0, 0 };
        gblContentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gblContentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        contentPane.setLayout(gblContentPane);

        this.cadmaProcess = cadmaProcess;

        cadmaProcess.setEventUpdateData(new EventUpdateData() {
            @Override
            public void execute() {
                nameOfProcess.setText("Process: " + cadmaProcess.getNameOfProcess());
                numberOfSubstituents.setText("Number of substituents: " + cadmaProcess.getNumberOfSubstituents());
                numberOfMoleculesGenerated.setText("Number of molecules generated: " + cadmaProcess.getNumberOfMoleculesGenerated());
                nameOfPath.setText("Path: " + cadmaProcess.getPath());
                numberOfMoleculesGenerated.setVisible(true);
                nameOfProcess.setVisible(true);
                numberOfSubstituents.setVisible(true);


            }
        });
        }

    /** initialize all JPanels. */
    public void initialize() {
        Menu menu = new Menu();
        menu.setActions((String a) -> {
            try {
                cadmaProcess.importCadmaProcess(a);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        setJMenuBar(menu);
        initializeRouteLabel();
        initializePanelSmileIt();
        initializePanelCadma1();
        initializePanelCadma2();
        setVisible(true);
        panelSmileIt.enableButtons();
        panelSmileIt.setPanelNext(panelCadma1);
        panelCadma1.setPanelNext(panelCadma2);
    }

    private void initializeRouteLabel() {
        JPanel panelPrincipal2 = new JPanel();
        GridBagLayout gblContentPane = new GridBagLayout();
        gblContentPane.columnWidths = new int[] { 0, 0 };
        gblContentPane.rowHeights = new int[] { 0, 0 };
        gblContentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gblContentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        panelPrincipal2.setLayout(gblContentPane);





        GridBagConstraints gbcPanelPrincipal = new GridBagConstraints();
        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 0;

        /*

         */

        nameOfPath = new JLabel("");
        nameOfProcess        = new JLabel("Please Generate the Smiles or Upload a file or import a project in the menu");
        numberOfSubstituents = new JLabel("");
        numberOfMoleculesGenerated = new JLabel(" ");
        panelPrincipal2.add(nameOfPath, gbcPanelPrincipal);
        gbcPanelPrincipal.gridy = 1;
        panelPrincipal2.add(nameOfProcess, gbcPanelPrincipal);
        gbcPanelPrincipal.gridy = 2;
        panelPrincipal2.add(numberOfSubstituents, gbcPanelPrincipal);
        gbcPanelPrincipal.gridy = 3;
        panelPrincipal2.add(numberOfMoleculesGenerated, gbcPanelPrincipal);


        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 0;


        contentPane.add(panelPrincipal2, gbcPanelPrincipal);
    }

    private void initializePanelSmileIt() {

        panelSmileIt = new RequiredPanel("SMILE-IT", cadmaProcess.getSmileGenerate());

        GridBagConstraints gbcPanelPrincipal = new GridBagConstraints();
        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 1;
        contentPane.add(panelSmileIt, gbcPanelPrincipal);
        panelSmileIt.setStatus(StatusProcess.EMPTY);
    }

    private void initializePanelCadma1() {
        panelCadma1 = new RequiredPanel("CADMA-1", cadmaProcess.getCadma1Generate());
        GridBagConstraints gbcPanelPrincipal = new GridBagConstraints();
        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 2;
        contentPane.add(panelCadma1, gbcPanelPrincipal);
        panelCadma1.setStatus(StatusProcess.EMPTY);
    }

    private void initializePanelCadma2() {
        panelCadma2 = new RequiredPanel("CADMA-2", cadmaProcess.getCadma2Generate());
        GridBagConstraints gbcPanelPrincipal = new GridBagConstraints();
        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 3;
        contentPane.add(panelCadma2, gbcPanelPrincipal);
        panelCadma2.setStatus(StatusProcess.EMPTY);
    }
}
