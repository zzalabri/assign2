/**  
 * @title NGramNode.java  
 * @package assign2.ngram  
 * @author khaled  
 * @version V1.0  
 * created 22/05/2014  
 */
package assign2.ngram;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

/**
 * @author khaled
 *
 */
public class NGramNodeTest {
	NGramNode testNode;
	NGramNode testNode2;
	String context = "be or not to";
	String[] words = {"to", "be", "or", "not"};
	String[] predictions = {"be", "mention", "exceed", "say", "the"};
	Double[] probabilities = { 0.136059, 0.066563, 0.032759, 0.028824, 0.024524 };
	
	@Before 
	public void setUp() throws NGramException{		
		testNode = new NGramNode(context, predictions, probabilities);
	}
	
	// Tests for constructor 
	// ======= Probabilities
	
	@Test 
	public void TestGetProbabilities() {
		assertArrayEquals(probabilities, testNode.getProbabilities());
	}
		
	@Test 
	public void TestSetProbabilities() throws NGramException {
		Double[] testProbabilities = {0.232, 0.3234};
		testNode.setProbabilities(testProbabilities);
		assertArrayEquals(testProbabilities, testNode.getProbabilities());
	}
		
	@Test 
	(expected = NGramException.class)
	public void NullTestProbabilities() throws NGramException {
		Double[] testProbabilities = null;
		testNode.setProbabilities(testProbabilities);
	}
		
	@Test 
	(expected = NGramException.class)
	public void InvalidTestPredictions() throws NGramException {
		Double[] testProbabilities = {-2.0, 3.32};
		testNode.setProbabilities(testProbabilities);
	}
	
	// Tests for constructor 
	// ========= Predictions
	
	@Test 
	public void TestGetPredictions() {
		assertArrayEquals(predictions, testNode.getPredictions());
	}
		
	@Test 
	public void TestSetPredictions() throws NGramException {
		String[] testPredictions = {"test", "predictions"};
		testNode.setPredictions(testPredictions);
		assertArrayEquals(testPredictions, testNode.getPredictions());
	}
		
	@Test 
	(expected = NGramException.class)
	public void NullTestPredictions() throws NGramException {
		String[] testPredictions = null;
		testNode.setPredictions(testPredictions);
	}
		
	@Test 
	(expected = NGramException.class)
	public void EmptyTestPredictions() throws NGramException {
		String[] testPredictions = {};
		testNode.setPredictions(testPredictions);
	}
	
	// Tests for constructor 
	// ============ Context
	
	@Test 
	public void TestGetContext() {
		assertEquals("be or not to", testNode.getContext());	
	}
		
	@Test
	public void StringTestSetContextTest() throws NGramException {
		testNode.setContext("new context");
		assertEquals("new context", testNode.getContext());
	}
		
	@Test
	(expected = NGramException.class)
	public void NullTestSetContext() throws NGramException {
		String test = null;
		testNode.setContext(test);
		assertEquals("new context", testNode.getContext());
	}
		
	@Test
	(expected = NGramException.class)
	public void EmptyTestSetContextArray() throws NGramException {
		String[] stringArray = {};
		testNode.setContext(stringArray);
	}
		
	@Test
	(expected = NGramException.class)
	public void ArrayNullTestSetContext() throws NGramException {
		String[] stringArray = null;
		testNode.setContext(stringArray);
	}
	
	// Tests for constructor 
	// ==============  words
	
	@Test
	(expected=NGramException.class)
	public void NullTestWordsConstructor() throws NGramException {
		String[] testWord = null;
		testNode2 = new NGramNode(testWord, predictions, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void EmptyTestWordsConstructorContext() throws NGramException {
		String[] testWord = {};
		testNode2 = new NGramNode(testWord, predictions, probabilities);
	}


	
}
