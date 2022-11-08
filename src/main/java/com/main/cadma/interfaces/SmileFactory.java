package com.main.cadma.interfaces;

import com.main.smileit.interfaces.SmilesHInterface;

@FunctionalInterface
public interface SmileFactory {
    SmilesHInterface create(String smile,String name, String message, boolean hydrogenImplicit);
}
