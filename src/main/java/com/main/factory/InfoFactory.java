package com.main.factory;

import com.main.framework.cdk.InfoCDK;
import com.main.framework.cdk.VerifiedSmile;
import com.main.interfaces.GenerateInfo;
import com.main.shared.domain.Molecule;

public class InfoFactory {
    private static VerifiedSmile verifier = new VerifiedSmile();
    private InfoFactory() {
    }
    public static GenerateInfo getGenerateInfo(Molecule molecule) {
        if (verifier.isValid(molecule.getSmile())) {
            return new InfoCDK(molecule.getSmile());
        }
        return null;
    }
}
