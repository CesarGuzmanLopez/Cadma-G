package com.main.cadma.interfaces;

import java.util.List;

import com.main.shared.domain.Molecule;
import com.main.shared.domain.cadma.interfaces.EventComplete;

public interface Cadma1Interface {
    /**
     *
     */
    void showGenerate(Molecule molecule, List<Molecule> substituents, String path, List<Molecule> Generate);

    /**
     * Add event to obtain the result.
     * @param completeEvent
     */
    void addGenerateEvent(EventComplete completeEvent);
}
