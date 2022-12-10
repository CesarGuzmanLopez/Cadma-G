package com.main.cadma.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.BufferedInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.main.common.methods.ImageGenerator;

import java.io.FileInputStream;
import java.io.IOException;

public class ViewSmileIt extends JFrame {
    private JPanel contentPane;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private String[][] dataTable;
    private String routeImage;
    private JPanel panelImage;

    /**
    *
    */
    public ViewSmileIt() {
        this.setTitle("SmileIt");
        setSize(850, 400);
        setMinimumSize(new Dimension(400, 400));
        contentPane = new JPanel();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gblContentPane = new GridBagLayout();
        gblContentPane.columnWidths = new int[] { 0, 0 };
        gblContentPane.rowHeights = new int[] { 0, 0 };
        gblContentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gblContentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        contentPane.setLayout(gblContentPane);
        drawTable();
        drawImage();
    }

    private void drawImage() {
        panelImage = new JPanel();
        panelImage.setPreferredSize(new java.awt.Dimension(300, 300));
        panelImage.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(panelImage, gbc);
    }

    private void setImageInPanel() {
        panelImage.removeAll();
        panelImage.setBackground(Color.WHITE);
        try {
            InputStream imageInput = new BufferedInputStream(new FileInputStream(routeImage));
            BufferedImage imageMolecule = ImageIO.read(imageInput);
            imageMolecule = ImageGenerator.resize(imageMolecule, 300, 300);
            JLabel picLabel2 = new JLabel(new ImageIcon(imageMolecule));
            panelImage.add(picLabel2);
            panelImage.revalidate();
            panelImage.repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setVisible(boolean b) {
        revalidate();
        repaint();
        setImageInPanel();
        super.setVisible(b);
    }

    public void setInfo(String[][] data, String[] columnNames) {
        this.dataTable = data;
        routeImage = data[0][1];
        jTable1.setModel(new DefaultTableModel(data, columnNames));
    }

    private void drawTable() {
        JPanel panel = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setSelectionBackground(new java.awt.Color(50, 100, 107));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable1.getTableHeader().setForeground(new Color(15, 15, 15));
        jTable1.setPreferredScrollableViewportSize(new Dimension(190, 300));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedElement();
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                selectedElement();
            }
        });
        jScrollPane1.setBackground(new Color(11, 10, 12));
        jScrollPane1.setForeground(new Color(11, 10, 12));
        jScrollPane1.setViewportView(jTable1);
        panel.add(jScrollPane1);
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        contentPane.add(panel, gbcPanel);
    }

    private void selectedElement() {
        routeImage = dataTable[jTable1.getSelectedRow()][1];
        setImageInPanel();
    }
}
