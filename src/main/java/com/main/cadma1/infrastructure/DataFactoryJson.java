package com.main.cadma1.infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.cadma1.domain.DataCadma;
import com.main.shared.domain.Molecule;

public class DataFactoryJson {
    private DataFactoryJson() {
        throw new IllegalStateException("Utility class");
    }
    public static DataCadma GenerateData(String pathFile, Molecule moleculePrincipal, List<Molecule> selectedMolecules) {
        ObjectMapper mapper = new ObjectMapper();
        DataCadma dataCadma = new DataCadma(moleculePrincipal, selectedMolecules);
        try {
            File file = new File(pathFile);
            if( file.createNewFile() ){
                mapper.writeValue(file, dataCadma);
            } else {
                throw new RuntimeException("Error al crear el archivo");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataCadma;
    }
    public static DataCadma ReadData(String pathFile) {
        ObjectMapper mapper = new ObjectMapper();
        DataCadma dataCadma = null;
        try {
            File file = new File(pathFile);
            dataCadma = mapper.readValue(file, DataCadma.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataCadma;
    }
    public static void updateData(String pathFile, DataCadma dataCadma) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(pathFile);
                mapper.writeValue(file, dataCadma);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
