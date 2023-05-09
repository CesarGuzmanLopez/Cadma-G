package com.main.smileit.domain.models;

import java.util.List;

import com.main.interfaces.SmileVerificationInterface;
import com.main.interfaces.SmilesHInterface;
import com.main.smileit.interfaces.AtomInterface;
import com.main.smileit.interfaces.MoleculeComparableInterface;
import com.main.smileit.interfaces.MoleculeDataFactoryInterface;
import com.main.smileit.interfaces.MoleculeDataInterface;

import java.awt.image.BufferedImage;

public class Molecule extends Smiles implements MoleculeComparableInterface {
    private MoleculeDataFactoryInterface moleculeDataFactory;
    private MoleculeDataInterface moleculeDataOfSmile;
    private String pathImage = "";
    public Molecule(final SmilesHInterface smile, final MoleculeDataFactoryInterface moleculeFactory) {
        super(smile);
        this.moleculeDataFactory = moleculeFactory;
        moleculeDataOfSmile = moleculeDataFactory.getMoleculeDataOfSmile(this);
        resetSmile();
        pathImage = "";
    }
    public static SmilesHInterface create(Molecule a){
        return a;
    }
    public Molecule(final String name, final String smiles, final String message, final boolean hydrogenImplicit,
            final SmileVerificationInterface smileVerification,
            final MoleculeDataFactoryInterface moleculeDataOfSmileFactory) {
        super(name, smiles, message, hydrogenImplicit, smileVerification);
        this.moleculeDataFactory = moleculeDataOfSmileFactory;
        moleculeDataOfSmile = this.moleculeDataFactory.getMoleculeDataOfSmile(this);
        resetSmile();
    }
    public void setPathImage(String path){
        this.pathImage = path;
    }
    public String getPathImage(){
        return pathImage;
    }
    public Molecule(final Molecule molecule) {
        super(molecule);
        this.moleculeDataFactory = molecule.moleculeDataFactory;
        moleculeDataOfSmile = moleculeDataFactory.getMoleculeDataOfSmile(this);
        resetSmile();
    }

    public Molecule(final Molecule molecule, final boolean cloneData) {
        super(molecule);
        this.moleculeDataFactory = molecule.moleculeDataFactory;
        if (cloneData) {
            moleculeDataOfSmile = moleculeDataFactory.getMoleculeDataOfSmile(this, molecule.getMoleculeData());
        } else {
            moleculeDataOfSmile = moleculeDataFactory.getMoleculeDataOfSmile(this);
        }
        resetSmile();
    }

    public Molecule(final Molecule molecule, final String newName, final boolean cloneData) {
        super(molecule);
        this.moleculeDataFactory = molecule.moleculeDataFactory;
        if (cloneData) {
            moleculeDataOfSmile = moleculeDataFactory.getMoleculeDataOfSmile(this, molecule.getMoleculeData());
        } else {
            moleculeDataOfSmile = moleculeDataFactory.getMoleculeDataOfSmile(this);
        }
        resetSmile();
        setName(newName);
    }

    /**
     * @param index
     * @return Atom by index id
     */
    public AtomInterface getAtom(final int index) {
        return moleculeDataOfSmile.getAtom(index);
    }

    /**
     * Get number of Atoms in the molecule.
     *
     * @return The number of atoms in the molecule
     */
    public int atomCount() {
        return moleculeDataOfSmile.getNumberAtoms();
    }

    /**
     * Get the molecule data.
     *
     * @return The molecule data
     */
    public MoleculeDataInterface getMoleculeData() {
        if (moleculeDataOfSmile == null) {
            throw new NullPointerException("MoleculeDataOfSmile is null");
        }
        return moleculeDataOfSmile;
    }

    /**
     * compare two molecules.
     *
     * @return Integer The molecule data
     */
    @Override
    public int compareTo(final Molecule molecule) {
        return getMoleculeData().compareTo(molecule);
    }

    /**
     * toString.
     *
     * @return String The molecule isomeric smiles.
     */

    @Override
    public String toString() {
        return getName() + " Smile:" + smile();
    }

    /**
     * Return a fusion molecule Principal wit substituent.
     *
     * @param principal         The molecule to fusion
     * @param substituent       The substituent to fusion
     * @param numAtomPrincipal  substitution of the principal molecule
     * @param numAtomSubstitute substitution of the substituent molecule
     * @return Molecule The fusion molecule
     */
    public static Molecule fusionMolecule(final Molecule principal, final Molecule substituent,
            final Integer numAtomPrincipal, final Integer numAtomSubstitute) {
        return fusionMolecule(principal, substituent, numAtomPrincipal, numAtomSubstitute, 1);
    }

