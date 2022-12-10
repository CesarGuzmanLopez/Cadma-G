package com.main.cadma.framework.smileit;


import com.main.cadma.interfaces.SmileFactoryInterfaces;
import com.main.smileit.interfaces.SmilesHInterface;
import  com.main.smileit.domain.models.Smiles;
import com.main.smileit.framework.cdk.VerifiedSmile;
public class SmileFactory implements SmileFactoryInterfaces {
    @Override
    public SmilesHInterface create(String smile, String name, String message, boolean hydrogenImplicit) {
        return new Smiles(name, smile, message, hydrogenImplicit,new VerifiedSmile());
    }

}
