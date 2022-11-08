package com.main.cadma.domain;

import com.main.cadma.domain.relations.Cadma1Generate;
import com.main.cadma.domain.relations.Cadma2Generate;
import com.main.cadma.domain.relations.SmileGenerate;
import com.main.cadma.interfaces.SmilesGuiInterface;

public class CadmaProcess {
    private SmileGenerate smileGenerate;
    private String parentPath;
    private Cadma1Generate cadma1Generate;
    private Cadma2Generate cadma2Generate;

    /**
     * @param smilesGuiInterface controller of smilit
     */
    public CadmaProcess(SmilesGuiInterface smilesGuiInterface) {// UNCHECK
        this.smileGenerate = new SmileGenerate(smilesGuiInterface);
        this.cadma1Generate = new Cadma1Generate();
        this.cadma2Generate = new Cadma2Generate();
        initialize();


    }
    private void initialize() {
        this.smileGenerate.addObtainEvent(this::generateEvent);

    }

    /**
     * @param path path of file
     */
    public void setParentPath(final String path) {
        this.parentPath = path;
    }

    private void generateEvent() {
        setParentPath(smileGenerate.getParentPath());
    }

    /**
     * @return controller of smiles
     */
    public SmileGenerate getSmileGenerate() {
        return smileGenerate;
    }

    /**
     * @return controller of cadma1
     */
    public Cadma1Generate getCadma1Generate() {
        return cadma1Generate;
    }

    /**
     * @return controller of cadma
     */
    public Cadma2Generate getCadma2Generate() {
        return cadma2Generate;
    }

    /**
     * @return path of file
     */
    public String getParentPath() {
        return this.parentPath;
    }
}
