/**
 * Contour feature
 * 
 * @author Luis Suárez Lloréns
 */
package sri.feature;

import static java.lang.Math.sqrt;
import java.util.ArrayList;

public class ContourFeature extends ArrayList<Double>{
    
    private String path;
    
    public ContourFeature(){
        super();
    }
    
    public ContourFeature(String path, ArrayList<Double> values){
        super(values);
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void normalize(){
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        
        for(double value:this){
            if (value > max){
                max = value;
            }
            if (value < min){
                min = value;
            }
        }
        
        for(int i = 0; i < this.size(); i++){
            this.set(i, (this.get(i)-min)/(max-min));
        }
    }
    
    public void normalizeOrder(){
        int maxIdx = -1;
        double maxValue = Double.NEGATIVE_INFINITY;
        
        for(int i = 0; i < this.size(); i++){
            if (this.get(i) > maxValue){
                maxValue = this.get(i);
                maxIdx = i;
            }
        }
        
        ArrayList<Double> orderedValues = new ArrayList<>();
        
        for(int i = maxIdx; i < this.size(); i++){
            orderedValues.add(this.get(i));
        }
        for(int i = 0; i < maxIdx; i++){
            orderedValues.add(this.get(i));
        }
        
        for(int i = 0; i < this.size(); i++){
            this.set(i, orderedValues.get(i));
        }
        
    }
    
    public void resizeFeature(int length){
        if (length < this.size()){
            reduceFeatureSize(length);
        }
        else if (this.size() < length){
            increaseFeatureSize(length);
        }            
    }
    
    private void reduceFeatureSize(int length){
        ArrayList<Double> newValues = new ArrayList<>();
        double step = this.size()/(this.size()-length);
        double nextStepPosition = step;
        double lastValue = 0.0;
        
        for(int i = 0; i < this.size(); i++){
            if ((double) i >= nextStepPosition){
                nextStepPosition += step;
            }
            else if(newValues.size() < length){
                newValues.add(this.get(i));
            }
            
            lastValue = this.get(i);
        }
        
        while(newValues.size() < length){
            newValues.add(lastValue);
        }
        
        this.clear();
        
        for(double value:newValues){
            this.add(value);
        }
    }
    
    private void increaseFeatureSize(int length){
        ArrayList<Double> newValues = new ArrayList<>();
        double step = this.size()/(this.size()-length);
        double nextStepPosition = step;
        double lastValue = 0.0;
        
        for(int i = 0; i < this.size(); i++){
            if ((double) i >= nextStepPosition){
                nextStepPosition += step;
                if(newValues.size() < length){
                    newValues.add((this.get(i)+lastValue)/2);
                }
                if(newValues.size() < length){
                    newValues.add(this.get(i));
                }
                
            }
            else if(newValues.size() < length){
                newValues.add(this.get(i));
            }
            
            lastValue = this.get(i);
        }
        
        while(newValues.size() < length){
            newValues.add(lastValue);
        }
        
        this.clear();
        
        for(double value:newValues){
            this.add(value);
        }
    }

    public double distance(ContourFeature feature) {
        if(this.size() == feature.size()){
            double result = 0;
            
            for(int i = 0; i < this.size(); i++){
                double difference = this.get(i)-feature.get(i);
                result += difference*difference;
            }
            
            return sqrt(result);
        }
        else{
            return -1;
        }
    }
}
