package com.main.interfaces;
/**Interface for the SmilesH.
 *
 * @author Cesar Gerardo Guzman Lopez
 * @Description: Contain a name of the molecule, the smile and the message xand
 *                especially for the hydrogen implicit.
 */
public  interface SmilesHInterface {
    /**
     * @return name of Smile.
    */
    String  getName();
    /**
     * @return  Smile.
    */
    String  smile();
    /**
     * @return  Message.
    */
    String  getMessage();
    /**
     * @return  Hydrogen true if has hydrogen implicit.
    */
    boolean hasHydrogenImplicit();
}
