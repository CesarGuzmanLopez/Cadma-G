package com.main.shared.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**Interface for the SmilesH.
 *
 * @author Cesar Gerardo Guzman Lopez
 * @Description: Contain a name of the molecule, the smile and the message xand
 *                especially for the hydrogen implicit.
 */
@JsonSerialize
@JsonDeserialize
public  class Molecule {
    static final List<Integer> ListOfID = new ArrayList<>();

    private Integer id;
    private String name;
    private String smile;
    private String pathImage;
    private String Smart;

    private List<Molecule> substitutes;

    public Molecule(){}
    public void setID(){
        int tempId = 0;
        while(ListOfID.contains(tempId)){
            tempId++;
        }
        ListOfID.add(tempId);
        this.id = tempId;
    }
    static public void deleteAllID(){
        ListOfID.clear();
    }
    public int getID(){
        if(this.id == null)
            return 0;
        return this.id;
    }
    public void setID(int id){

        if(ListOfID.contains(id) && id != 0){
            throw new IllegalArgumentException("ID already exist");
        }

        this.id = id;
    }
    public Molecule(String name, String smile) {
        this.name = name;
        this.smile = smile;
        this.pathImage = "" ;
        substitutes = new ArrayList<>();
    }
    /**
     * @return name of Smile.
    */
    public String  getName(){
        return name;
    }

    public String getSmart() {
        return Smart;
    }

    public void setSmart(String smart) {
        Smart = smart;
    }

    public void setName(String name){
        this.name = name;
    }
    /**
     * @return  Smile.
    */
    public String  getSmile(){
        return smile;
    }

    /**
     * @return  Path.
    */

    public String  getPathImage(){
        return pathImage;
    }

    /**
     * @param String Path
    */
    public void setPathImage(String path){
        this.pathImage = path;
    }

    /**
     * @param MoleculeSubstitute
    */
    public void addSubstitutes(Molecule substitutes){
        this.substitutes.add(substitutes);
    }
    /**
     * @param String namePrincipal
     * @param String smile
     */
    public void addSubstitutes(String namePrincipal, String smile){
        this.substitutes.add(new Molecule(namePrincipal, smile));
    }

    public List<Molecule> getSubstitutes() {
        return this.substitutes;
    }
    @Override
    public String toString() {
        return name;
    }
    public String toStringAll() {
        return "Molecule [id=" + id + ", name=" + name + ", smile=" + smile + ", pathImage=" + pathImage
                + ", substitutes=" + substitutes + "]";
    }
}
