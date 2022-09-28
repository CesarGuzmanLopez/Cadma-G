package com.cadma.main.interfaces;

import javax.swing.JPanel;

import com.cadma.main.domain.models.Molecule;
/**Interface for the MoleculeGraphPainter.
 * @author Cesar Gerardo Guzman Lopez
 */
public interface MoleculeGraphPainterInterface {
    /**
     * Method paint molecule in the panel.
     * @param molecule molecule to paint.
     * @param panel panel to paint.
    */
    void paintMolecule(JPanel panel, Molecule molecule);

}
