/**
 * Manage a caffe trained classifier
 * 
 * @author Luis Suárez Lloréns
 */

package sri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassifierManager {
    
    /**
     * Path to model's definition.
     */
    private String modelDefPath;
    /**
     * Path to model's weights.
     */
    private String modelWeightsPath;
    
    /**
     * Creates a ClassifierManager for the default model.
     */
    public ClassifierManager(){  
        modelDefPath = "../Clasificadores/caffenet/deploy.prototxt";
        modelWeightsPath = "../Clasificadores/caffenet/weights.caffemodel";
    }

    /**
     * Creates a ClassifierManager for a model.
     * 
     * @param modelWeightsPath path to the model weights
     * @param modelDefPath path to the model definition
     */
    public ClassifierManager(String modelWeightsPath, String modelDefPath) {
        this.modelDefPath = modelDefPath;
        this.modelWeightsPath = modelWeightsPath;
    }

    /**
     * Sets the model definition path.
     * 
     * @param modelDefPath path to the model definition
     */
    public void setModelDefPath(String modelDefPath) {
        this.modelDefPath = modelDefPath;
    }

    /**
     * Sets the model weights path.
     * 
     * @param modelWeightsPath path to the model weights
     */
    public void setModelWeightsPath(String modelWeightsPath) {
        this.modelWeightsPath = modelWeightsPath;
    }
    
    /**
     * Classifies an image.
     * 
     * @param imgPath path of the image to classify
     * 
     * @return Classification of the image
     */
    public ImageClassification classifyImage(String imgPath){
        
        String[] cmd = {
            "/bin/bash",
            "-c",
            "../scripts/classify_image.sh "+ imgPath + " " + modelDefPath + " " +  modelWeightsPath
        };
        
        Process p = null;
        
        try{
            p = Runtime.getRuntime().exec(cmd);
        }
        catch(Exception e){
            System.err.println("Exception calling bash scipt: " + e.getMessage());
        }
        
        BufferedReader stdInput = new BufferedReader(new 
            InputStreamReader(p.getInputStream()));
        
        ArrayList<Double> values = new ArrayList<>();
        String s;
        try{
            while ((s = stdInput.readLine()) != null) {
                values.add(Double.parseDouble(s));
            }
        }
        catch(Exception e){
            System.err.println("Exception reading bash scipt: " + e.getMessage());
        }

        ImageClassification imageClassification = new ImageClassification(imgPath,values);

        return imageClassification;
    } 

    /**
     * Classifies a list of images.
     * 
     * @param paths list of paths of the images to classify
     * 
     * @return ClassificationDB of the images
     */
    public ClassificationDB classifyImages(ArrayList<String> paths){
        ClassificationDB newDB = new ClassificationDB();
        
        for(String path : paths){
            ImageClassification imageClassification = classifyImage(path);
            newDB.add(imageClassification);
        }
        
        return newDB;
    }
    
    /**
     * Classifies a folder of images.
     * 
     * @param folderPath path of the folder to classify
     * 
     * @return ClassificationDB of the folder
     */
    public ClassificationDB classifyFolder(String folderPath) {
        File folder = new File(folderPath);
        ArrayList<String> paths = new ArrayList<>();
        
        if(folder.isDirectory()){
            // create new filename filter
            FilenameFilter filter= new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jpg");
                }
            };
            
            String[] filesnames = folder.list(filter);
            
            for(String name: filesnames){
                paths.add(folder.getAbsolutePath()+name);
            }
            return classifyImages(paths);
        }
        
        return null;
    }
    
}
