package com.main.smileit.domain.generator;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.main.smileit.domain.models.Molecule;
import com.main.smileit.domain.models.MoleculesList;
import com.main.smileit.interfaces.MoleculeDataFactoryInterface;
import com.main.smileit.interfaces.MoleculeListInterface;
import com.main.smileit.interfaces.SmileVerificationInterface;
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
            String filePath = saveImagesPath + System.getProperty("file.separator") + name;
            try {

                ImageIO.write(bi, "png", new File(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            molecule.setPathImage(filePath);
        }
    }

    public static void saveImages(final String namePrincipal, final String saveImagesPath, List<String> listOfSmiles,
            final SmileVerificationInterface verificationSmile, final MoleculeDataFactoryInterface factory) {
                MoleculeListInterface generateList = new MoleculesList(verificationSmile, factory);
                int i = 0;
                for(String smile : listOfSmiles){
                    generateList.addSmiles(namePrincipal + i++, smile, "", false);
                }
                saveImages(generateList, namePrincipal, saveImagesPath);
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
    public static void writeHeadDescription(final SmilesHInterface principal, final int rSubstitutes,
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
