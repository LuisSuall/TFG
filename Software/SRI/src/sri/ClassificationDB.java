/**
 * Handle a list of classifications of images
 * 
 * @author Luis Suárez Lloréns
 */

package sri;

import java.util.ArrayList;

public class ClassificationDB extends ArrayList<ImageClassification> {
    
    public ClassificationDB(){
    
    }
    
    public ClassificationDB(ArrayList<ImageClassification> imageClassifications){
        super(imageClassifications);
    }
    
    public ClassificationDB search(String name){        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public ClassificationDB searchBySynset(String synset){        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public ClassificationDB searchByIdx(int idx){        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void save(String path){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void load(String path){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
