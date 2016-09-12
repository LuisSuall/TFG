/**
 * Classification of an image.
 * 
 * Classifications are stored in a tree. 
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
    /**
     * Starting nodes of the tree.
     */
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

    /**
     * Construct an ImageClassification
     * @param imagePath path of the image
     * @param rootsNodes starting nodes of the tree
     * @param tree classification tree
     */
    public ImageClassification(String imagePath, ArrayList<String> rootsNodes, Map<? extends String, ? extends ClassificationNode> tree) {
        super(tree);
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
    /**
     * Returns the roots of the tree.
     * @return roots of the tree
     */
    public ArrayList<String> getRootsNodes() {
        return rootsNodes;
    }

    /**
     * Searches the tree to find a concept
     * @param concept concept to find
     * @return value asociated to the concept
     */
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
    
    /**
     * Get the n best leaves of the tree
     * @param n number of leaves to return
     * @return synset of the leaves
     */
    public ArrayList<String> top(int n){
        ArrayList<Pair<String,Float>> leaves = new ArrayList<>();
        
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
                leaves.add(new Pair<String,Float>(synset,this.get(synset).getValue()));
            }    
        }
        
        Collections.sort(leaves,new Comparator<Pair<String,Float>>(){
            public int compare(Pair<String,Float> p1, Pair<String,Float> p2){
                return p2.getValue().compareTo(p1.getValue());
            }
        });
        
        ArrayList<String> result = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            result.add(leaves.get(i).getKey());
        }
        
        return result;
    }
}
