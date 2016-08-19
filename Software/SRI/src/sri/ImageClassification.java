/**
 * Classification of an image
 * 
 * @author Luis Suárez Lloréns
 */
package sri;

import java.util.ArrayList;

public class ImageClassification extends ArrayList<Double> {
    
    /**
     * Path of the image.
     */
    private String imagePath;
    /**
     * Idx of the best classification.
     */
    private int bestClass;
    /**
     * Value of the best classification.
     */
    private double probabilityBestClass;
    
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
     * Constructs an ImageClassification with set values.
     * 
     * @param imagePath path of the image
     * @param values clasification values of the image
     */
    public ImageClassification (String imagePath, ArrayList<Double> values){
        super(values);
        this.imagePath = imagePath;
        
        setBestClassInfo();
    }
    
    /**
     * Changes the classification values.
     * 
     * @param values new classification values 
     */
    public void setClassificationValues (ArrayList<Double> values){
        this.clear();
        
        for(Double value : values){
            this.add(value);
        }
        
        setBestClassInfo();
    }
    
    /**
     * Updates the best classification information.
     */
    public void setBestClassInfo(){
        bestClass = -1;
        probabilityBestClass = 0.0;
        
        for (int i = 0; i < this.size(); i++){
            if (this.get(i) > probabilityBestClass){
                bestClass = i;
                probabilityBestClass = this.get(i);
            }
        }
    }
    
}
