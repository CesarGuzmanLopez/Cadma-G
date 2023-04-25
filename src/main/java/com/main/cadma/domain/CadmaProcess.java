package com.main.cadma.domain;


import com.main.cadma.domain.relations.Cadma1Generate;
import com.main.cadma.domain.relations.Cadma2Generate;
import com.main.cadma.domain.relations.SmileGenerate;
import com.main.cadma.interfaces.MoleculesGuiInterface;
import com.main.cadma.interfaces.SaveImagesInterface;
import com.main.cadma.interfaces.SmilesUploadInterface;
import com.main.cadma.views.ViewSmileIt;

public final class CadmaProcess {
    private SmileGenerate smileGenerate;
    private Cadma1Generate cadma1Generate;
    private Cadma2Generate cadma2Generate;

    /**
     * @param smilesGuiGenerator controller of smileIt
     * @param viewSmileIt
     */
    public CadmaProcess(final MoleculesGuiInterface smilesGuiGenerator,final SmilesUploadInterface smilesUpload, ViewSmileIt viewSmileIt,SaveImagesInterface saveImage) {

        smileGenerate = new SmileGenerate(smilesGuiGenerator, smilesUpload, viewSmileIt, saveImage );

        cadma1Generate = new Cadma1Generate();

        cadma2Generate = new Cadma2Generate();

    }
    public void importCadmaProcess(final String path) {
        smileGenerate.importCadmaProcess(path);
        cadma1Generate.importCadmaProcess(path);
        cadma2Generate.importCadmaProcess(path);
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
}
