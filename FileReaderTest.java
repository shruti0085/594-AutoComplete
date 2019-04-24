import java.io.FileNotFoundException;

import org.junit.Test;

public class FileReaderTest {
    
    @Test
    public void testMakeFrequencyTable() throws FileNotFoundException {
	FileReader fr = new FileReader("testfile.txt");
	System.out.println(fr.getWords());
    }
    
//    @Test
//    public void testMakeSuccessorTable() throws FileNotFoundException {
//	FileReader fr = new FileReader("successor.txt");
//	System.out.println(fr.getSuccessors());
//    }
}
