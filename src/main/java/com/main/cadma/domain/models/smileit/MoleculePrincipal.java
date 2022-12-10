package com.main.cadma.domain.models.smileit;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.main.cadma.domain.models.AttributeAbstract;
import com.main.shared.domain.Molecule;
@JsonSerialize
public class MoleculePrincipal extends AttributeAbstract<Molecule> {
    private static final String SLUG_SMILE = "SMILE";
    private static final String SLUG_NAME = "Principal";

    private String name;

    public MoleculePrincipal(Molecule value) {
        super(value, SLUG_SMILE, true);
        initialize();
    }

    public MoleculePrincipal() {
        super(null, SLUG_NAME, false);
        initialize();
    }

    private void initialize() {
        name = "";
    }

    @Override
    public void lineAnalyze(String line) {
        if (line.contains(SLUG_SMILE)) {
            String smile = line.substring((SLUG_SMILE + ": ").length());
            setValue(new Molecule(name, smile));

        }
        if (line.contains(SLUG_NAME)) {
            name = line.substring((SLUG_NAME + ": ").length());
        }
    }

    @Override
    public String toString() {
        return SLUG_NAME + ": " + getValue().getName() + "\n"
        + SLUG_SMILE + ": " + getValue().getSmile() + "\n";
    }
}
