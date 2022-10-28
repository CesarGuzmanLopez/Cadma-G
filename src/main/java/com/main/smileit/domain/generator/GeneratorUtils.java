package com.main.smileit.domain.generator;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.main.smileit.domain.models.Molecule;
import com.main.smileit.interfaces.MoleculeListInterface;

final public class GeneratorUtils {
    /**
     *
     * @param generateList List of molecules permutes.
     */
    static final int WIDTH = 700;
    static final int HEIGHT = 700;

    /**
     *
     * @param generateList List of molecules permutes.
     */
    public final static void saveImages(final MoleculeListInterface generateList, String namePrincipal,
            String saveImagesPath) { // UNCHECK
        final List<Molecule> list = generateList.getListMolecule();
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
     * Write Head Description.
     */
    public static void writeHeadDescription(Molecule principal, int rSubstitutes, MoleculeListInterface substitutes,
            BufferedWriter writeDescription) throws IOException {
        if (writeDescription != null) {
            writeDescription.write("Principal: " + principal.getName() + "\nSmile " + principal.smile() + "\n");
            if (substitutes != null) {
                writeDescription.write("Number of substituents: " + substitutes.getListMolecule().size() + "\n");

                writeDescription.write("Simultaneous substitutions allowed: " + rSubstitutes + "\n");

                writeDescription.write("Substitutes: " + "\n");

                for (Molecule molecule : substitutes.getListMolecule()) {
                    writeDescription.write("\t " + molecule.getName());
                    writeDescription.write("\n");
                }
            }

        }
    }
}
