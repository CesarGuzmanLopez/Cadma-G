package com.main.cadma.views.panels;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.main.cadma.interfaces.ActionsCadma;
import com.main.cadma.interfaces.StatusProcess;

/**
 *
 * @author Cesar Gerardo Guzman
 */
@SuppressWarnings("java:S1948")
public class RequiredPanel extends JPanel {
    private static final long serialVersionUID = 2405172041950251807L;
    private static final int WIDTH_SYMBOL = 30;
    private static final int HEIGHT_SYMBOL = 30;
    private BufferedImage tache;
    private BufferedImage paloma;
    private BufferedImage neutro;
    private BufferedImage circulo;
    private StatusProcess status;
    private ActionsCadma actions;
    private JButton buttonGenerate;
    private JButton buttonUpload;
    private JButton buttonView;
    private JButton buttonDelete;
    private RequiredPanel panelNext;

    private JPanel panel1;

    public RequiredPanel(final String title, final ActionsCadma actions) {
        setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        this.actions = actions;
        try {
            tache = ImageIO.read(classLoader.getResourceAsStream("tache.png"));
            paloma = ImageIO.read(classLoader.getResourceAsStream("paloma.png"));
            neutro = ImageIO.read(classLoader.getResourceAsStream("neutro.png"));
            circulo = ImageIO.read(classLoader.getResourceAsStream("circulo.png"));

            tache = resize(tache, WIDTH_SYMBOL, HEIGHT_SYMBOL);
            paloma = resize(paloma, WIDTH_SYMBOL, HEIGHT_SYMBOL);
            neutro = resize(neutro, WIDTH_SYMBOL, HEIGHT_SYMBOL);
            circulo = resize(circulo, WIDTH_SYMBOL, HEIGHT_SYMBOL);
        } catch (IOException e) {
            throw new RuntimeException("Could not load image");
        }
        actions.addObtainEvent(() ->
            setStatus(actions.getStatusProcess())
        );
        actions.addEventUpdateData(() ->
            setStatus(actions.getStatusProcess())
        );
        initialize();

    }

    /**
    * enable buttons generate and upload.
    * */
    public void enableButtons() {
        buttonGenerate.setEnabled(true);
        buttonUpload.setEnabled(true);
        revalidateAll();
    }

    /**
     * disable buttons generate and upload.
     * */
    public void ActionsAfterUploadOrGenerate() {
        buttonGenerate.setEnabled(false);
        buttonUpload.setEnabled(false);
        buttonView.setEnabled(true);
        buttonDelete.setEnabled(true);
        revalidateAll();
    }
    /**
     * @param RequiredPanel panel to next validate
     * add tje next panel to validate.
     * */

    public void setPanelNext(final RequiredPanel panelNext) {
        this.panelNext = panelNext;
    }

    /**
     * set Status of panel.
     * @param status actual of process
     */
    public void setStatus(final StatusProcess status) {
        this.status = status;
        panel1.removeAll();
        if (status == StatusProcess.ERROR) {
            panel1.add(new JLabel(new ImageIcon(tache)));

        } else if (status == StatusProcess.COMPLETE) {
            panel1.add(new JLabel(new ImageIcon(paloma)));
            ActionsAfterUploadOrGenerate();
            if (panelNext != null && panelNext.status != StatusProcess.COMPLETE) {
                panelNext.enableButtons();
            }
        } else if (status == StatusProcess.IN_PROCESS) {
            buttonGenerate.setText("Continue");
            buttonGenerate.setEnabled(true);
            buttonUpload.setEnabled(false);
            buttonView.setEnabled(true);
            buttonDelete.setEnabled(true);
            panel1.add(new JLabel(new ImageIcon(neutro)));
        } else if (status == StatusProcess.EMPTY) {
            panel1.add(new JLabel(new ImageIcon(circulo)));
            buttonGenerate.setEnabled(false);
            buttonUpload.setEnabled(false);
            buttonView.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonGenerate.setText("Generate");
        } else if (status == StatusProcess.NOT_IMPLEMENTED) {
            panel1.add(new JLabel(new ImageIcon(circulo)));
            buttonGenerate.setEnabled(true);
            buttonUpload.setEnabled(false);
            buttonView.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonGenerate.setText("Generate");
        }
        panel1.revalidate();
        panel1.repaint();
    }

    private void initialize() {
        initPanel1();
        initPanel2();
        initPanel3();
        initPanel4();
        initPanel5();
        buttonGenerate.setEnabled(false);
        buttonUpload.setEnabled(false);
        buttonView.setEnabled(false);
        buttonDelete.setEnabled(false);
    }
    private void initPanel1() {
        panel1 = new JPanel();
        add(panel1);
        JLabel picLabel = new JLabel();
        panel1.add(picLabel);
        setStatus(status);
    }

    private void initPanel2() {
        JPanel panel2 = new JPanel();
        buttonGenerate = new JButton("Generate");
        if(!actions.isGenerate()) {
            buttonGenerate.setVisible(false);
            buttonGenerate.setEnabled(false);
        }else{
            add(panel2);
            panel2.add(buttonGenerate);
        }
        buttonGenerate.addActionListener(e -> actions.generate());
    }

    private void initPanel3() {
        JPanel panel3 = new JPanel();
        buttonUpload = new JButton("Upload");
        if(!actions.isUpload()) {
            buttonUpload.setVisible(false);
            buttonUpload.setEnabled(false);
        }else{
            add(panel3);
            panel3.add(buttonUpload);
        }
        buttonUpload.addActionListener(e -> actions.upload());
    }

    private void initPanel4() {
        JPanel panel4 = new JPanel();
        add(panel4);
        buttonView  = new JButton("View");
        panel4.add(buttonView);
        buttonView.addActionListener(e -> actions.view());
    }
    private void initPanel5() {
        JPanel panel5 = new JPanel();
        buttonDelete  = new JButton("delete");
        if(!actions.isDelete()) {
            buttonDelete.setVisible(false);
            buttonDelete.setEnabled(false);
        }else{
            add(panel5);
            panel5.add(buttonDelete);
        }
        buttonDelete.addActionListener(e -> actions.delete());
    }

    private void revalidateAll() {
        panel1.revalidate();
        panel1.repaint();
        buttonView.revalidate();
        buttonView.repaint();
        buttonUpload.revalidate();
        buttonUpload.repaint();
        buttonGenerate.revalidate();
        buttonGenerate.repaint();
    }
    private static BufferedImage resize(final Image img, final int newW, final int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage redImg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = redImg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return redImg;
    }
 }
