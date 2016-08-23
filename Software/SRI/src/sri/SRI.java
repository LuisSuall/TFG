/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri;

/**
 *
 * @author luis
 */
public class SRI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SynsetDictionary dict = new SynsetDictionary();
        dict.load();

        ClassifierManager cl = new ClassifierManager();
        ClassificationDB db = cl.classifyFolder("/home/luis/TFG/Software/Ejemplos/");
        double sum = 0;
        for(ImageClassification img:db){
            System.out.println(img.getImagePath());
            System.out.println(img.getBestClass());
            System.out.println(img.getProbabilityBestClass());
            System.out.println(dict.searchByIdx(img.getBestClass()).getConcept());
        }
        System.out.println("La suma es: "+Double.toString(sum));
    }
    
}
