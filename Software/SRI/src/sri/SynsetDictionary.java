/**
 * Information about all synsets of the classifier
 *
 * @author Luis Suárez Lloréns
 */
package sri;

import java.util.ArrayList;
import java.util.Collection;

public class SynsetDictionary extends ArrayList<SynsetInfo>{

    /**
     * Default constructor.
     */
    public SynsetDictionary() {
    }

    /**
     * Creates a synset dictionary with a list of synset information.
     * 
     * @param c Array of SynsetInformation
     */
    public SynsetDictionary(ArrayList<SynsetInfo> c) {
        super(c);
    }
    
    /**
     * Returns the synset information of a concept.
     * 
     * @param concept concept to search
     * @return information of the synset
     */
    public SynsetInfo searchByConcept(String concept){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Returns the synset information of a synset.
     * 
     * @param synset synset to search
     * @return information of the synset
     */
    public SynsetInfo searchBySynset(String synset){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Returns the synset information with a set index.
     * 
     * @param idx index to search
     * @return information of the synset
     */
    public SynsetInfo searchByIdx(int idx){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
