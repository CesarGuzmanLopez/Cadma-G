package com.main.cadma.domain.models.attributes.smileit;

import java.util.LinkedList;
import java.util.List;

import com.main.cadma.domain.models.AttributeAbstract;
import com.main.smileit.interfaces.SmilesHInterface;

public class Substitutes extends AttributeAbstract<List<SmilesHInterface>> {
    private static final String SLUG = "Substitutes";
    private static final String SLUG_SUBSTITUTES = "Number of substituent";
    private static final String SLUG_ALLOWED = "Simultaneous substitutions allowed";

    private int numberSubstitutes;
    private int numberSubstitutesRest;
    private int numberAllowed;
    private boolean startup;

    public Substitutes(List<SmilesHInterface> listSmiles) {
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
            if (smileFactory == null) {
                throw new IllegalArgumentException(
                        "SmileFactory is null pleas set it whit AttributeAbstract.setSmileFactory()");
            }
            line = line.replace("\t", "").replace(" ", "");
            getValue().add(smileFactory.create(line.split("->")[0], line.split("->")[1], "", true));
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
        for (SmilesHInterface smiles : getValue()) {
            sb.append("\t" + smiles.smile() + " -> " + smiles.getName() + "\n");
        }
        return sb.toString();
    }
}
