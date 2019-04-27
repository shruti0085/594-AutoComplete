import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

public class FileReaderTest {
    
    @Test //the string: This is a test file. Repeat some words: test. Test. test file file file! it's so so cool
    public void testMakeFrequencyTable() throws FileNotFoundException {
	FileReader fr = new FileReader("testfile.txt");
	assertEquals("1", fr.getWords().get("this").toString());
	assertEquals("1", fr.getWords().get("is").toString());
	assertEquals("1", fr.getWords().get("a").toString());
	assertEquals("4", fr.getWords().get("test").toString());
	assertEquals("4", fr.getWords().get("file").toString());
	assertEquals("1", fr.getWords().get("repeat").toString());
	assertEquals("1", fr.getWords().get("some").toString());
	assertEquals("1", fr.getWords().get("words").toString());
	assertEquals("1", fr.getWords().get("it's").toString());
	assertEquals("2", fr.getWords().get("so").toString());
	assertEquals("1", fr.getWords().get("cool").toString());
	
    }
    
    @Test //the string: Happy new year! Happy new day? Happy new year, friend! Brand new car.
    public void testMakeSuccessorTable() throws FileNotFoundException {
	FileReader fr = new FileReader("successor.txt");
	assertEquals("{new=3}", fr.getSuccessors().get("happy").toString());
	assertEquals("{year=2, car=1, day=1}", fr.getSuccessors().get("new").toString());
	assertEquals("{friend=1}", fr.getSuccessors().get("year").toString());
	assertEquals("{new=1}", fr.getSuccessors().get("brand").toString());
    }
}
