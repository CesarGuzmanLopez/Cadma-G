package com.main.cadma.domain;

import com.main.cadma.domain.model.Cadma1Generate;
import com.main.cadma.domain.model.Cadma2Generate;
import com.main.cadma.domain.model.SmileGenerate;
import com.main.cadma.interfaces.SmilesGuiInterface;

public class CadmaProcess {
    private SmileGenerate smileGenerate;
    private String path;
    private Cadma1Generate cadma1Generate;
    private Cadma2Generate cadma2Generate;

    public CadmaProcess(String path, SmilesGuiInterface smilesGuiInterface) {
        this.smileGenerate  = new SmileGenerate(smilesGuiInterface);
        this.cadma1Generate = new Cadma1Generate();
        this.cadma2Generate = new Cadma2Generate();
        initialize();

    }
    private void initialize() {
        this.smileGenerate.addObtainEvent(this::generateEvent);

    }
    public void setPath(String path) {
        this.path = path;
    }
    private void generateEvent(){
        setPath(smileGenerate.getParentPath());
    }
    public SmileGenerate getSmileGenerate() {
        return smileGenerate;
    }
    public Cadma1Generate getCadma1Generate() {
        return cadma1Generate;
    }
    public Cadma2Generate getCadma2Generate() {
        return cadma2Generate;
    }
}
