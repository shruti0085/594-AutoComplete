import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

public class TrieTest {
	
	
	@Test
	public void searchTest(){
		Trie prefixTrie = new Trie('h');
		String str = "hello";
		assertNull(prefixTrie.search(str));
		
	}
	
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
	
	@Test(expected =IllegalArgumentException.class)
	public void insertTest2() {
		Trie prefixTrie = new Trie('a');
		String str1 = "apple";
		String str2 = "axe";
		prefixTrie.insert(str1);
		prefixTrie.insert(str2);
		prefixTrie.insert(null);
		
	}
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
	}
	
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
	
	@Test
	public void listPredictTest2(){
		Trie prefixTrie = new Trie('a');
		ArrayList<String> predict = prefixTrie.listOfPredictions(new TrieNode('a'));	
		assertNull(predict);
	}
}