    /**
     * Return a fusion molecule Principal wit substituent.
     *
     * @param principal         The molecule to fusion
     * @param substituent       The substituent to fusion
     * @param numAtomPrincipal  substitution of the principal molecule
     * @param numAtomSubstitute substitution of the substituent molecule
     * @param numBond           Number of bonds between the principal and
     *                          substituent
     * @return Molecule The fusion molecule
     */
    public static Molecule fusionMolecule(final Molecule principal, final Molecule substituent,
            final Integer numAtomPrincipal, final Integer numAtomSubstitute, final Integer numBond) {
        verifyToSubstitute(principal, substituent, numAtomPrincipal, numAtomSubstitute);
        Molecule substituentClone = new Molecule(substituent, true);
        Molecule fusion = new Molecule(principal, principal.getName() + "<" + numAtomPrincipal + "> |" + numBond + "| "
                + substituentClone.getName() + "<" + numAtomSubstitute + ">", true);

        fusion.getMoleculeData().addMoleculeData(substituentClone, numAtomPrincipal, numAtomSubstitute, numBond);

        return fusion;
    }

    /**
     * select a atom id.
     *
     * @param index the atom id.
     */
    public void selectAtom(final int index) {
        if (index < 0 || index >= atomCount()) {
            throw new IllegalArgumentException("Atom index out of range");
        }
        moleculeDataOfSmile.selectOrderAtom(getAtom(index));
    }

    /**
     * Reset Smile input for isomericSmile.
     */
    public void resetSmile() {
        setStrSmiles(getMoleculeData().absoluteSmile());
    }

    /**
     * Verify if the molecule can be substituted.
     *
     * @param principal         The molecule to fusion
     * @param substituent       The substituent to fusion
     * @param numAtomPrincipal  substitution of the principal molecule
     * @param numAtomSubstitute substitution of the substituent molecule
     */
    private static void verifyToSubstitute(final Molecule principal, final Molecule substituent,
            final Integer numAtomPrincipal, final Integer numAtomSubstitute) {
        if (principal == null || substituent == null) {
            throw new NullPointerException("molecule principal or substituent is null");
        }
        if (principal.atomCount() > 1 && principal.getMoleculeData().getListAtomsSelected().isEmpty()
                || substituent.atomCount() > 1 && substituent.getMoleculeData().getListAtomsSelected().isEmpty()) {
            throw new NullPointerException("molecule has no selected atoms and are more than 1");
        }
        verifyEntryAtomSelected(principal, numAtomPrincipal);
        verifyEntryAtomSelected(substituent, numAtomSubstitute);

    }

    private static void verifyEntryAtomSelected(final Molecule principal, final Integer numAtomPrincipal) {
        if (numAtomPrincipal == null && principal.atomCount() > 1) {
            throw new NullPointerException("toSubstitute is null or 0 and principal has more than 1 atom");
        }
        if (numAtomPrincipal != null && principal.atomCount() > 1
                && !principal.getMoleculeData().getListAtomsSelected().contains(principal.getAtom(numAtomPrincipal))) {
            throw new NullPointerException("The atom no selected");
        }
        if (numAtomPrincipal != null && principal.getMoleculeData().getListAtomsSelected().isEmpty()) {
            throw new NullPointerException("molecule has no selected atoms");
        }
    }

    /**
     * @return if the molecule is equals to the molecule.
     */
    @Override
    public int hashCode() {
        return getMoleculeData().hashCode() + getName().hashCode();
    }

    /**
     * @param mol the molecule to compare.
     * @return if the molecule is equals to the molecule.
     */
    @Override
    public boolean equals(final Object mol) { // NOSONAR
        if (mol == null) {
            return false;
        }
        Molecule molecule = (Molecule) mol;
        return molecule.smile().equals(smile()) && molecule.getName().equals(getName());
    }

    /**
     * @return if isOnlySubstitutedHydrogens.
     */
    public boolean isOnlySubstitutedHydrogens() {
        return moleculeDataOfSmile.isOnlySubstitutedHydrogens();
    }

    /**
     * @return MoleculeDataFactoryInterface
     */
    protected MoleculeDataFactoryInterface getMoleculeDataFactory() {
        return moleculeDataFactory;
    }

    /**
     * @param index the atom id.
     * @return is selected
     */
    public boolean isSelected(final int index) {
        return moleculeDataOfSmile.isSelected(index);
    }

    /**
     * @param id of substitute
     * @return MoleculeSubsitute
     */
    public Molecule getSubstitute(final int id) {
        List<MoleculesAdded> adds = moleculeDataOfSmile.getListMoleculesAdded();
        for (MoleculesAdded moleculesAdded : adds) {
            if (moleculesAdded.getSelectedPrincipal() == id) {
                return moleculesAdded.getMolecule();
            }
        }
        throw new NullPointerException("this molecule no selected");
    }

    /**
     * Generate Molecule of MoleculeData.
     *
     * @param width  of image.
     * @param height of image.
     * @param title
     * @return BufferedImage.
     *
     *
     */
    public BufferedImage getImage(final int width, final int height, final String title) {
        return moleculeDataOfSmile.getImage(width, height, title);
    }
}
