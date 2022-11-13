package com.main.smileit.interfaces;

import java.util.List;

@FunctionalInterface
public interface EventGenerateSmiles {
    /**
     * This method is called when the is completed the process.
     */
    void execute( String pathPrincipal,SmilesHInterface principal, List<SmilesHInterface> substitutes, List<SmilesHInterface> smiles);
}
