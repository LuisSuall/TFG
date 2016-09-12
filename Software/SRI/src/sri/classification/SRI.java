/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri.classification;

import java.util.ArrayList;
import java.util.HashMap;
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
        ClassifierManager manager = new ClassifierManager();
        ClassificationDB db = manager.classifyFolder("/home/luis/TFG/Software/Ejemplos/");
        db.save("/home/luis/TFG/Software/Ejemplos/db.sridb");
    }
    
}
