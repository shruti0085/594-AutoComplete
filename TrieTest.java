import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

public class TrieTest {
	@Test
	public void searchTest{
		Trie tr = new Trie();
		String str = "hello";
		assertNull(tr.search(str));
		
	}
	
	@Test
	public void insertTest{
		Trie tr = new Trie();
		String str = "apple";
		String s = "axe";
		String sz = "app";
		tr.insert(str);
		//how to test insert? boolean
		
		
	}
	@Test
	public void searchTest2{
		Trie tr = new Trie();
		String z = "apple";
		String s = "axe";
		String sz = "app";
		tr.insert(z);
		tr.insert(s);
		tr.insert(sz);
		assertNotNull(tr.search(sz));
		assertNull(tr.search("an"));
	}
	
	@Test
	public void listPredictTest() {
		Trie tr = new Trie();
		String z = "apple";
		String s = "axe";
		String sz = "app";
		tr.insert(z);
		tr.insert(s);
		tr.insert(sz);
		TrieNode t = tr.search("a");
		assertEquals(tr.listOfPredictions(t).size(), 3);
	}
}
