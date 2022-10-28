package com.main.cadma.interfaces;

import com.main.smileit.interfaces.CompleteEventInterface;
import com.main.smileit.interfaces.MoleculeListInterface;

public interface SmilesGuiInterface {
    public void showGenerate();
    public void uploadList();
    public String getParentPath();
    public MoleculeListInterface getAllMolecules();
    public void addObtainEvent(final CompleteEventInterface completeEvent);

}
