/**
 * Handle a list of classifications of images
 * 
 * @author Luis Suárez Lloréns
 */

package sri;

import java.util.ArrayList;

public class ClassificationDB extends ArrayList<ImageClassification> {
    
    /**
     *  Default number of search results
     */
    public static final int DEFAULT_SEARCH_RESULT_N = 5;
    
    /**
     * Default constructor.
     */
    public ClassificationDB(){   
    }
    
    /**
     * Creates a DB with a list of ImageClassification
     * 
     * @param imageClassifications list of ImageClassification
     */
    public ClassificationDB(ArrayList<ImageClassification> imageClassifications){
        super(imageClassifications);
    }
    
    /**
     * Searches for the best classified images in the DB by concept.
     * 
     * @param concept concept to search
     * @return new ClassificationDB with the best images
     */
    public ClassificationDB search(String concept){        
        return search(concept,DEFAULT_SEARCH_RESULT_N);
    }
    
    /**
     * Searches for the best classified images in the DB by concept.
     * 
     * @param concept concept to search
     * @param n number of images to search
     * @return new ClassificationDB with the best images
     */
    public ClassificationDB search(String concept, int n){        
        SynsetDictionary synsetDictionary = new SynsetDictionary();
        
        SynsetInfo synsetInfo = synsetDictionary.searchByConcept(concept);
        
        return searchByIdx(synsetInfo.getIdx(),n);
    }
    
    /**
     * Searches for the best classified images in the DB by synset.
     * 
     * @param synset synset to search
     * @return new ClassificationDB with the best images
     */
    public ClassificationDB searchBySynset(String synset){        
        return searchBySynset(synset, DEFAULT_SEARCH_RESULT_N);
    }
    
    /**
     * Searches for the best classified images in the DB by synset.
     * 
     * @param synset synset to search
     * @param n number of images to search
     * @return new ClassificationDB with the best images
     */
    public ClassificationDB searchBySynset(String synset, int n){        
        SynsetDictionary synsetDictionary = new SynsetDictionary();
        
        SynsetInfo synsetInfo = synsetDictionary.searchBySynset(synset);
        
        return searchByIdx(synsetInfo.getIdx(),n);
    }
    
    /**
     * Searches for the best classified images in the DB by index.
     * 
     * @param idx index to search
     * @return new ClassificationDB with the best images
     */
    public ClassificationDB searchByIdx(int idx){        
        return searchByIdx(idx, DEFAULT_SEARCH_RESULT_N);
    }
    
    /**
     * Searches for the best classified images in the DB by index.
     * 
     * @param idx index to search
     * @param n number of images to search
     * @return new ClassificationDB with the best images
     */
    public ClassificationDB searchByIdx(int idx, int n){        
        
        ArrayList<Integer> bestResultsIdx = new ArrayList<>();
        
        //TODO: ordered search
        
        ClassificationDB searchResult = new ClassificationDB();
        
        for(int i: bestResultsIdx){
            searchResult.add(this.get(i));
        }
        
        return searchResult;
    }
    
    /**
     * Saves the data base in a file.
     * 
     * @param path path to the DB.
     */
    public void save(String path){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Loads the data base from a file.
     * 
     * @param path path to the DB.
     */
    public void load(String path){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
