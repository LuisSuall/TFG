/**
 * Handle a list of classifications of images
 * 
 * @author Luis Suárez Lloréns
 */

package sri;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassificationDB extends ArrayList<ImageClassification> implements java.io.Serializable{
    
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
        synsetDictionary.load();
        
        ArrayList<SynsetInfo> synsetInfoList = synsetDictionary.search(concept);
        ArrayList<Integer> idxList = new ArrayList<>();
        
        for(SynsetInfo synset: synsetInfoList){
            idxList.add(synset.getIdx());
        }
        
        return search(idxList,n);
    }
    
    /**
     * Searches for the best classified images in the DB by a list of index.
     * 
     * @param idxList list of index
     * @param n number of images to search
     * @return new ClassificationDB with the best images 
     */
    public ClassificationDB search(ArrayList<Integer> idxList, int n){
        ArrayList<Integer> bestResultsIdx = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            int bestIdx = -1;
            double bestValue = -1.0;
            
            for(int j=0; j<this.size(); j++){
                ImageClassification imgClass = this.get(j);
                int bestClass = imgClass.getBestClass(idxList);
                if(imgClass.get(bestClass) > bestValue && !bestResultsIdx.contains(j)){
                    bestIdx = j;
                    bestValue = imgClass.get(bestClass);
                }
            }
            
            bestResultsIdx.add(bestIdx);
        }
        
        ClassificationDB searchResult = new ClassificationDB();
        
        for(int i: bestResultsIdx){
            searchResult.add(this.get(i));
        }
        
        return searchResult;
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
        
        for(int i=0; i<n; i++){
            int bestIdx = -1;
            double bestValue = -1.0;
            
            for(int j=0; j<this.size(); j++){
                if(this.get(j).get(idx) > bestValue && !bestResultsIdx.contains(j)){
                    bestIdx = j;
                    bestValue = this.get(j).get(idx);
                }
            }
            
            bestResultsIdx.add(bestIdx);
        }
        
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
        try(FileOutputStream file = new FileOutputStream(path); ObjectOutputStream objectOut = new ObjectOutputStream(file)) {
            objectOut.writeObject(this);
        }
        catch(IOException e){
            System.err.println("Exception saving DB: " + e.getMessage());
        }
        
    }
    
    /**
     * Loads the data base from a file.
     * 
     * @param path path to the DB.
     */
    public void load(String path){
        try(FileInputStream file = new FileInputStream(path); ObjectInputStream objectIn = new ObjectInputStream(file)) {
            ClassificationDB loadedData = (ClassificationDB)objectIn.readObject();
            
            this.clear();
            
            for(ImageClassification img: loadedData){
                this.add(img);
            }   
        }
        catch(IOException e){
            System.err.println("Exception loading DB: " + e.getMessage());            
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ClassificationDB.class.getName()).log(Level.SEVERE, null, e);
        }
            
    }
}
