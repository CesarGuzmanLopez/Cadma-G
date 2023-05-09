package com.main.command;

import com.main.interfaces.SmileVerificationInterface;
import com.main.smileit.interfaces.MoleculeDataFactoryInterface;

public class GenerateSubstitute extends Command {

    public GenerateSubstitute(final SmileVerificationInterface verification,
            final MoleculeDataFactoryInterface factory) {
        super(verification, factory);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        super.execute();
        generateSelects();
    }

    private void generateSelects() {
        String selects = getArg("-p");
        if (selects != null) {
            String[] selectArray = selects.split(",");
            for (String select : selectArray) {
                molecule().selectAtom(Integer.parseInt(select));
            }
        }
    }

}
