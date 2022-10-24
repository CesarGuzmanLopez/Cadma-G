package com.main.smileit.infrastructure;

import com.main.smileit.domain.models.Molecule;
import com.main.smileit.domain.models.MoleculesList;
import com.main.smileit.interfaces.MoleculeDataFactoryInterface;
import com.main.smileit.interfaces.SmileVerificationInterface;

public final class FirstSubstituent {
    private FirstSubstituent() {
    }

    /**
     * @param verificationSmile SmileVerificationInterface
     * @param factory           MoleculeDataFactoryInterface
     * @return MoleculesList prepared to use.
     */
    public static MoleculesList getMoleculeListInitializer(final SmileVerificationInterface verificationSmile,
            final MoleculeDataFactoryInterface factory) {
        final MoleculesList list = new MoleculesList(verificationSmile, factory);
        Molecule aNew = new Molecule("Alcohol", "[OH]", "Over Oxygen", true, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Thiol", "[SH]", "Over Sulfur", true, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Amine", "[NH2]", "Over Nitrogen", true, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Chlorine", "[Cl]", "Over Chlorine", false, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("CarboxylicAcid", "[C](=O)O", "Over Carbon", false, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Chloromethane", "[CH2]Cl", "Over Carbon", true, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Aldehyde", "[CH]=O", "Over Carbon", true, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Benzene", "c1ccccc1", "Over Carbon", false, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Diichloromethane", "[CH](Cl)Cl", "Over Carbon", true, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Fluoromethane", "[CH2]F", "Over Carbon", true, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Difluoromethane", "[CH](F)F", "Over Carbon", true, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        aNew = new Molecule("Nitro", "[N](=O)([O])", "Over Nitrogen", false, verificationSmile, factory);
        aNew.selectAtom(0);
        list.addMolecule(aNew);
        return list;
    }
}
