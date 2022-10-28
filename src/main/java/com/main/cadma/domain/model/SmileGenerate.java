package com.main.cadma.domain.model;



import com.main.cadma.interfaces.ActionsCadma;
import com.main.cadma.interfaces.SmilesGuiInterface;
import com.main.smileit.interfaces.CompleteEventInterface;
import com.main.smileit.interfaces.MoleculeListInterface;

public class SmileGenerate implements ActionsCadma {
    SmilesGuiInterface smilesGui;
    private boolean uploadFile;
    private String parentPath;

    public SmileGenerate(SmilesGuiInterface smilesGui) {
        this.smilesGui = smilesGui;

        uploadFile = false;
    }

    public String getParentPath() {
        if (uploadFile)
            return parentPath;
        return smilesGui.getParentPath();
    }
    public MoleculeListInterface getListSmiles() {
        return smilesGui.getAllMolecules();
    }
    @Override
    public void upload() {
        smilesGui.uploadList();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void getName() {
        // TODO Auto-generated method stub

    }

    @Override
    public void generate() {
        smilesGui.showGenerate();

    }

    @Override
    public void view() {
        // TODO Auto-generated method stub
    }



    @Override
    public void addObtainEvent(CompleteEventInterface completeEvent) {
        smilesGui.addObtainEvent(completeEvent);
    }

}
