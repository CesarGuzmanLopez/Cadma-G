package com.main.common.methods;
import com.main.framework.cdk.VerifiedSmile;
public class CompareSmiles {
    private CompareSmiles() {}
    public static boolean compareSmiles(String smiles1, String smiles2) {
        VerifiedSmile verifiedSmile = new VerifiedSmile();
        if (verifiedSmile.isValid(smiles1) && verifiedSmile.isValid(smiles2)) {
            String absoluteSmiles1 = verifiedSmile.toAbsoluteSmiles(smiles1);
            String absoluteSmiles2 = verifiedSmile.toAbsoluteSmiles(smiles2);
            if (absoluteSmiles1.equals(absoluteSmiles2)) {
                return true;
            }
        }
        return false;
    }
}
