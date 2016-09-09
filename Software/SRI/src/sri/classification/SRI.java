/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri.classification;

import sri.feature.ContourFeature;
import sri.feature.FeatureDB;
import sri.feature.FeatureDBFactory;

/**
 *
 * @author luis
 */
public class SRI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FeatureDBFactory factory = new FeatureDBFactory();
        FeatureDB db = factory.createFeatureDB("/home/luis/MPEG7dataset/original/", FeatureDBFactory.CURVATURE_MODE);
        
        for(ContourFeature cf:db){
            System.out.println(cf.getPath());
            System.out.println(cf.size());
        }
        
        db.save("/home/luis/MPEG7dataset/original/aa.featdb");
    }
    
}
