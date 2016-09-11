/**
 * Handle a list of contour feature
 *
 * @author Luis Suárez Lloréns
 */
package sri.feature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmr.result.FloatResult;
import jmr.result.ResultList;
import jmr.result.ResultMetadata;
import sri.classification.ClassificationDB;

public class FeatureDB extends ArrayList<ContourFeature> implements java.io.Serializable{

    /**
     * Default constructor.
     */
    public FeatureDB(){
        super();
    }
    
    /**
     * Creates a FeatureDB from a list of ContourFeature.
     * 
     * @param contourFeatureList input list of ContourFeature
     */
    public FeatureDB(ArrayList<ContourFeature> contourFeatureList){
        super(contourFeatureList);
    }
    
    /**
     * Searches the DB for similar features to the input feature.
     * 
     * @param featureToSearch feature to search
     * @return ResultList with the images and distance value to featureToSearch
     */
    public ResultList search(ContourFeature featureToSearch){
        if (featureToSearch.size() != this.get(0).size()){
            featureToSearch.resizeFeature(this.get(0).size());
        }
        
        ResultList resultList = new ResultList();
        
        for(ContourFeature feature:this){
            FloatResult distance = new FloatResult((float) featureToSearch.distance(feature));
            
            resultList.add(new ResultMetadata(distance, feature.getPath()));
        }
        
        resultList.sort();
        
        return resultList;
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
            FeatureDB loadedData = (FeatureDB)objectIn.readObject();
            
            this.clear();
            
            for(ContourFeature feature: loadedData){
                this.add(feature);
            }   
        }
        catch(IOException e){
            System.err.println("Exception loading DB: " + e.getMessage());            
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ClassificationDB.class.getName()).log(Level.SEVERE, null, e);
        }
            
    }
}
