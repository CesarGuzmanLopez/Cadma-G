package com.main.cadma.interfaces;

import java.util.List;

import com.main.shared.domain.Molecule;
import com.main.shared.domain.cadma.interfaces.EventComplete;
import com.main.shared.domain.cadma.interfaces.EventUpdateData;

public interface Cadma1Interface {
    /**
     *
     */
    void showGenerate(Molecule molecule, List<Molecule> substituents, List<Molecule> Generate, String path);

    /**
     * Add event to obtain the result.
     * @param completeEvent
     */
    void addGenerateEvent(EventComplete completeEvent);

    /**
     * update event
     */
    void addEventUpdateData(EventUpdateData eventUpdateData);
}
