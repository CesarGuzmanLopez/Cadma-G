package com.main.cadma1.domain;

import java.util.LinkedList;
import java.util.List;

import com.main.shared.domain.Molecule;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize
@JsonSerialize
public class DataCadma {

    private Molecule principal;
    private List<Molecule> generatedMolecules;

    public DataCadma(Molecule principal, List<Molecule> generatedMolecules) {
        this.principal = principal;
        this.generatedMolecules = generatedMolecules;
        Molecule.deleteAllID();
    }
    protected DataCadma() {
        this.principal = new Molecule();
        this.generatedMolecules = new LinkedList<>();
        Molecule.deleteAllID();
    }
    public Molecule getPrincipal() {
        return this.principal;
    }
    public List<Molecule> getGeneratedMolecules() {
        return this.generatedMolecules;
    }
    public void setPrincipal(Molecule principal) {
        this.principal = principal;
    }
    public void setGeneratedMolecules(List<Molecule> generatedMolecules) {
        this.generatedMolecules = generatedMolecules;
    }
}
