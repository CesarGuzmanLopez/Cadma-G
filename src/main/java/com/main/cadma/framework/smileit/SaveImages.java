package com.main.cadma.framework.smileit;

import java.util.ArrayList;
import java.util.List;

import com.main.cadma.interfaces.SaveImagesInterface;
import com.main.shared.domain.Molecule;
import com.main.smileit.framework.cdk.MoleculeDataFactory;
import com.main.smileit.framework.cdk.VerifiedSmile;

public class SaveImages  implements SaveImagesInterface{


    @Override
    public void saveImage(String principalName, String saveImagesPath, List<Molecule> listOfSmiles) {
        final MoleculeDataFactory moleculeFactory = new MoleculeDataFactory();
        final VerifiedSmile verifierSmile = new VerifiedSmile();
        List<String> smiles = new ArrayList<>();
        for (Molecule molecule : listOfSmiles) {
            smiles.add(molecule.getSmile());
        }
        com.main.smileit.domain.generator.GeneratorUtils.saveImages(principalName, saveImagesPath, smiles, verifierSmile, moleculeFactory);
    }
}

