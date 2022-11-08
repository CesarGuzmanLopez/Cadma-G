package com.main.cadma.interfaces;

import com.main.smileit.interfaces.EventInterface;
import com.main.smileit.interfaces.MoleculeListInterface;

public interface SmilesGuiInterface {
    /**
     * Show the principal view for generate Smiles.
     */
    void showGenerate();

    /**
     * show of view for upload a file.
     */
    void uploadList();

    /**
     * Get the path of parent.
     * @return path of parent.
     */
    String getParentPath();

    /**src/main/java/com/main/cadma/views/panels/RequiredPanel.java:
     * Get all molecules generated.
     * @return all molecules.
     */
    MoleculeListInterface getAllMolecules();

    /**
     * Add event to obtain the result.
     * @param completeEvent
     */
    void addObtainEvent(EventInterface completeEvent);

}
