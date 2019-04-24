import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;



public class TrieNodeTest {
	
	@Test
	public void valueTest(){
		TrieNode tr = new TrieNode();
		tr.setValue('a');
		assertEquals('a',tr.getValue());
	}
	
	@Test
	public void lastNodeTest(){
		TrieNode tr = new TrieNode('a');
		assertFalse(tr.isLastNode());
		tr.setLastNode(true);
		assertTrue(tr.isLastNode());
		
		
	}
	
	@Test
	public void childrenTest(){
		TrieNode tr = new TrieNode('a');
		HashMap<Character, TrieNode> children = new HashMap<>();
		TrieNode te = new TrieNode('b');
		children.put('b', te);
		tr.setChildren(children);
		assertEquals(1, tr.getChildren().size());
	}
	

}
