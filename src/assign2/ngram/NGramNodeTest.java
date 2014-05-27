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
		
//	@Test // need check 
//	public void ArrayTestSetContext() throws NGramException {
//		String[] stringArray = {"to", "go", "home"};
//		testNode.setContext(stringArray);
//		assertEquals("to go home", testNode.getContext());
//	}
		
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

	@Test
	(expected=NGramException.class)
	public void EmptyTestWordsConstructorContain() throws NGramException {
		String[] testWords = {"to", "be", ""};
		testNode2 = new NGramNode(testWords, predictions, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void EmptyTestWordsConstructorPredictions() throws NGramException {
		String[] testArray = {};
		testNode2 = new NGramNode(words, testArray, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void EmptyStringTestWordsConstructorPredictions() throws NGramException {
		String[] testPredictions = {"", ""};
		testNode2 = new NGramNode(words, testPredictions, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void NullTestWordsConstructorProbabilities() throws NGramException {
		probabilities = null;
		testNode2 = new NGramNode(words, predictions, probabilities);
	}
				
	@Test
	(expected=NGramException.class)
	public void SizeEmptyTestWordsConstructorProbabilities() throws NGramException {
		Double[] testDouble = {};
		testNode2 = new NGramNode(words, predictions, testDouble);
	}
		
	@Test
	(expected=NGramException.class)
	public void NegativeTestWordsConstructorProbabilitiesContains() throws NGramException {
		Double[] testDouble = {-0.456552};
		testNode2 = new NGramNode(words, predictions, testDouble);
	}
		
	@Test
	(expected=NGramException.class)
	public void ZeroTestWordsConstructorProbabilitiesContains() throws NGramException {
		Double[] testDouble = {0.0};
		testNode2 = new NGramNode(words, predictions, testDouble);
	}
		
	@Test
	(expected=NGramException.class)
	public void GreaterThanOneTestWordsConstructorProbabilitiesContains() throws NGramException {
		Double[] testDouble = {1.8, 2.9};
		testNode2 = new NGramNode(words, predictions, testDouble);
	}
	
	// Tests for constructor 
	// ===========  context
	
	@Test
	(expected=NGramException.class)
	public void EmptyTestConstructorContext() throws NGramException {
		testNode2 = new NGramNode("", predictions, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void NullTestConstructorContext() throws NGramException {
		context = null;
		testNode2 = new NGramNode(context, predictions, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void NullTestConstructorPredictions() throws NGramException {
		predictions = null;
		testNode2 = new NGramNode(context, predictions, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void EmptyTestConstructorPredictions() throws NGramException {
		String[] testArray = {};
		testNode2 = new NGramNode(context, testArray, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void EmptyStringTestConstructorPredictions() throws NGramException {
		String[] testPredictions = {"", ""};
		testNode2 = new NGramNode(context, testPredictions, probabilities);
	}
		
	@Test
	(expected=NGramException.class)
	public void NullTestConstructorProbabilities() throws NGramException {
		probabilities = null;
		testNode2 = new NGramNode(context, predictions, probabilities);
	}
				
	@Test
	(expected=NGramException.class)
	public void EmptyTestConstructorProbabilitiesSize() throws NGramException {
		Double[] testDouble = {};
		testNode2 = new NGramNode(context, predictions, testDouble);
	}
		
	@Test
	(expected=NGramException.class)
	public void NegativeTestConstructorProbabilitiesContains() throws NGramException {
		Double[] testDouble = {-0.456552};
		testNode2 = new NGramNode(context, predictions, testDouble);
	}
		
	@Test
	(expected=NGramException.class)
	public void ZeroTestConstructorProbabilitiesContains() throws NGramException {
		Double[] testDouble = {};
		testNode2 = new NGramNode(context, predictions, testDouble);
	}
		
	@Test
	(expected=NGramException.class)
	public void GreaterThanOneTestconstructorProbabilitiesContains() throws NGramException {
		Double[] testDouble = {1.5, 2.3};
		testNode2 = new NGramNode(context, predictions, testDouble);
	}
	//NGramNodeTests.java      
	 	/*
	   	 * Confirm that the API spec has not been violated through the
	   	 * addition of public fields, constructors or methods that were
	   	 * not requested
	   	 */
	   	@Test
	   	public void NoExtraPublicMethods() {
	   		//Extends Object, implements NGramContainer
	   		final int toStringCount = 1;
	   		final int NumObjectClassMethods = Array.getLength(Object.class.getMethods());
	   		final int NumInterfaceMethods = Array.getLength(NGramContainer.class.getMethods());
	   		final int NumNGramNodeClassMethods = Array.getLength(NGramNode.class.getMethods());
	   		assertTrue("obj:"+NumObjectClassMethods+":inter:"+NumInterfaceMethods+" - 1 (toString()) = class:"+NumNGramNodeClassMethods,
	   				(NumObjectClassMethods+NumInterfaceMethods-toStringCount)==NumNGramNodeClassMethods);
	   	}
	   	
	   	@Test 
	   	public void NoExtraPublicFields() {
	   	//Extends Object, implements NGramContainer
	   		final int NumObjectClassFields = Array.getLength(Object.class.getFields());
	   		final int NumInterfaceFields = Array.getLength(NGramContainer.class.getFields());
	   		final int NumNGramNodeClassFields = Array.getLength(NGramNode.class.getFields());
	   		assertTrue("obj + interface = class",(NumObjectClassFields+NumInterfaceFields)==NumNGramNodeClassFields);
	   	}
	   	
	   	@Test 
	   	public void NoExtraPublicConstructors() {
	   	//Extends Object, implements NGramContainer
	   		final int ExtraConsCount =1;
	   		final int NumObjectClassConstructors = Array.getLength(Object.class.getConstructors());
	   		final int NumInterfaceConstructors = Array.getLength(NGramContainer.class.getConstructors());
	   		final int NumNGramNodeClassConstructors = Array.getLength(NGramNode.class.getConstructors());
	   		assertTrue("obj:"+NumObjectClassConstructors+":inter:"+NumInterfaceConstructors+" 1 (extra) = class:"+NumNGramNodeClassConstructors,
	   				(NumObjectClassConstructors+NumInterfaceConstructors+ExtraConsCount)==NumNGramNodeClassConstructors);
	   	}
		

	       @Test
	       public void TOSTRING_ComplexObject() throws NGramException {
	     	   	  DecimalFormat df = new DecimalFormat(NGramContainer.DecFormat);
	    	   	  String test = "be or not to | be : 0.136059\n" + "be or not to | mention : 0.066563\n" + 
	    	   			  		"be or not to | exceed : 0.032759\n" + "be or not to | say : 0.028824\n" +
	    	   			  		"be or not to | the : 0.024524\n";
	    	   	  testNode.setContext("be or not to");
	    	   	  testNode.setPredictions(new String[]{"be","mention","exceed","say","the"});
	    	   	  testNode.setProbabilities(new Double[]{0.13605912332,0.066563234345,0.03275912314,0.028823899932,0.0245242343});
	    	   	  String str = testNode.toString(); 
	    	      assertEquals(test,str);
	       }

	
}
