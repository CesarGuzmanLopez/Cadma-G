package com.main.framework.cdk;

import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.smiles.SmiFlavor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;

import com.main.interfaces.SmileVerificationInterface;
import com.main.interfaces.SmilesHInterface;

public class VerifiedSmile implements SmileVerificationInterface {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String smile) {
        final SmilesParser smileParser = new SmilesParser(DefaultChemObjectBuilder.getInstance());
        try {
            smileParser.parseSmiles(smile);
        } catch (InvalidSmilesException e) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final SmilesHInterface smile) {
        final SmilesParser smileParser = new SmilesParser(DefaultChemObjectBuilder.getInstance());
        try {
            smileParser.parseSmiles(smile.smile());
        } catch (InvalidSmilesException e) {
            return false;
        }
        return true;
    }
    /**
     * {@inheritDoc}
    */
    @Override
    public String toAbsoluteSmiles(final String smile) {
        final SmilesParser smileParser = new SmilesParser(DefaultChemObjectBuilder.getInstance());
        final SmilesGenerator generator = new SmilesGenerator(SmiFlavor.Absolute);
        try {
            return generator.create(smileParser.parseSmiles(smile));
        } catch (CDKException e) {
            return null;
        }

    }
}
