package com.main.cadma.domain.models.smileit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.main.cadma.domain.models.AttributeAbstract;
import com.main.shared.domain.Molecule;
@JsonSerialize
public class GenerateSmiles extends AttributeAbstract<List<Molecule>> {
    private int numSmiles;
    private String nameOfPrincipal;

    public GenerateSmiles(List<Molecule> value, String nameOfPrincipal) {
        super(value, nameOfPrincipal, true);
        initialize();
    }

    public GenerateSmiles(String nameOfPrincipal) {
        super(new ArrayList<>(), nameOfPrincipal, true);
        initialize();
    }

    public void initialize() {
        numSmiles = 0;
    }

    @Override
    public void lineAnalyze(String line) {
        getValue().add(new Molecule( nameOfPrincipal + "_" + numSmiles,line));
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (Molecule smile : getValue()) {
            sb.append(smile.getSmile() + "\n");
        }
        return sb.toString();
    }

}
