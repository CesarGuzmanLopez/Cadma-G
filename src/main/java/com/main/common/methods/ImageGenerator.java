package com.main.common.methods;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public final class ImageGenerator {
    private ImageGenerator() {
        throw new IllegalStateException("Utility class");
    }
    public static BufferedImage resize(final Image img, final int newW, final int newH) {
        Image imgTmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage redImg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = redImg.createGraphics();
        g2d.drawImage(imgTmp, 0, 0, null);
        g2d.dispose();
        return redImg;
    }
}
