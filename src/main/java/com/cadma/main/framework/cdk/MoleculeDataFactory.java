package com.cadma.main.framework.cdk;

import com.cadma.main.domain.models.Molecule;
import com.cadma.main.interfaces.MoleculeDataFactoryInterface;
import com.cadma.main.interfaces.MoleculeDataInterface;

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
