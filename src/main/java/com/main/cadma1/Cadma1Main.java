package com.main.cadma1;

import java.io.File;
import java.util.List;

import com.main.cadma1.domain.DataCadma;
import com.main.cadma1.infrastructure.DataFactoryJson;
import com.main.cadma1.views.Cadma1Preview;
import com.main.cadma1.views.Cadma1view;
import com.main.common.Constant;
import com.main.shared.domain.Molecule;


public class Cadma1Main {
    private String path;
    private DataCadma dataCadma;
    private Cadma1view cadma1view;
    public Cadma1Main() {
        cadma1view = new Cadma1view();
    }
    public void initialize(Molecule molecule, List<Molecule> substitutes, List<Molecule> generate, String path) {
        this.path = path;
        String pathDataJson = path + Constant.FILE_CADMA1_DATA_JSON;
        File file = new File(pathDataJson);
        if (!file.exists()) {
            Cadma1Preview cadma1Preview = new Cadma1Preview();
            cadma1Preview.initialize(molecule, substitutes, generate, path);
            cadma1Preview.addEventSelectedMolecules((List<Molecule> selectedMolecules, Molecule moleculePrincipal) -> {
                dataCadma = DataFactoryJson.GenerateData(pathDataJson, moleculePrincipal, selectedMolecules);
                cadma1view.initialize(dataCadma, path);
            });
        } else {
            dataCadma = DataFactoryJson.ReadData(pathDataJson);
            cadma1view.initialize(dataCadma, path);
        }
    }


}
