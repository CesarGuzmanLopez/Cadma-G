package com.main.smileit.interfaces;

import java.io.BufferedWriter;


public interface EventDescriptionInterface extends EventInterface {
    /**
     * Interface EventDescritionInterface.
     * @param writeDescription
     * @param generateList
     */
    void setBuferedWriter(BufferedWriter writeDescription, MoleculeListInterface generateList);
}
