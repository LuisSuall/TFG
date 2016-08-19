/**
 * Manage a caffe trained classifier
 * 
 * @author Luis Suárez Lloréns
 */

package sri;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        modelDefPath = "../Clasificador/caffenet_deploy.prototxt";
        modelWeightsPath = "../Clasificador/caffenet_weights.caffemodel";
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
            "../scripts/classifyImage.sh "+ imgPath + " " + modelDefPath + " " +  modelWeightsPath
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
        
        String s = null;
        ArrayList<Double> values = new ArrayList<>();
        
        try{
            while ((s = stdInput.readLine()) != null) {
                values.add(Double.parseDouble(s));
            }
        }
        catch(Exception e){
            System.err.println("Exception calling bash scipt: " + e.getMessage());
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
        ImageClassification imageClassification;
        
        for(String path : paths){
            imageClassification = classifyImage(path);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
