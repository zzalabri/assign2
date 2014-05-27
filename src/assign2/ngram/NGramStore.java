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
	private static final String Key = "068cc746-31ff-4e41-ae83-a2d3712d3e68";
	
	/**
	 * Constractor 
	 */
	public NGramStore() {
		this.collection = new HashMap<String, NGramContainer>();
	}

	/**
	 * 
	 * (Silently) Add an ngram to the Map. If the <code>context</code> does not exist in the Map, 
	 * the entry is added.<br>
	 * If the <code>context</code> exists in the Map, then the associated ngram is 
	 * updated (and thus overwritten). 
	 * 
	 * @param ngram - ngram to be added 
	 */
	@Override
	public void addNGram(NGramContainer ngram) {
		collection.put(ngram.getContext(), ngram);
	}

	/**
	 * 
	 * (Silently) Remove an ngram from the Map. If the <code>context</code> does not exist in the Map, 
	 * the entry is not removed, but no status is returned. We guarantee that the entry no longer exists<br>
	 * If the <code>context</code> exists in the Map, then the associated ngram is removed. 
	 * 
	 * @param context - context string for ngram to be removed
	 */
	@Override
	public void removeNGram(String context) {
		collection.remove(context);
	}

	/**
	 * 
	 * Find the NGram associated with the context if it exists in the Map. 
	 * Return null if the context is not a key in the Map. 
	 * 
	 * @param context
	 * @return NGramContainer associated with the context or null 
	 */
	@Override
	public NGramContainer getNGram(String context) {
		if (collection.get(context) != null){
			return collection.get(context);
		}
		return null;
	}

	/**
	 * 
	 * Get the top maxResults ngrams returned from the service.  
	 * 
	 * @param context - the context for the ngram search 
	 * @param maxResults - the maximum number of 
	 * @return true and store the NGram in the Map if the service returns at least one result<br>
	 * return false and do not store the bare context if the service returns no predictions
	 * @throws NGramException if the service fails to connect or if the NGramContainer cannot be 
	 * created. 
	 */
	@Override
	public boolean getNGramsFromService(String context, int maxResults)
			throws NGramException {
		if (context == null || context.isEmpty())throw new NGramException("Invalid context");
		boolean emptyToken;
		String[] words = new String[maxResults];
		NgramServiceFactory factory = NgramServiceFactory.newInstance(SimpleNGramGenerator.Key);
		
		GenerationService service = factory.newGenerationService();
		if (service == null) throw new NGramException("Erorr in connection");
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
