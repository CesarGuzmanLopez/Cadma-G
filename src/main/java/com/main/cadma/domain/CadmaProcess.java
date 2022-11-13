package com.main.cadma.domain;

import com.main.cadma.domain.models.attributes.smileit.SmilePrincipal;
import com.main.cadma.domain.models.attributes.smileit.Substitutes;


import com.main.cadma.domain.relations.Cadma1Generate;
import com.main.cadma.domain.relations.Cadma2Generate;
import com.main.cadma.domain.relations.SmileGenerate;
import com.main.cadma.interfaces.SmilesGuiInterface;
import com.main.cadma.interfaces.SmilesUploadInterface;

final public class CadmaProcess {
    private String parentPath;
    private SmileGenerate smileGenerate;
    private Cadma1Generate cadma1Generate;
    private Cadma2Generate cadma2Generate;

    private SmilePrincipal smilePrincipal;
    private Substitutes substitutes;
    private String pathParenth;


    /**
     * @param smilesGuiGenerator controller of smileIt
     */
    public CadmaProcess(SmilesGuiInterface smilesGuiGenerator, SmilesUploadInterface Smillesupload) {// UNCHECK
        this.smilePrincipal = new SmilePrincipal();
        this.substitutes = new Substitutes();

        this.smileGenerate = new SmileGenerate(smilesGuiGenerator,Smillesupload);



        this.cadma1Generate = new Cadma1Generate();
        this.cadma2Generate = new Cadma2Generate();

    }


    void uploadSmiles(String nameFile,final String setParentPath, String name){
        setParentPathUpload(smileGenerate.getParentPath());

    }
    /**
     * @param path path of file
     */
    public void setParentPathUpload(final String path) {
        this.parentPath = path;
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
