package com.main.cadma.views;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.cadma.domain.CadmaProcess;
import com.main.cadma.interfaces.StatusProcess;
import com.main.cadma.views.panels.RequiredPanel;
import com.main.cadma.views.panels.Menu;

@SuppressWarnings("java:S1948")
public class Cadma extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CadmaProcess cadmaProcess;

    /**
     * @param cadmaProcess
     */
    public Cadma(final CadmaProcess cadmaProcess) {
        super("CADMA - Computer Aided Design for M Applications");
        setSize(850, 300);
        setMinimumSize(new Dimension(400, 180));
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gblContentPane = new GridBagLayout();
        gblContentPane.columnWidths = new int[] {0, 0 };
        gblContentPane.rowHeights = new int[] {0, 0 };
        gblContentPane.columnWeights = new double[] {1.0, Double.MIN_VALUE };
        gblContentPane.rowWeights = new double[] {1.0, Double.MIN_VALUE };
        this.cadmaProcess = cadmaProcess;
        contentPane.setLayout(gblContentPane);
    }

    /** initialize all JPanels. */
    public void initialize() {
        setJMenuBar(new Menu());
        initializeRouteLabel();
        initializePanelSmileIt();
        initializePanelCadma1();
        initializePanelCadma2();
        setVisible(true);

    }

    private void initializeRouteLabel() {
        JPanel panelPrincipal2 = new JPanel();
        GridBagConstraints gbcPanelPrincipal = new GridBagConstraints();
        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 0;
        /*
         * un Jlabel que contenga el nombre otro que de el porcentaje de avance en el
         * proceso y un boton de cancelar
         */
        JLabel nameOfProcess = new JLabel("No es no");
        JLabel percentageOfProcess = new JLabel("Lo he padaso bien ");
        JLabel cancelProcess = new JLabel("Y es bastante mas de lo que jamas soñe");
        panelPrincipal2.add(nameOfProcess);
        panelPrincipal2.add(percentageOfProcess);
        panelPrincipal2.add(cancelProcess);

        contentPane.add(panelPrincipal2, gbcPanelPrincipal);
    }

    private void initializePanelSmileIt() {
        RequiredPanel panelPrincipal2 = new RequiredPanel("SMILE-IT", cadmaProcess.getSmileGenerate());
        GridBagConstraints gbcPanelPrincipal = new GridBagConstraints();
        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 1;
        contentPane.add(panelPrincipal2, gbcPanelPrincipal);
        panelPrincipal2.setStatus(StatusProcess.EMPTY);
    }

    private void initializePanelCadma1() {
        RequiredPanel panelPrincipal2 = new RequiredPanel("CADMA-1", cadmaProcess.getCadma1Generate());
        GridBagConstraints gbcPanelPrincipal = new GridBagConstraints();
        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 2;
        contentPane.add(panelPrincipal2, gbcPanelPrincipal);
        panelPrincipal2.setStatus(StatusProcess.EMPTY);

    }

    private void initializePanelCadma2() {
        RequiredPanel panelPrincipal2 = new RequiredPanel("CADMA-2", cadmaProcess.getCadma1Generate());
        GridBagConstraints gbcPanelPrincipal = new GridBagConstraints();
        gbcPanelPrincipal.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelPrincipal.gridx = 0;
        gbcPanelPrincipal.gridy = 3;
        contentPane.add(panelPrincipal2, gbcPanelPrincipal);
        panelPrincipal2.setStatus(StatusProcess.EMPTY);
    }
}
