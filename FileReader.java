import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {

    private String filename;
    private HashMap <String, Integer> words;
    private HashMap<String, HashMap<String, Integer>> successors;

    public FileReader (String fn) throws FileNotFoundException {
	filename = fn;
	words = new HashMap <String, Integer>();
	successors = new HashMap<String, HashMap<String, Integer>> ();
	readFile();
    }

    public HashMap <String, Integer> getWords(){
	return words;
    }
    
    public HashMap <String, HashMap<String, Integer>> getSuccessors(){
	return successors;
    }


    private void readFile() throws FileNotFoundException {

	Scanner sc = new Scanner(new File(filename));

	String tok; //the token


	while (sc.hasNext()) {
	    tok = sc.next();
	    tok = tok.toLowerCase().trim().replaceAll("[^a-zA-Z0-9']", ""); //woohoo!
	    makeFrequencyTable(tok);
	}

	sc.close();

    }

    public void makeFrequencyTable (String word) {

	if (words.containsKey(word)) {
	    words.put(word, words.get(word) + 1);
	} else {
	    words.put(word, 1);
	}
    }

    public void makeSuccessorMap (String word, String previous) {

    }

}