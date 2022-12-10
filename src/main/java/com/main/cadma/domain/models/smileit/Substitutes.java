package com.main.cadma.domain.models.smileit;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.main.cadma.domain.models.AttributeAbstract;
import com.main.shared.domain.Molecule;

@JsonSerialize
public class Substitutes extends AttributeAbstract<List<Molecule>> {
    private static final String SLUG = "Substitutes";
    private static final String SLUG_SUBSTITUTES = "Number of substituent";
    private static final String SLUG_ALLOWED = "Simultaneous substitutions allowed";

    private int numberSubstitutes;
    private int numberSubstitutesRest;
    private int numberAllowed;
    private boolean startup;

    public Substitutes(List<Molecule> listSmiles) {
        super(listSmiles, SLUG, true);
        numberSubstitutes = listSmiles.size();
    }

    public Substitutes() {
        super(new LinkedList<>(), SLUG, false);
        initialize();

    }

    private void initialize() {
        numberSubstitutes = 0;
        numberSubstitutesRest = 0;
        startup = false;
    }

    @Override
    public void lineAnalyze(String line) {
        if (isFound()) {
            return;
        }
        if (!line.contains(SLUG_SUBSTITUTES) && numberSubstitutes == 0) {
            return;
        }
        if (line.contains(SLUG_SUBSTITUTES)) {
            numberSubstitutes = Integer.parseInt(line.substring((SLUG_SUBSTITUTES + ": ").length()));
            numberSubstitutesRest = Integer.parseInt(line.substring((SLUG_SUBSTITUTES + ": ").length()));

            return;
        }
        if (line.contains(SLUG_ALLOWED)) {
            numberAllowed = Integer.parseInt(line.substring((SLUG_ALLOWED + ": ").length()));
            return;
        }
        if (line.contains(SLUG)) {
            startup = true;
            return;
        }
        if (startup && numberSubstitutesRest > 0) {
            line = line.replace("\t", "").replace(" ", "");
            getValue().add(
                    new Molecule( line.split("->")[1],line.split("->")[0]));
            numberSubstitutesRest--;
        }
        if (numberSubstitutesRest == 0) {
            startup = false;
            found();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SLUG_SUBSTITUTES + ": " + numberSubstitutes + "\n");
        sb.append(SLUG_ALLOWED + ": " + numberAllowed + "\n");
        sb.append(SLUG + ":\n");
        for (Molecule smiles : getValue()) {
            sb.append("\t" + smiles.getSmile() + " -> " + smiles.getName() + "\n");
        }
        return sb.toString();
    }
}
