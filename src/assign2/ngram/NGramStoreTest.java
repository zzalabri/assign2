/**
 * 
 */
package assign2.ngram;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author khaled
 *
 */
public class NGramStoreTest {
	NGramStore teststore;
	String context = "be or not to";
	String[] words = {"be", "or", "not", "to"};
	String[] predictions = {"be", "mention", "exceed", "say", "the"};
	Double[] probabilities = { 0.136059, 0.066563, 0.032759, 0.028824, 0.024524 };

	@Before
	public void setUp() throws NGramException {
		teststore = new NGramStore();
		teststore.getNGramsFromService("be or not to", 5);
	}

	@Test
	public void TestgetNGramsFromService() throws NGramException {
		boolean testTrue = teststore.getNGramsFromService("be or not to", 5);
		assertEquals(true, testTrue);
	}
	

//	@Test
//	public void getNGramsFromServiceReturnFalseTest() throws NGramException {
//		boolean testFalse = teststore.getNGramsFromService("" , 0);
//		assertEquals(false, testFalse);
//	}
	
//	@Test
//	public void addNGramTest() throws NGramException {
//		NGramNode node = new NGramNode(context, predictions, probabilities);
//		store.addNGram(node);
//		assertEquals();
//	}
	
	
	@Test
	public void TestGetNGram() throws NGramException {
		NGramContainer node = teststore.getNGram(context);
		assertEquals(node.getContext(), context);
	}
	
	@Test
	public void TestRemoveNGram() throws NGramException {
		NGramNode node = new NGramNode(context, predictions, probabilities);
		teststore.addNGram(node);
		teststore.removeNGram(context);
	}
	
}
