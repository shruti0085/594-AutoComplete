import java.io.FileNotFoundException;

import org.junit.Test;

public class FileReaderTest {
    
    @Test
    public void testMakeFrequencyTable() throws FileNotFoundException {
	FileReader fr = new FileReader("testfile.txt");
	fr.makeFrequencyTable("file");
	System.out.println(fr.getWords());
	
    }
    
    @Test
    public void testMakeSuccessorMap() {
	
    }

}
