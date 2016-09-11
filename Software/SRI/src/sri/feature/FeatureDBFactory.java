/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri.feature;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import jfi.shape.Contour;
import jfi.shape.CurvatureFunction;
import jfi.shape.ImageMask;
import jfi.shape.fuzzy.FuzzyContour;
import jfi.shape.fuzzy.FuzzyContourFactory;

/**
 * Factory to create ContourFeature and FeatureDB
 * 
 * @author Luis Suárez Lloréns
 */
public class FeatureDBFactory {
    
    /**
     * Mode used to create ContourFeature based on curvature.
     */
    public static final int CURVATURE_MODE = 0;
    
    /**
     * Mode used to create ContourFeature based on curvacity.
     */
    public static final int CURVACITY_MODE = 1;
    
    /**
     * Default constructor.
     */
    public FeatureDBFactory(){
        
    }
    
    /**
     * Creates a FeatureDB with all the shapes from a folder.
     * 
     * @param folderPath path of the folder to classify
     * @param mode selected mode
     * @return FeatureDB of the folder
     */  
    public FeatureDB createFeatureDB(String folderPath, int mode){
        File folder = new File(folderPath);
        ArrayList<String> paths = new ArrayList<>();
        FeatureDB result = new FeatureDB();
        
        if(folder.isDirectory()){
            // create new filename filter
            FilenameFilter filter= new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".gif");
                }
            };
            
            String[] filesnames = folder.list(filter);
            
            for(String name: filesnames){
                String absolutePath = folder.getAbsolutePath()+"/"+name;
                result.add(createContourFeature(absolutePath,mode));
            }
            
            return result;
        }
        return null;
    }
    
    
    /**
     * Creates a ContourFeature from a image.
     * 
     * @param path path of the image
     * @param mode selected mode
     * @return ContourFeature of the image
     */
    public ContourFeature createContourFeature(String path, int mode){
        return createContourFeature(path,mode,100);
    }
    
    /**
     * Creates a ContourFeature from a image.
     * 
     * @param path path of the image
     * @param mode selected mode
     * @param size size of the feature
     * @return ContourFeature of the image
     */
    public ContourFeature createContourFeature(String path, int mode, int size){
        BufferedImage img = null;
        
        try {
            File f = new File(path);
            img = ImageIO.read(f);
        } catch (Exception ex) {
            //TODO: Excepcion
        }
        
        ImageMask mask = new ImageMask(img);
        Contour contour = new Contour(mask);
        
        
        ArrayList<Double> values = new ArrayList<>();
        
        if(mode == FeatureDBFactory.CURVATURE_MODE){
            CurvatureFunction curvature = contour.getCurvature();

            for(int i = 0; i < curvature.size(); i++){
                values.add(curvature.apply(i));                
            }
        }
        else if(mode == FeatureDBFactory.CURVACITY_MODE){
            FuzzyContour fuzzyContour= FuzzyContourFactory.getLinearityInstance(contour);
            
            for(int i = 0; i < fuzzyContour.size(); i++){
                values.add(1-fuzzyContour.membershipDegree(contour.get(i)));
            }
        }

        ContourFeature contourFeature = new ContourFeature(path,values);
        contourFeature.resizeFeature(size);
        
        contourFeature.normalize();
        contourFeature.normalizeOrder();
        
        return contourFeature;
    }
}
