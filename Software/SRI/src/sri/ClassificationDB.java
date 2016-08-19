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
    
    public ClassificationDB(){   
    }
    
    public ClassificationDB(ArrayList<ImageClassification> imageClassifications){
        super(imageClassifications);
    }
    
    public ClassificationDB search(String name){        
        return search(name,DEFAULT_SEARCH_RESULT_N);
    }
    
    public ClassificationDB search(String name, int n){        
        SynsetDictionary synsetDictionary = new SynsetDictionary();
        
        SynsetInfo synsetInfo = synsetDictionary.searchByName(name);
        
        return searchByIdx(synsetInfo.getIdx(),n);
    }
    
    public ClassificationDB searchBySynset(String synset){        
        return searchBySynset(synset, DEFAULT_SEARCH_RESULT_N);
    }
    
    public ClassificationDB searchBySynset(String synset, int n){        
        SynsetDictionary synsetDictionary = new SynsetDictionary();
        
        SynsetInfo synsetInfo = synsetDictionary.searchBySynset(synset);
        
        return searchByIdx(synsetInfo.getIdx(),n);
    }
    
    public ClassificationDB searchByIdx(int idx){        
        return searchByIdx(idx, DEFAULT_SEARCH_RESULT_N);
    }
    
    public ClassificationDB searchByIdx(int idx, int n){        
        
        ArrayList<Integer> bestResultsIdx = new ArrayList<>();
        
        //TODO: ordered search
        
        ClassificationDB searchResult = new ClassificationDB();
        
        for (int i: bestResultsIdx){
            searchResult.add(this.get(i));
        }
        
        return searchResult;
    }
    
    public void save(String path){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void load(String path){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
