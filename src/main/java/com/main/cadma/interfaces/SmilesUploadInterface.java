package com.main.cadma.interfaces;

import com.main.shared.domain.cadma.interfaces.EventComplete;

public interface SmilesUploadInterface {
    /**
     * Show the principal view for generate Smiles.
     */
    void showUpload();


    /**
     * Add event to obtain the result.
     * @param completeEvent
     */
    void addUploadEvent(EventComplete completeEvent);


    /**
     * @return the pathPrincipal
     */
    String getPathPrincipal();

    /**
     * @return the namePrincipalMolecule
     */
    String getNamePrincipalMolecule();

    /**
     * @return theFileToUpload
     */
    String getFileToUpload();
}
