/**
 * Information about all synsets of the classifier
 *
 * @author Luis Suárez Lloréns
 */
package sri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SynsetDictionary extends ArrayList<SynsetInfo>{

    private static final String DEFAULT_DICTIONARY_PATH = "/path/to/dictionary";

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

    public void load() {
        this.load(DEFAULT_DICTIONARY_PATH);
    }

    public void load(String path) {
        this.clear();
        
        File f = new File(path);
        
        try (FileReader fis = new FileReader(f);BufferedReader reader = new BufferedReader(fis)) {
            String line = null;
            int idx = 0;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> splitedLine = splitInfo(line);
                SynsetInfo synsetInfo = new SynsetInfo(idx, splitedLine.get(0), splitedLine.get(1));
                
                this.add(synsetInfo);
                idx++;
            }  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SynsetDictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SynsetDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<String> splitInfo(String line){
        ArrayList<String> splitedLine = new ArrayList<>();
        
        splitedLine.add(line.substring(0, line.indexOf(' ')));
        splitedLine.add(line.substring(line.indexOf(' ')+1));
        
        return splitedLine;
    }
    
}
