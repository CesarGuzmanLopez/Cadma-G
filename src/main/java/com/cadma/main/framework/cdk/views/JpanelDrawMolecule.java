package com.cadma.main.framework.cdk.views;

import java.awt.Graphics;

import com.cadma.main.domain.models.Molecule;

public class JpanelDrawMolecule extends JpanelDrawMoleculeAbstract {

    public JpanelDrawMolecule(final Molecule molecule) {
        super(molecule);
    }

    @Override
    void paintHerder(final Graphics a) {
      // document why this method is empty
      //only DrawMolecule
    }

}
