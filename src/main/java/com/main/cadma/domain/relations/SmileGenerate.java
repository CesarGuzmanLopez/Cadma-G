package com.main.cadma.domain.relations;

import com.main.cadma.interfaces.ActionsCadma;
import com.main.cadma.interfaces.SmilesGuiInterface;
import com.main.smileit.interfaces.EventInterface;
import com.main.smileit.interfaces.MoleculeListInterface;

public class SmileGenerate implements ActionsCadma {
    private SmilesGuiInterface smilesGui;
    private boolean uploadFile;
    private String parentPath;

    public SmileGenerate(SmilesGuiInterface smilesGui) {// UNCHECK
        this.smilesGui = smilesGui;
    }

    /**
     * @return parent path
     */
    public String getParentPath() {
        if (uploadFile) return parentPath;
        return smilesGui.getParentPath();
    }

    /**
     * @return return all list of smiles.
     */
    public MoleculeListInterface getListSmiles() {
        return smilesGui.getAllMolecules();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upload() {
        smilesGui.uploadList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getName() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate() {
        smilesGui.showGenerate();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void view() {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void addObtainEvent(final EventInterface completeEvent) {
        smilesGui.addObtainEvent(completeEvent);
    }

}
