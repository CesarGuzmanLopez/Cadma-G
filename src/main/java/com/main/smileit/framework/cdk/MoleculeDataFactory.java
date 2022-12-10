package com.main.smileit.framework.cdk;

import com.main.smileit.domain.models.Molecule;
import com.main.smileit.interfaces.MoleculeDataFactoryInterface;
import com.main.smileit.interfaces.MoleculeDataInterface;

public class MoleculeDataFactory implements MoleculeDataFactoryInterface {
    /**
     * {@inheritDoc}
     */
    @Override
    public MoleculeDataInterface getMoleculeDataOfSmile(final Molecule molecule) {
        return new MoleculeData(molecule);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public MoleculeDataInterface getMoleculeDataOfSmile(final Molecule molecule,
            final MoleculeDataInterface moleculeData) {
        return new MoleculeData(molecule, moleculeData);
    }
}
