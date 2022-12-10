package com.main.smileit.domain.generator;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.main.smileit.domain.models.Molecule;
import com.main.smileit.framework.cdk.MoleculeDataFactory;
import com.main.smileit.interfaces.MoleculeListInterface;
import com.main.smileit.interfaces.SmilesHInterface;

public final class GeneratorUtils {
    static final int WIDTH = 700;
    static final int HEIGHT = 700;

    private GeneratorUtils() {
    }

    /**
     *
     * @param generateList   List of molecules permutes.
     * @param namePrincipal  Name of principal molecule.
     * @param saveImagesPath Path to save the image.
     */
    public static void saveImages(final MoleculeListInterface generateList, final String namePrincipal,
            final String saveImagesPath) {
        final List<Molecule> list = generateList.getListMolecule();
        File file = new File(saveImagesPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        int i = 0;
        for (Molecule molecule : list) {
            String name = namePrincipal + "_" + i++ + ".png";
            BufferedImage bi = molecule.getImage(WIDTH, HEIGHT, "SMILE: " + molecule.smile());
            try {
                ImageIO.write(bi, "png", new File(saveImagesPath + System.getProperty("file.separator") + name));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /**
     *
     * @param generateList   List of molecules permutes.
     * @param namePrincipal  Name of principal molecule.
     * @param saveImagesPath Path to save the image.
     */
    public static void saveImages(final List<SmilesHInterface> list, final String namePrincipal,
            final String saveImagesPath) { // UNCHECK
        int i = 0;
        File file = new File(saveImagesPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        for (SmilesHInterface smile : list) {
            Molecule molecule = new Molecule(smile, new MoleculeDataFactory());
            String name = namePrincipal + "_" + i++ + ".png";
            BufferedImage bi = molecule.getImage(WIDTH, HEIGHT, "SMILE: " + molecule.smile());
            try {
                ImageIO.write(bi, "png", new File(saveImagesPath + System.getProperty("file.separator") + name));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }finally{

            }
        }
    }

    /**
     * Write Head Description.
     *
     * @param principal        Molecule principal.
     * @param rSubstitutes     num of rSubstitutes.
     * @param substitutes      List of substitutes.
     * @param writeDescription Writer to write.
     * @throw IOException if the writer has a problem.
     */
    public static void writeHeadDescription(final Molecule principal, final int rSubstitutes,
            final MoleculeListInterface substitutes, BufferedWriter writeDescription) throws IOException { // UNCHECK
        if (writeDescription != null) {
            writeDescription.write("Principal: " + principal.getName() + "\nSmile " + principal.smile() + "\n");
            if (substitutes != null) {
                writeDescription.write("Number of substituent: " + substitutes.getListMolecule().size() + "\n");
                writeDescription.write("Simultaneous substitutions allowed: " + rSubstitutes + "\n");
                writeDescription.write("Substitutes: " + "\n");
                for (Molecule molecule : substitutes.getListMolecule()) {
                    writeDescription.write("\t " + molecule.getName() + " -> " + molecule.smile() + "\n");
                    writeDescription.write("\n");
                }
            }

        }
    }
}
