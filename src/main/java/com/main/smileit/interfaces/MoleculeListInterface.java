package com.main.smileit.interfaces;

import java.util.List;

import com.main.interfaces.SmilesHInterface;
import com.main.smileit.domain.models.Molecule;
/**Interface for the MoleculeList.
 * @author Cesar Gerardo Guzman Lopez
 */
public interface MoleculeListInterface  {
    /**
     * @return the list of molecules selected or complete.
    */
    List<Molecule> getListMolecule();
    /** Add Smile.
     * @param name name of the molecule.
     * @param smile smile of the molecule.
     * @param message The message to be displayed.
     * @param hasHydrogenImplicit true if the molecule has hydrogen implicit.
     * @return number in the insert | -1 if the molecule already exists.
    */
    int addSmiles(String name, String smile, String message, boolean hasHydrogenImplicit);
    /** Add molecule.
     * @param smile molecule to be added.
     * @return number in the insert
    */
    int addSmiles(SmilesHInterface smile);
    /**
     * @param name name of the molecule.
     * @return number in the insert | -1 if the molecule already exists.
    */
    Molecule getMolecule(String name);
    /**
     * add Clone of molecule.
     * @param molecule molecule to be added.
     * @return number in the insert | -1 if the molecule already exists.
     */
    int addMolecule(Molecule molecule);
    /**
     * Add element only new molecules without data.
     * @param molecule molecule to be added.
     * @param name name of the molecule.
     * @return number in the insert | -1 if the molecule already exists.
     */
    int addMolecule(Molecule molecule, String name);
}
