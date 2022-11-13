package com.main.cadma.interfaces;

import java.util.List;

import com.main.smileit.interfaces.SmilesHInterface;

public interface SmilesGuiInterface {
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
    List<SmilesHInterface> getMoleculesList();

    /**
     * @return the List of substitutes selected.
     */
    List<SmilesHInterface> getSubstitutes();

    /**
     * @return the moleculePrincipal
     */
    SmilesHInterface getMoleculePrincipal();

    /**
     * @return the pathPrincipal
     */
    String getPathPrincipal();

}
