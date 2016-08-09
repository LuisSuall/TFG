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
    
    public static String defaultModelDefPath = "../Clasificador/caffenet_deploy.prototxt";
    public static String defaultModelWeightsPath = "../Clasificador/caffenet_weights.caffemodel";
    
    public ClassifierManager(){
        
    }
    
    public ClassifierManager(String modelWeightsPath){
        defaultModelWeightsPath = modelWeightsPath;
    }

    public ClassifierManager(String modelWeightsPath, String modelDefPath) {
        defaultModelDefPath = modelDefPath;
        defaultModelWeightsPath = modelWeightsPath;
    }
    
    public ImageClassification classifyImage(String imgPath){
        
        String[] cmd = {
            "/bin/bash",
            "-c",
            "../scripts/classifyImage.sh "+ imgPath + " " + defaultModelDefPath + " " +  defaultModelWeightsPath
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

    private void classifyFolder(String folderPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

  
    
}
