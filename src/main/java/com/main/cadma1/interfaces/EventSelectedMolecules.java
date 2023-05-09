package com.main.cadma1.interfaces;

import java.util.List;

import com.main.shared.domain.Molecule;

public interface EventSelectedMolecules {
    void runSelected(List<Molecule> selectedMolecules, Molecule moleculePrincipal);
}
