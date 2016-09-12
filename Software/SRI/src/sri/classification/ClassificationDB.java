/**
 * Handle a list of classifications of images
 * 
 * @author Luis Suárez Lloréns
 */

package sri.classification;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmr.result.FloatResult;
import jmr.result.ResultList;
import jmr.result.ResultMetadata;

public class ClassificationDB extends ArrayList<ImageClassification> implements java.io.Serializable{

    /**
     * Default constructor.
     */
    public ClassificationDB(){   
    }
    
    /**
     * Creates a DB from a list of ImageClassification
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
     * @return ResultList with the images and success value
     */
    public ResultList search(String concept){        
        ResultList searchResult = new ResultList();
        
        for(ImageClassification imgClass: this){
            FloatResult value;
            value = new FloatResult(imgClass.valueOf(concept));
            
            searchResult.add(new ResultMetadata(value, imgClass.getImagePath()));
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
