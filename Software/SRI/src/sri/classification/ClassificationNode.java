/**
 * Represents a node of the classification tree
 * 
 * @author Luis Suárez Lloréns
 */
package sri.classification;

import java.util.ArrayList;


public class ClassificationNode implements java.io.Serializable{
    
    /**
     * Father of the node
     */
    private String fatherSynset;
    /**
     * List of sons of the node
     */
    private ArrayList<String> sonsSynsets;
    /**
     * Classification value of the node
     */
    private float value;
    
    
    /**
     * Constructs a new ClassificationNode.
     * @param fatherSynset father of the node
     * @param sonsSynsets list of sons of the node
     * @param value value of the node
     */
    public ClassificationNode(String fatherSynset, ArrayList<String> sonsSynsets, float value) {
        this.fatherSynset = fatherSynset;
        this.sonsSynsets = sonsSynsets;
        this.value = value;
    }

    /**
     * Returns the father of the node.
     * @return father's synset
     */
    public String getFatherSynset() {
        return fatherSynset;
    }

    /**
     * Sets the node's father.
     * @param fatherSynset father's synset
     */
    public void setFatherSynset(String fatherSynset) {
        this.fatherSynset = fatherSynset;
    }

    /**
     * Returns the sons of the node.
     * @return list of sons synset
     */
    public ArrayList<String> getSonsSynsets() {
        return sonsSynsets;
    }
    
    /**
     * Sets the node's sons.
     * @param sonsSynsets list of sons synset
     */
    public void setSonsSynsets(ArrayList<String> sonsSynsets) {
        this.sonsSynsets = sonsSynsets;
    }
    
    /**
     * Adds a new son
     * @param sonSynset synset of the new son
     */
    public void addSon(String sonSynset){
        this.sonsSynsets.add(sonSynset);
    }

    /**
     * Returns the value of the node
     * @return value
     */
    public float getValue() {
        return value;
    }
    /**
     * Sets the value of the node
     * @param value new value of the node
     */
    public void setValue(float value) {
        this.value = value;
    } 
    
}
