package com.main.cadma.views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewSmileIt extends JFrame {
    private JPanel contentPane;

    private JScrollPane jScrollPane1;
    private JTable jTable1;

    /**
     *
     */
    public ViewSmileIt() {
        this.setTitle("SmileIt");
        this.setSize(500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(contentPane);

        JPanel panelTabla = new JPanel();
        drawTable(panelTabla);
        contentPane.add(panelTabla);

    }

    @Override
    public void setVisible(boolean b) {
        revalidate();
        repaint();
        super.setVisible(b);
    }

    public void setInfo(Object[][] data, Object[] columnNames) {
        jTable1.setModel(new DefaultTableModel(data, columnNames));
    }

    private void drawTable(JPanel panel) {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(50, 100, 107));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable1.getTableHeader().setForeground(new Color(15, 15, 15));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);
        panel.add(jScrollPane1);
    }
}
