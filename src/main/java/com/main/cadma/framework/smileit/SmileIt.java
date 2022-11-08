package com.main.cadma.framework.smileit;

import com.main.cadma.framework.smileit.views.SmileListUpload;
import com.main.cadma.interfaces.SmilesGuiInterface;
import com.main.smileit.views.SmileViewGenerator;
import com.main.smileit.domain.models.MoleculesList;
import com.main.smileit.framework.cdk.MoleculeDataFactory;
import com.main.smileit.framework.cdk.MoleculeGraphPainter;
import com.main.smileit.framework.cdk.VerifiedSmile;
import com.main.smileit.infrastructure.FirstSubstituent;
import com.main.smileit.interfaces.EventInterface;
import com.main.smileit.interfaces.MoleculeListInterface;

public class SmileIt implements SmilesGuiInterface {

    private final SmileViewGenerator principalView;

    private final SmileListUpload upload;

    public SmileIt() {
        final MoleculeDataFactory moleculeFactory = new MoleculeDataFactory();
        final VerifiedSmile verifierSmile = new VerifiedSmile();
        final MoleculeGraphPainter moleculeGraphPainter = new MoleculeGraphPainter();
        final MoleculesList smiles = FirstSubstituent.getMoleculeListInitializer(verifierSmile, moleculeFactory);
        principalView = new SmileViewGenerator(smiles, verifierSmile, moleculeGraphPainter, moleculeFactory);
        principalView.initialize();
        principalView.dispose();
        upload = new SmileListUpload();
        upload.dispose();
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGenerate() {
        principalView.repaint();
        principalView.pack();
        principalView.setLocationByPlatform(true);
        principalView.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadList() {
        upload.setVisible(true);
        upload.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParentPath() {
        if (principalView != null) return principalView.getParentPath();
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoleculeListInterface getAllMolecules() {
        return principalView.getAllMoleculesGenerated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObtainEvent(final EventInterface completeEvent) {
        if (principalView != null) {
            principalView.addGenerateEvent(completeEvent);
        }
        if (upload != null) {
            upload.addEventUpload(completeEvent);
        }
    }
}
