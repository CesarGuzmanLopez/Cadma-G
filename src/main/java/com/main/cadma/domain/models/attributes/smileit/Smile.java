package com.main.cadma.domain.models.attributes.smileit;

import com.main.cadma.domain.models.AttributeAbstract;
import com.main.smileit.interfaces.SmilesHInterface;

public class Smile extends AttributeAbstract<SmilesHInterface> {
    private static final String SLUG_SMILE = "SMILE";
    private static final String SLUG_NAME = "Principal";

    private String name;

    protected Smile(SmilesHInterface value) {
        super(value, SLUG_SMILE, true);
        initialize();
    }

    protected Smile() {
        super(null, SLUG_SMILE, false);
        initialize();
    }

    private void initialize() {
        name = "";
    }

    @Override
    public void lineAnalyze(String line) {
        if (line.contains(SLUG_SMILE)) {
            String smile = line.substring((SLUG_SMILE + ": ").length());
            if (smileFactory != null) {
                setValue(smileFactory.create(smile, name, "Smile", true));
            } else
                throw new IllegalArgumentException("SmileFactory not defined , define it with setSmileFactory");
        }
        if (line.contains(SLUG_NAME)) {
            name = line.substring((SLUG_NAME + ": ").length());
        }
    }

    @Override
    public String toString() {
        return SLUG_NAME + ": " + getValue().smile() + "\n" + SLUG_SMILE + ": " + getValue().getName() + "\n";
    }
}
