import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;
/**
 * Test class to test Trie class
 * @author sinha
 *
 */
public class TrieTest {
	/**
	 * test for search method when Trie does not have any children
	 */
	
	@Test
	public void searchTest(){
		Trie prefixTrie = new Trie('h');
		String str = "hello";
		assertNull(prefixTrie.search(str));
		
	}
	/**
	 * Test to check insert function
	 */
	@Test
	public void insertTest(){
		Trie prefixTrie = new Trie('a');
		String str1 = "apple";
		String str2 = "axe";
		String str3 = "app";
		String str4 = "apply";
		prefixTrie.insert(str1);
		prefixTrie.insert(str3);
		prefixTrie.insert(str4);
		assertNotNull(prefixTrie.search("appl"));
		
		
	}
	/**
	 * Test to check insert function when string is null
	 */
	@Test(expected =IllegalArgumentException.class)
	public void insertTest2() {
		Trie prefixTrie = new Trie('a');
		String str1 = "apple";
		String str2 = "axe";
		prefixTrie.insert(str1);
		prefixTrie.insert(str2);
		prefixTrie.insert(null);
		
		
	}
	
	/**
	 * Test to check insert function when string is empty
	 */
	@Test(expected =IllegalArgumentException.class)
	public void insertTest3() {
		Trie prefixTrie = new Trie('a');
		String str1 = "apple";
		String str2 = "axe";
		prefixTrie.insert(str1);
		prefixTrie.insert(str2);
		prefixTrie.insert("");
		
		
	}
	/**
	 * Test to check search function after adding valid words to the Trie
	 */
	@Test
	public void searchTest2(){
		Trie prefixTrie = new Trie('a');
		String str1 = "apple";
		String str2 = "axe";
		String str3 = "app";
		prefixTrie.insert(str1);
		prefixTrie.insert(str2);
		prefixTrie.insert(str3);
		assertNotNull(prefixTrie.search(str2));
		assertNull(prefixTrie.search("an"));
		assertNull(prefixTrie.search(null));
		assertNull(prefixTrie.search(""));
	}
	
	/**
	 * test to check ListOfPrediction function to get all words by traversing the 
	 * trie from given node
	 */
	@Test
	public void listPredictTest(){
		Trie prefixTrie = new Trie('a');
		String str1 = "apple";
		String str2 = "axe";
		String str3 = "app";
		prefixTrie.insert(str1);
		prefixTrie.insert(str2);
		prefixTrie.insert(str3);
		TrieNode prefixTrieNode = prefixTrie.search("a");
		ArrayList<String> predict = prefixTrie.listOfPredictions(prefixTrieNode);
		assertEquals(3,prefixTrie.listOfPredictions(prefixTrieNode).size());
	}
	/**
	 * test to check listOfPrediction function returns null when
	 *  no words can be found by traversing the Trie from given node
	 *  or when node is null
	 */
	@Test
	public void listPredictTest2(){
		Trie prefixTrie = new Trie('a');
		ArrayList<String> predict = prefixTrie.listOfPredictions(new TrieNode('a'));	
		assertNull(predict);
		assertNull(prefixTrie.listOfPredictions(null));
	}
}
