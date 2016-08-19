/**
 * Information about all synsets of the classifier
 *
 * @author Luis Suárez Lloréns
 */
package sri;

import java.util.ArrayList;

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
        for(SynsetInfo synsetInfo: this){
            if (synsetInfo.getConcept().equals(concept))
                return synsetInfo;
        }
        return null;
    }
    
    /**
     * Returns the synset information of a synset.
     * 
     * @param synset synset to search
     * @return information of the synset
     */
    public SynsetInfo searchBySynset(String synset){
        for(SynsetInfo synsetInfo: this){
            if (synsetInfo.getSynset().equals(synset))
                return synsetInfo;
        }
        return null;
    }
    
    /**
     * Returns the synset information with a set index.
     * 
     * @param idx index to search
     * @return information of the synset
     */
    public SynsetInfo searchByIdx(int idx){
        for(SynsetInfo synsetInfo: this){
            if (synsetInfo.getIdx() == idx)
                return synsetInfo;
        }
        return null;
    }
}
