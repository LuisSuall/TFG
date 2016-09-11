/**
 * Classification of an image
 * 
 * @author Luis Suárez Lloréns
 */
package sri.classification;

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

    /**
     * Returns the indexs of the best n classifications values.
     * @param n number of classifications
     * @return list of indexs of the best values
     */
    public ArrayList<Integer> top(int n){
        ArrayList<Integer> bestResultsIdx = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            int bestIdx = -1;
            double bestValue = -1.0;
            
            for(int j=0; j<this.size(); j++){
                if(this.get(j) > bestValue && !bestResultsIdx.contains(j)){
                    bestIdx = j;
                    bestValue = this.get(j);
                }
            }
            
            bestResultsIdx.add(bestIdx);
        }
        
        return bestResultsIdx;
    }
    
    /**
     * Returns the path to the image.
     * @return path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Returns the best classification class.
     * @return Index of the best class
     */
    public int getBestClass() {
        return bestClass;
    }
    
    /**
     * Returns the best classification class of a set list of classes.
     * @param idxList list of classes
     * @return Index of the best class
     */
    public int getBestClass(ArrayList<Integer> idxList){
        int bestIdx = -1;
        double max = -1;
        
        for(int idx : idxList){
            if(this.get(idx)>max){
                bestIdx = idx;
                max = this.get(idx);
            }
        }
        
        return bestIdx;
    }

    /**
     * Returns the probability of the best class.
     * @return probability of the best class
     */
    public double getProbabilityBestClass() {
        return probabilityBestClass;
    }
    
}
