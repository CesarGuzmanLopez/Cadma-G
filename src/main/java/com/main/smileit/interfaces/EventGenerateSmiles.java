package com.main.smileit.interfaces;

import java.util.List;

import com.main.smileit.domain.models.Molecule;

@FunctionalInterface
public interface EventGenerateSmiles {
    /**
     * This method is called when the is completed the process.
     */
    void execute( String pathPrincipal,Molecule principal, List<Molecule> substitutes, List<Molecule> smiles);
}
