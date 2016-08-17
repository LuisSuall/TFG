/**
 * Information of a synset
 * 
 * @author Luis Suárez Lloréns
 */

package sri;


public class SynsetInfo {
    private int idx;
    private String synset;
    private String name;

    public SynsetInfo() {
    }

    public SynsetInfo(int idx, String synset, String name) {
        this.idx = idx;
        this.synset = synset;
        this.name = name;
    }
   
    public void setIdx(int idx) {
        this.idx = idx;
    }


    public void setSynset(String synset) {
        this.synset = synset;
    }


    public void setName(String name) {
        this.name = name;
    }
    
    public int getIdx() {
        return idx;
    }
    
    public String getSynset() {
        return synset;
    }

    public String getName() {
        return name;
    }
 
}
