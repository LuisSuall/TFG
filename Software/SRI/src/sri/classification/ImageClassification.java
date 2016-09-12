/**
 * Classification of an image
 * 
 * @author Luis Suárez Lloréns
 */
package sri.classification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

public class ImageClassification extends HashMap<String,ClassificationNode> {
    
    /**
     * Path of the image.
     */
    private String imagePath;
    
    private ArrayList<String> rootsNodes;

    /**
     * Constructs an empty ImageClassification for an image.
     * 
     * @param imagePath path of the image
     */
    public ImageClassification (String imagePath){
        super();
        this.imagePath = imagePath;
    }

    public ImageClassification(String imagePath, ArrayList<String> rootsNodes, Map<? extends String, ? extends ClassificationNode> m) {
        super(m);
        this.rootsNodes = rootsNodes;
        this.imagePath = imagePath;
    }
    
    /**
     * Returns the path to the image.
     * @return path
     */
    public String getImagePath() {
        return imagePath;
    }

    public ArrayList<String> getRootsNodes() {
        return rootsNodes;
    }

    public float valueOf(String concept) {
        ArrayList<String> synsetsToSearch = new ArrayList<>();
        SynsetDictionary dictionary = new SynsetDictionary();
        dictionary.loadWordsDictionary();
        
        for(String root:rootsNodes){
            synsetsToSearch.add(root);
        }
        
        float value = 0;
        
        for(int i = 0; i < synsetsToSearch.size();i++){
            String synset = synsetsToSearch.get(i);
            String def = dictionary.get(synset);
            
            if(def.contains(concept)){
                if(this.get(synset).getValue()>value){
                    value = this.get(synset).getValue();
                    if(value > 0.5){
                        synsetsToSearch.clear();
                    }
                }   
            }
            
            if(this.get(synset).getValue()>value){
                if(this.get(synset).getSonsSynsets() != null){
                    for(String synsetSon: this.get(synset).getSonsSynsets()){
                        synsetsToSearch.add(synsetSon);
                    }
                }
            }
            
        }
        
        return value;
    }
    
    public ArrayList<String> top(int n){
        ArrayList<Pair<String,Float>> leafs = new ArrayList<>();
        
        ArrayList<String> synsetsToSearch = new ArrayList<>();
        
        for(String root:rootsNodes){
            synsetsToSearch.add(root);
        }
        
        for(int i = 0; i < synsetsToSearch.size();i++){
            String synset = synsetsToSearch.get(i);

            if(this.get(synset).getSonsSynsets() != null){
                for(String synsetSon: this.get(synset).getSonsSynsets()){
                    synsetsToSearch.add(synsetSon);
                }
            }
            else{
                leafs.add(new Pair<String,Float>(synset,this.get(synset).getValue()));
            }    
        }
        
        Collections.sort(leafs,new Comparator<Pair<String,Float>>(){
            public int compare(Pair<String,Float> p1, Pair<String,Float> p2){
                return p2.getValue().compareTo(p1.getValue());
            }
        });
        
        ArrayList<String> result = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            result.add(leafs.get(i).getKey());
        }
        
        return result;
    }
}
