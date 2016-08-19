/**
 * Information of a synset
 * 
 * @author Luis Suárez Lloréns
 */

package sri;


public class SynsetInfo {
    /**
     * Index of the synset
     */
    private int idx;
    /**
     * Name of the synset.
     */
    private String synset;
    /**
     * Concept of the synset.
     */
    private String concept;

    /**
     * Default constructor.
     */
    public SynsetInfo() {
    }

    /**
     * Creates a new SynsetInfo.
     * 
     * @param idx index of the synset
     * @param synset name of the synset
     * @param concept concept of the synset
     */
    public SynsetInfo(int idx, String synset, String concept) {
        this.idx = idx;
        this.synset = synset;
        this.concept = concept;
    }
   
    /**
     * Sets the index.
     * @param idx index to set
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

    /**
     * Sets the synset.
     * @param synset synset to set
     */
    public void setSynset(String synset) {
        this.synset = synset;
    }

    /**
     * Sets the concept.
     * @param concept 
     */
    public void setConcept(String concept) {
        this.concept = concept;
    }
    
    /**
     * Returns synset's index.
     * 
     * @return synset's index
     */
    public int getIdx() {
        return idx;
    }
    
    /**
     * Returns synset's name.
     * 
     * @return synset's name
     */
    public String getSynset() {
        return synset;
    }
    
    /**
     * Returns synset's concept.
     * 
     * @return synset's concept 
     */
    public String getConcept() {
        return concept;
    }
 
}
