/**  
 * @title NGramNode.java  
 * @package assign2.ngram  
 * @author khaled  
 * @version V1.0  
 * created 22/05/2014  
 */
package assign2.ngram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import assign2.examples.ngram.SimpleNGramGenerator;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.NgramServiceFactory;
import com.microsoft.research.webngram.service.GenerationService.TokenSet;


public class NGramStore implements NGramMap {
	
	private HashMap<String, NGramContainer> collection;
	public static final String Key = "068cc746-31ff-4e41-ae83-a2d3712d3e68"; 

	/**
	 * 
	 */
	public NGramStore() {
		this.collection = new HashMap<String, NGramContainer>();
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramMap#addNGram(assign2.ngram.NGramContainer)
	 */
	@Override
	public void addNGram(NGramContainer ngram) {
		collection.put(ngram.getContext(), ngram);
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramMap#removeNGram(java.lang.String)
	 */
	@Override
	public void removeNGram(String context) {
		collection.remove(context);
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramMap#getNGram(java.lang.String)
	 */
	@Override
	public NGramContainer getNGram(String context) {
		if (collection.get(context) != null){
			return collection.get(context);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramMap#getNGramsFromService(java.lang.String, int)
	 */
	@Override
	public boolean getNGramsFromService(String context, int maxResults)
			throws NGramException {
		if (context == null || context.isEmpty())throw new NGramException("Invalid context");
//		if (maxResults<=5){ throw new NGramException("Invalid maxResults");} 
		boolean emptyToken;
		String[] words = new String[maxResults];
		NgramServiceFactory factory = NgramServiceFactory.newInstance(SimpleNGramGenerator.Key);
		GenerationService service = factory.newGenerationService();
		
		TokenSet tokenSet = service.generate(Key, "bing-body/2013-12/5", context, 5, null);
		
		
		emptyToken= tokenSet.getWords().isEmpty();
		
		if(!emptyToken){
			tokenSet.getWords().toArray(words);
			
			List<Double> logProbs = tokenSet.getProbabilities();
			List<Double> probs = new ArrayList<Double>();
			
			for (Double x : logProbs) {
				probs.add(Math.pow(10.0,x));
			}
			
			Double[] probabilities = new Double[maxResults];
			probs.toArray(probabilities);	
			
			NGramNode ngram = new NGramNode(context, words, probabilities);
			addNGram(ngram);
			
			return true;
			
		}else{
			return false;
		}
	}
	
	public String toString(){
		String outputString = "";
		Iterator<NGramContainer> iterator = collection.values().iterator();
		while(iterator.hasNext()){
			outputString += iterator.next().toString() + "\n";
		}
		return outputString;
	}
	
}
