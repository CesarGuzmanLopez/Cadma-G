package com.main.cadma.interfaces;

import java.util.List;

import com.main.smileit.domain.generator.GeneratorUtils;
import com.main.smileit.interfaces.SmilesHInterface;

@FunctionalInterface
public interface SmileFactoryInterfaces {
    SmilesHInterface create(String smile,String name, String message, boolean hydrogenImplicit);
    public static  void saveImages(final List<SmilesHInterface> value, String nameOfPrincipal, String sabeImagesPath){
        GeneratorUtils.saveImages(value, nameOfPrincipal, sabeImagesPath);
    }
}
