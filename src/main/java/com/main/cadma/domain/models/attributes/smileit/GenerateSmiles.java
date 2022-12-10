package com.main.cadma.domain.models.attributes.smileit;

import java.util.ArrayList;
import java.util.List;
import com.main.cadma.domain.models.AttributeAbstract;
import com.main.smileit.interfaces.SmilesHInterface;

public class GenerateSmiles extends AttributeAbstract<List<SmilesHInterface>> {
    private int numSmiles;
    private String nameOfPrincipal;

    public GenerateSmiles(List<SmilesHInterface> value, String nameOfPrincipal) {
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
        getValue().add(smileFactory.create(line, nameOfPrincipal + "_" + numSmiles, line, true));
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (SmilesHInterface smile : getValue()) {
            sb.append(smile.smile() + "\n");
        }
        return sb.toString();
    }

}
