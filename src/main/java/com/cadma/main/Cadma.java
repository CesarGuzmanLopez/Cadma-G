package com.cadma.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.cadma.main.domain.models.MoleculesList;
import com.cadma.main.framework.cdk.MoleculeDataFactory;
import com.cadma.main.framework.cdk.MoleculeGraphPainter;
import com.cadma.main.framework.cdk.VerifiedSmile;
import com.cadma.main.infrastructure.FirstSubstituent;
import com.cadma.main.views.SmileViewGenerator;
/**
 * This class is the main class of the application. It is the entry point of the
 * application.
 *
 * @author Cesar G. Guzman Lopez
 * @version 1.0
 * @since 1.0
 */
public final class Cadma { // NOSONAR
    public static final MoleculeDataFactory MOLECULE_FACTORY = new MoleculeDataFactory();
    public static final VerifiedSmile VERIFIER_SMILE = new VerifiedSmile();

    /**
     * This method is the main method of the application. It is the entry point of
     * the application.
     *
     * @since 1.0
     */
    private Cadma() {

    }

    /**
     * This method is the main method of the application. It is the entry point of
     * the application.
     *
     * @since 1.0
     * @param args the arguments of the application.
     *
     */

    public static void main(final String[] args) {
        if (args.length == 0) {
            graphic();
        }
    }

    /**
     * this method execute the graphic interface.
     */
    private static void graphic() {
        try {
            themeSelected();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        final MoleculeGraphPainter moleculeGraphPainter = new MoleculeGraphPainter();

        final MoleculesList smiles = FirstSubstituent.getMoleculeListInitializer(VERIFIER_SMILE, MOLECULE_FACTORY);
        final SmileViewGenerator principalView = new SmileViewGenerator(smiles, VERIFIER_SMILE, moleculeGraphPainter,
                MOLECULE_FACTORY);
        principalView.initialize();
    }
    /**
     * This method is used to select the theme of the application.
     *
     * @since 1.0
     * @throws ClassNotFoundException          if the class is not found.
     * @throws InstantiationException          if the class is not instantiated.
     * @throws IllegalAccessException          if the class is not accessed.
     * @throws UnsupportedLookAndFeelException if the theme is not supported.
     */
    private static void themeSelected() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        String themeDefault = "";
        final String firsTheme = UIManager.getInstalledLookAndFeels()[0].getClassName();
        String themePoint = "";
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            String className = info.getClassName();
            if ("GTKLookAndFeel".equals(info.getName())) {
                themePoint = className;
                UIManager.setLookAndFeel(themePoint);
                break;
            }
            if ("Nimbus".equals(info.getName())) {
                themeDefault = className;
            }
            if ("Windows".equals(info.getName())) {
                themePoint = className;
            }
            if ("Metal".equals(info.getName())) {
                themePoint = className;
            }
            UIManager.setLookAndFeel(themePoint);
        }
        if (!themeDefault.equals("") && themePoint.equals(firsTheme)) {
            UIManager.setLookAndFeel(themeDefault);
        }

    }
}
