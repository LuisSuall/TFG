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
        ClassifierManager cl = new ClassifierManager();
        ImageClassification img = cl.classifyImage("/home/luis/Caffe/caffe/examples/images/cat.jpg");
        double sum = 0;
        for(double value:img){
            System.out.println(value);
            sum +=value;
        }
        System.out.println("La suma es: "+Double.toString(sum));
    }
    
}
