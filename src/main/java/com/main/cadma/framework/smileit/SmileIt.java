package com.main.cadma.framework.smileit;

import com.main.cadma.interfaces.SmilesGuiInterface;
import com.main.smileit.views.SmileViewGenerator;
import com.main.smileit.domain.models.MoleculesList;
import com.main.smileit.framework.cdk.MoleculeDataFactory;
import com.main.smileit.framework.cdk.MoleculeGraphPainter;
import com.main.smileit.framework.cdk.VerifiedSmile;
import com.main.smileit.infrastructure.FirstSubstituent;
public class SmileIt  implements SmilesGuiInterface{

    final SmileViewGenerator principalView;
    public SmileIt(){
        final MoleculeDataFactory moleculeFactory = new MoleculeDataFactory();
        final VerifiedSmile verifierSmile = new VerifiedSmile();
        final MoleculeGraphPainter moleculeGraphPainter = new MoleculeGraphPainter();

        final MoleculesList smiles = FirstSubstituent.getMoleculeListInitializer(verifierSmile, moleculeFactory);
        principalView = new SmileViewGenerator(smiles, verifierSmile, moleculeGraphPainter,
        moleculeFactory);
        principalView.initialize();
        principalView.setVisible(false);


    }
    @Override
    public void showGenerate() {
        principalView.repaint();
        principalView.pack();
        principalView.setLocationByPlatform( true );
    
        principalView.setVisible(true);
        
    }
    @Override
    public void insertList(String path) {

    }

}
