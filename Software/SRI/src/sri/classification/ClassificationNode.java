/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri.classification;

import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class ClassificationNode implements java.io.Serializable{
    
    private String fatherSynset;
    private ArrayList<String> sonsSynsets;
    private float value;

    public ClassificationNode(String fatherSynset, ArrayList<String> sonsSynsets, float value) {
        this.fatherSynset = fatherSynset;
        this.sonsSynsets = sonsSynsets;
        this.value = value;
    }

    public String getFatherSynset() {
        return fatherSynset;
    }

    public void setFatherSynset(String fatherSynset) {
        this.fatherSynset = fatherSynset;
    }

    public ArrayList<String> getSonsSynsets() {
        return sonsSynsets;
    }

    public void setSonsSynsets(ArrayList<String> sonsSynsets) {
        this.sonsSynsets = sonsSynsets;
    }
    
    public void addSon(String sonSynset){
        this.sonsSynsets.add(sonSynset);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    } 
    
}
