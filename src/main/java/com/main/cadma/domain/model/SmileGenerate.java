package com.main.cadma.domain.model;
import com.main.cadma.interfaces.ActionsCadma;
import com.main.cadma.interfaces.SmilesGuiInterface;

public class SmileGenerate implements ActionsCadma  {
    SmilesGuiInterface smilesGui;
    public SmileGenerate(SmilesGuiInterface smilesGui) {
        this.smilesGui = smilesGui;
    }

    @Override
    public void upload() {

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

}
