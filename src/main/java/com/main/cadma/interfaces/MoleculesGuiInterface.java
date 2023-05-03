package com.main.cadma.interfaces;

import java.util.List;

import com.main.shared.domain.Molecule;
import com.main.shared.domain.cadma.interfaces.EventComplete;

public interface MoleculesGuiInterface {
    /**
     * Show the principal view for generate Smiles.
     */
    void showGenerate();


    /**
     * Add event to obtain the result.
     * @param completeEvent
     */
    void addGenerateEvent(EventComplete completeEvent);


    /**
     * @return the List of substitutes selected.
     */
    List<Molecule> getMoleculesList();

    /**
     * @return the List of substitutes selected.
     */
    List<Molecule> getSubstitutes();

    /**
     * @return the moleculePrincipal
     */
    Molecule getMoleculePrincipal();

    /**
     * @return the pathPrincipal
     */
    String getPathPrincipal();

}
