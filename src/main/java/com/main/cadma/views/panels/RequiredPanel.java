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
        initialize();
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
        } else if (status == StatusProcess.FINISH) {
            panel1.add(new JLabel(new ImageIcon(paloma)));
        } else if (status == StatusProcess.INCOMPLETE) {
            panel1.add(new JLabel(new ImageIcon(neutro)));
        } else if (status == StatusProcess.EMPTY) {
            panel1.add(new JLabel(new ImageIcon(circulo)));
        }
        panel1.revalidate();
        panel1.repaint();
    }

    private void initialize() {
        initPanel1();
        initPanel2();
        initPanel3();
        initPanel4();

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
        add(panel2);
        JButton button = new JButton("Generate");
        panel2.add(button);
        button.addActionListener(e -> actions.generate());
    }

    private void initPanel3() {
        JPanel panel3 = new JPanel();
        add(panel3);
        JButton button = new JButton("Upload");
        panel3.add(button);

        button.addActionListener(e -> uploadFile());
    }

    private void uploadFile() {
        actions.upload();
    }

    private void initPanel4() {
        JPanel panel4 = new JPanel();
        add(panel4);
        JButton button = new JButton("View");
        panel4.add(button);
        button.addActionListener(e -> actions.view());
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
