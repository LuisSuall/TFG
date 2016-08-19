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
    
    private String modelDefPath = "../Clasificador/caffenet_deploy.prototxt";
    private String modelWeightsPath = "../Clasificador/caffenet_weights.caffemodel";
    
    public ClassifierManager(){  
    }

    public ClassifierManager(String modelWeightsPath, String modelDefPath) {
        this.modelDefPath = modelDefPath;
        this.modelWeightsPath = modelWeightsPath;
    }

    public void setModelDefPath(String modelDefPath) {
        this.modelDefPath = modelDefPath;
    }

    public void setModelWeightsPath(String modelWeightsPath) {
        this.modelWeightsPath = modelWeightsPath;
    }
    
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

    public ClassificationDB classifyImages(ArrayList<String> paths){
        ClassificationDB newDB = new ClassificationDB();
        ImageClassification imageClassification = null;
        
        for(String path : paths){
            imageClassification = classifyImage(path);
            newDB.add(imageClassification);
        }
        
        return newDB;
    }
    
    public ClassificationDB classifyFolder(String folderPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
