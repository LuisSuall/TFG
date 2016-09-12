/**
 * Manage a caffe trained classifier
 * 
 * @author Luis Suárez Lloréns
 */

package sri.classification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    private ArrayList<String> outputToSynset;
    private SynsetDictionary isA;
    private SynsetDictionary synsetToWords;
    
    /**
     * Creates a ClassifierManager for the default model.
     */
    public ClassifierManager(){  
        modelDefPath = "../Clasificadores/caffenet/deploy.prototxt";
        modelWeightsPath = "../Clasificadores/caffenet/weights.caffemodel";
        outputToSynset = loadOutputToSynset();
        
        isA = new SynsetDictionary();
        isA.loadIsADictionary();
        
        synsetToWords = new SynsetDictionary();
        synsetToWords.loadWordsDictionary();
        
        
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
        
        ArrayList<Float> values = new ArrayList<>();
        String s;
        try{
            while ((s = stdInput.readLine()) != null) {
                values.add(Float.parseFloat(s));
            }
        }
        catch(Exception e){
            System.err.println("Exception reading bash scipt: " + e.getMessage());
        }

        
        ImageClassification imageClassification = generateImageClassification(imgPath , values);

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
                paths.add(folder.getAbsolutePath()+"/"+name);
            }
            return classifyImages(paths);
        }
        
        return null;
    }

    /**
     * Loads the synset names of the net output.
     * @return synset names of the net output
     */
    private ArrayList<String> loadOutputToSynset() {
        ArrayList<String> result = new ArrayList<>();
        
        File f = new File("../synset_words.txt");
        
        try (FileReader fis = new FileReader(f);BufferedReader reader = new BufferedReader(fis)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line.substring(0, line.indexOf(' ')));
            }  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SynsetDictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SynsetDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return result;
    }

    /**
     * Generate a new ImageClassification
     * @param path path to the image
     * @param values classification values
     * 
     * @return a new ImageClassification
     */
    private ImageClassification generateImageClassification(String path, ArrayList<Float> values) {
        ArrayList<String> rootNodes = new ArrayList<>();
        HashMap<String,ClassificationNode> tree = new HashMap<>();
        
        for(int i = 0; i < values.size(); i++){
            String father = isA.get(outputToSynset.get(i));
            ArrayList<String> sons = null;
            float value = values.get(i);
            
            ClassificationNode node = new ClassificationNode(father, sons, value);
            
            tree.put(outputToSynset.get(i), node);
            
            generateHierarchy(outputToSynset.get(i),rootNodes,tree);
        }
        
        return new ImageClassification(path, rootNodes, tree);
    }

    /**
     * Generate the WordNet Hierarchy on the tree
     * @param synset Synset to actualize 
     * @param rootNodes list of root nodes
     * @param tree tree to actualize
     */
    private void generateHierarchy(String synset, ArrayList<String> rootNodes, HashMap<String, ClassificationNode> tree) {
        if(isA.containsKey(synset)){
            String father = isA.get(synset);
            
            if(tree.containsKey(father)){
                ClassificationNode fatherNode = tree.get(father);
                if(!fatherNode.getSonsSynsets().contains(synset))
                    fatherNode.addSon(synset);
                
                if(fatherNode.getValue() < tree.get(synset).getValue()){
                    fatherNode.setValue(tree.get(synset).getValue());
                    generateHierarchy(father, rootNodes,tree);
                }                
            }
            else{
                String grandfather = isA.get(father);
                ArrayList<String> sons = new ArrayList<>();
                sons.add(synset);
                float value = tree.get(synset).getValue();
                ClassificationNode fatherNode = new ClassificationNode(grandfather, sons, value);
                
                tree.put(father, fatherNode);
                
                generateHierarchy(father, rootNodes,tree);
            }
        }
        else{
            if(!rootNodes.contains(synset)){
                rootNodes.add(synset);
            }
        }
    }
    
}
