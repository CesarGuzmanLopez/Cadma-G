package com.main.cadma.domain;


import com.main.cadma.domain.relations.Cadma1Generate;
import com.main.cadma.domain.relations.Cadma2Generate;
import com.main.cadma.domain.relations.SmileGenerate;
import com.main.cadma.interfaces.EventComplete;
import com.main.cadma.interfaces.EventUpdateData;
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
        cadma1Generate = new Cadma1Generate(smileGenerate);
        cadma2Generate = new Cadma2Generate();
        smileGenerate.addObtainEvent(
                new EventComplete() {
                    @Override
                    public void execute() {
                        cadma1Generate.enableGenerate();
                    }
                }
        );
    }
    public void importCadmaProcess(final String path) {
        smileGenerate.importCadmaProcess(path);
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
     * @return Path of folder
     * */
    public String getPath() {
        return smileGenerate.getParentPath();
    }

    /**
     * set the event to update data
     * */
    public void addEventUpdateData(final EventUpdateData event) {
        smileGenerate.addEventUpdateData(event);
        cadma1Generate.addEventUpdateData(event);
        cadma2Generate.addEventUpdateData(event);
    }
   /**
    * @return name of process
    */
    public String getNameOfProcess() {
        return smileGenerate.getPrincipalName();
    }
    /**
     * @return number of substituents
     */
    public int getNumberOfSubstituents() {
        return smileGenerate.getNumberSubstitutes();

    }
    /**
     * @return number of molecules generated
     */
    public int getNumberOfMoleculesGenerated() {
        return smileGenerate.getNumberGenerateSmiles();
    }
}

