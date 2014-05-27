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

import org.junit.Before;
import org.junit.Test;


public class NGramStoreTest {
	NGramStore tester;
	String context = "be or not to";
	String[] words = {"be", "or", "not", "to"};
	String[] predictions = {"be", "mention", "exceed", "say", "the"};
	Double[] probabilities = { 0.136059, 0.066563, 0.032759, 0.028824, 0.024524 };
	private int MAXRESULTS = 5;
	
	@Before
	public void setUp() throws NGramException {
		tester = new NGramStore();
		tester.getNGramsFromService("be or not to", 5);
	}

	@Test
	public void TestgetNGramsFromService() throws NGramException {
		boolean testTrue = tester.getNGramsFromService("be or not to", 5);
		assertEquals(true, testTrue);
	}
	

//	@Test
//	public void getNGramsFromServiceReturnFalseTest() throws NGramException {
//		boolean testFalse = teststore.getNGramsFromService("" , 0);
//		assertEquals(false, testFalse);
//	}
	
	@Test
	public void Test_addNGram_NewContext() throws NGramException {
		NGramNode gram = new NGramNode(context, predictions, probabilities);
		tester.addNGram(gram);
		assertEquals(gram, tester.getNGram(context));
	}
	
	
	@Test
	public void TestGetNGram() throws NGramException {
		NGramContainer node = tester.getNGram(context);
		assertEquals(node.getContext(), context);
	}
	@Test
	public void Test_addNGram_ExistContext() throws NGramException {

//		String[] words = { "my", "book", "is" };
//		String context = "my book is";
//		String[] predictions = { "to", "or", "not" };
//		// String[] predictions = { "to", "be", "or" };
//		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		tester.addNGram(ngram);//it exist, so updated 
		assertEquals(ngram, tester.getNGram(context));
	}
	
	@Test
	public void TestRemoveNGram() throws NGramException {
		NGramNode node = new NGramNode(context, predictions, probabilities);
		tester.addNGram(node);
		tester.removeNGram(context);
		assertEquals(node.getContext(), context);
	}
	
	@Test
	public void Test_GetNGram_notExist() throws NGramException {
		
		assertEquals(null, tester.getNGram("to be or"));
	}
	@Test
	public void Test_GetNGram_exist() throws NGramException {
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		tester.addNGram(ngram);
		assertEquals(ngram, tester.getNGram(context));
	}
	@Test
	public void Test_GetNGramsFromService_Normal() throws NGramException {
		String str = "to be or";
		tester.getNGramsFromService(str, MAXRESULTS);
		assertEquals(tester.getNGramsFromService(context, MAXRESULTS), true);
	}
	@Test
	public void Test_GetNGramsFromService_NoResult() throws NGramException {
		String context = "pppppppppp qqqqqqqqqq zzzzzzzzz";
		assertEquals(tester.getNGramsFromService(context, MAXRESULTS), false);
	}
	@Test(expected = NGramException.class)
	public void Test_GetNGramsFromService_cannotCreatNgram() throws NGramException {
		String str = "";
		tester.getNGramsFromService(str, MAXRESULTS);
	}
	
	//NgramStoreTests.java

  	/*
   	 * Confirm that the API spec has not been violated through the
   	 * addition of public fields, constructors or methods that were
   	 * not requested
   	 */
   	@Test
   	public void NoExtraPublicMethods() {
   		//Extends Object, implements NGramMap
   		final int toStringCount = 1;
   		final int NumObjectClassMethods = Array.getLength(Object.class.getMethods());
   		final int NumInterfaceMethods = Array.getLength(NGramMap.class.getMethods());
   		final int NumNGramStoreClassMethods = Array.getLength(NGramStore.class.getMethods());
   		assertTrue("obj:"+NumObjectClassMethods+":inter:"+NumInterfaceMethods+" - 1 (toString()) = class:"+NumNGramStoreClassMethods,
   				(NumObjectClassMethods+NumInterfaceMethods-toStringCount)==NumNGramStoreClassMethods);
   	}
   	
   	@Test 
   	public void NoExtraPublicFields() {
   	//Extends Object, implements NGramMap
   		final int NumObjectClassFields = Array.getLength(Object.class.getFields());
   		final int NumInterfaceFields = Array.getLength(NGramMap.class.getFields());
   		final int NumNGramStoreClassFields = Array.getLength(NGramStore.class.getFields());
   		assertTrue("obj + interface = class",(NumObjectClassFields+NumInterfaceFields)==NumNGramStoreClassFields);
   	}
   	
   	@Test 
   	public void NoExtraPublicConstructors() {
   	//Extends Object, implements NGramMap
   		final int NumObjectClassConstructors = Array.getLength(Object.class.getConstructors());
   		final int NumInterfaceConstructors = Array.getLength(NGramMap.class.getConstructors());
   		final int NumNGramStoreClassConstructors = Array.getLength(NGramStore.class.getConstructors());
   		assertTrue("obj:"+NumObjectClassConstructors+":inter:"+NumInterfaceConstructors+" = class:"+NumNGramStoreClassConstructors,
   				(NumObjectClassConstructors+NumInterfaceConstructors)==NumNGramStoreClassConstructors);
   	}

	
}
