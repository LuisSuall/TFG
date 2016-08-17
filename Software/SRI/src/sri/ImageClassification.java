/**
 * Classification of an image
 * 
 * @author Luis Suárez Lloréns
 */
package sri;

import java.util.ArrayList;

public class ImageClassification extends ArrayList<Double> {
    
    private String imagePath;
    private int bestClass;
    private double probabilityBestClass;
    
    public ImageClassification (String imagePath){
        super();
        this.imagePath = imagePath;
    }
    
    public ImageClassification (String imagePath, ArrayList<Double> values){
        super(values);
        this.imagePath = imagePath;
        
        setBestClassInfo();
    }
    
    public void setClassificationValues (ArrayList<Double> values){
        this.clear();
        
        for (Double value : values){
            this.add(value);
        }
        
        setBestClassInfo();
    }
    
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
