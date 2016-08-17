/**
 * Information about all synsets of the classifier
 *
 * @author Luis Suárez Lloréns
 */
package sri;

import java.util.ArrayList;
import java.util.Collection;

public class SynsetDictionary extends ArrayList<SynsetInfo>{

    public SynsetDictionary() {
    }

    public SynsetDictionary(ArrayList<SynsetInfo> c) {
        super(c);
    }
    
    public SynsetInfo searchByName(String name){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public SynsetInfo searchBySynset(String Synset){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public SynsetInfo searchByIdx(int idx){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
