package com.main.cadma.domain.models.smileit;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.main.cadma.domain.models.AttributeAbstract;
import com.main.shared.domain.Molecule;
@JsonSerialize
public class MoleculePrincipal extends AttributeAbstract<Molecule> {
    private static final String SLUG_SMILE = "SMILE";
    private static final String SLUG_NAME = "Principal";

    public MoleculePrincipal(Molecule value) {
        super(value, value.getName(), true);
        initialize();
    }

    public MoleculePrincipal() {
        super(null, SLUG_NAME, false);
        initialize();
    }

    private void initialize() {
        setName("");
    }

    @Override
    public void lineAnalyze(String line) {
        if (line.contains(SLUG_SMILE)) {
            String smile = line.substring((SLUG_SMILE + ": ").length());
            setValue(new Molecule(getName(), smile));

        }
        if (line.contains(SLUG_NAME)) {
            setName( line.substring((SLUG_NAME + ": ").length()));
        }
    }

    @Override
    public String toString() {
        return SLUG_NAME + ": " + getValue().getName() + "\n"
        + SLUG_SMILE + ": " + getValue().getSmile() + "\n";
    }
}
