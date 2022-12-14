package com.main.shared.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**Interface for the SmilesH.
 *
 * @author Cesar Gerardo Guzman Lopez
 * @Description: Contain a name of the molecule, the smile and the message xand
 *                especially for the hydrogen implicit.
 */
@JsonSerialize
public  class Molecule {

    private String name;
    private String smile;
    private String message;
    private String pathImage;
    private List<Molecule> subbstitutes;
    public Molecule(String name, String smile, String message, String path) {
        this.name = name;
        this.smile = smile;
        this.message = message;
        this.pathImage = path;
        subbstitutes = new ArrayList<>();
    }

    public Molecule(String name, String smile) {
        this.name = name;
        this.smile = smile;
        this.message = "";
        this.pathImage = "" ;

    }


    
    /**
     * @return name of Smile.
    */
    public String  getName(){
        return name;
    }


    /**
     * @return  Smile.
    */
    public String  getSmile(){
        return smile;
    }
    /**
     * @return  Message.
    */
    public String  getMessage(){
        return message;
    }
    /**
     * @return  Path.
    */

    public String  getPathImage(){
        return pathImage;
    }
    /**
     * @param MoleculeSubstitute
    */
    public void addSubstitutes(Molecule substitutes){
        this.subbstitutes.add(substitutes);
    }
    /**
     * @param String namePrincipal
     * @param String smile
     */
    public void addSubstitutes(String namePrincipal, String smile){
        this.subbstitutes.add(new Molecule(namePrincipal, smile));
    }

}
