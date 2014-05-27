/**  
 * @title NGramNode.java  
 * @package assign2.ngram  
 * @author khaled - n8560081
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
	Integer MAXRESULTS = 5;
	
	@Before
	public void setUp() throws NGramException {
		tester = new NGramStore();
		tester.getNGramsFromService("be or not to", 5);
	}

	// Tests for Add NGram 
	@Test
	public void AddNewNGramNewContext() throws NGramException {
		NGramNode gram = new NGramNode(context, predictions, probabilities);
		tester.addNGram(gram);
		assertEquals(gram, tester.getNGram(context));
	}
	
	@Test
	public void AddNewNGramExistContext() throws NGramException {
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		tester.addNGram(ngram);
		assertEquals(ngram, tester.getNGram(context));
	}
	
	// Tests for Get NGram from service
	@Test
	public void GetNGramsFromServiceTest() throws NGramException {
		boolean test = tester.getNGramsFromService("be or not to", MAXRESULTS);
		assertEquals(true, test);
	}
	
	// Tests for Get NGram
	@Test
	public void GetNGramTest() throws NGramException {
		NGramContainer test = tester.getNGram(context);
		assertEquals(test.getContext(), context);
	}
	
	@Test
	public void GetNGramNotExistValueTest() throws NGramException {
		assertEquals(null, tester.getNGram("to be or"));
	}
	
	@Test
	public void GetNGramExistValueTest() throws NGramException {
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		tester.addNGram(ngram);
		assertEquals(ngram, tester.getNGram(context));
	}
	
	// Tests for Remove NGram
	@Test
	public void RemoveNGramTest() throws NGramException {
		NGramNode node = new NGramNode(context, predictions, probabilities);
		tester.addNGram(node);
		tester.removeNGram(context);
		assertEquals(node.getContext(), context);
	}
	
	// Tests for Remove NGram
	@Test
	public void NormalNGramFromServiceTest() throws NGramException {
		String str = "to be or";
		tester.getNGramsFromService(str, MAXRESULTS);
		assertEquals(tester.getNGramsFromService(context, MAXRESULTS), true);
	}
	
	@Test
	public void NoResultNGramFromService() throws NGramException {
		String context = "pppppppppp qqqqqqqqqq zzzzzzzzz";
		assertEquals(tester.getNGramsFromService(context, MAXRESULTS), false);
	}
	
	@Test(expected = NGramException.class)
	public void Test_GetNGramsFromService_cannotCreatNgram() throws NGramException {
		String str = "";
		tester.getNGramsFromService(str, MAXRESULTS);
	}
	
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
        
        System.out.println("Obje: "+ NumObjectClassFields + ", Interface: "+NumInterfaceFields+", Class: "+NumNGramStoreClassFields);
        
        assertTrue("obj + interface = class",(NumObjectClassFields+NumInterfaceFields)==NumNGramStoreClassFields);
        //assertTrue("obj",NumObjectClassFields=="obj");
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
