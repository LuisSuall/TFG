/**
 * Information about all synsets of the classifier
 *
 * @author Luis Suárez Lloréns
 */
package sri.classification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SynsetDictionary extends HashMap<String,String>{

    /**
     * Default path to load the words dictionary.
     */
    private static final String DEFAULT_WORDS_PATH = "../words.txt";
    /**
     * Default path to load the is-a dictionary.
     */
    private static final String DEFAULT_IS_A_PATH = "../is_a.txt";

    /**
     * Default constructor.
     */
    public SynsetDictionary() {
    }

    /**
     * Loads the default dictionary.
     */
    public void loadWordsDictionary() {
        this.load(DEFAULT_WORDS_PATH, '\t');
    }
    
    void loadIsADictionary() {
        this.clear();
        
        File f = new File(DEFAULT_IS_A_PATH);
        
        try (FileReader fis = new FileReader(f);BufferedReader reader = new BufferedReader(fis)) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> splitedLine = splitInfo(line,' ');
                this.put(splitedLine.get(1),splitedLine.get(0));
            }  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SynsetDictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SynsetDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads a dictionary from a file.
     * @param path path to the dictionary file
     */
    public void load(String path,char mark) {
        this.clear();
        
        File f = new File(path);
        
        try (FileReader fis = new FileReader(f);BufferedReader reader = new BufferedReader(fis)) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> splitedLine = splitInfo(line,mark);
                this.put(splitedLine.get(0),splitedLine.get(1));
            }  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SynsetDictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SynsetDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Splits the line into two strings, one with the first word an the other with the rest of the line.
     * @param line line to split
     * @return splited line
     */
    private ArrayList<String> splitInfo(String line,char mark){
        ArrayList<String> splitedLine = new ArrayList<>();
        
        splitedLine.add(line.substring(0, line.indexOf(mark)));
        splitedLine.add(line.substring(line.indexOf(mark)+1));
        
        return splitedLine;
    }
    
}
