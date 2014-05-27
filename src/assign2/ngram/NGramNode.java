/**  
 * @title NGramNode.java  
 * @package assign2.ngram  
 * @author khaled  
 * @version V1.0  
 * created 22/05/2014  
 */

package assign2.ngram;

public class NGramNode implements NGramContainer {
	
	private String context;
	private String[] predictions;
	private Double[] probabilities;
	
	/**
	 * 
	 * Format for output of probabilities 
	 */  
	
	private static final String DecFormat = "068cc746-31ff-4e41-ae83-a2d3712d3e68"; 
	
	/**
	 * 
	 * Constroctor 
	 * 
	 * 
	 */
	public NGramNode(String[] words, String[] predictions, Double[] probabilities) throws NGramException{
		if (words == null || words.length == 0 || stringContains(words, "") || stringContains(words, null)) 
			throw new NGramException("Invalid words");
		
		if (predictions == null || predictions.length == 0 || stringContains(predictions, "") || stringContains(predictions, null)) 
			throw new NGramException("Invalid predictions");
		
		if (probabilities == null || invalidNumbers(probabilities)) 
			throw new NGramException("Invalid probabilities");
		
		if(predictions.length != probabilities.length)
			throw new NGramException("Number of probabilities are not the same as number of predictions");
		
		
		this.context = words.toString();
		this.predictions = predictions;
		this.probabilities = probabilities; 
	}
	
	/**
	 * 
	 * Simple methode check text on string array  
	 * 
	 * @param words - array of string
	 * @param string - the text which would like looking for
	 * @return boolean - true : if found it - false: if not found it 
	 */
	private boolean stringContains(String[] words, String string) {
		for (String w : words){
			if(w == string){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * Simple methode invalid number for double array  
	 * 
	 * @param probabilities2 - array probabilities
	 * @return boolean - true : if found it - false: if not found it 
	 */
	private boolean invalidNumbers(Double[] probabilities2) {
		for (Double p : probabilities2){
			if (p <= 0 || p > 1){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Constroctor 
	 * 
	 * 
	 */
	public NGramNode(String context, String[] predictions, Double[] probabilities) throws NGramException {
		if (context == null || context == "") 
			throw new NGramException("Invalid context");
		
		if (predictions == null || predictions.length == 0 || stringContains(predictions, "") || stringContains(predictions, null))
			throw new NGramException("Invalid predictions");
		
		if (probabilities == null || invalidNumbers(probabilities)) 
			throw new NGramException("Invalid probabilities");
		
		if(predictions.length != probabilities.length)
			throw new NGramException("Number of probabilities are not the same as number of predictions");
		
		this.context = context;
		this.predictions = predictions;
		this.probabilities = probabilities;
	}

	/**
	 * 
	 * Simple getter method for the context string
	 * 
	 * @return String containing context phrase for predictions
	 */
	@Override
	public String getContext() {
		return this.context;
	}

	/**
	 * 
	 * Simple setter method for the context string
	 * 
	 * @param context - single String containing context phrase for predictions
	 * @throws NGramException if <code>context</code> is null or empty
	 */
	@Override
	public void setContext(String context) throws NGramException {
		if (context == null || context == "") throw new NGramException("Invalid context");
		this.context = context; 
	}

	/**
	 * 
	 * Simple setter method for the context string from multiple words
	 * 
	 * @param words - array of words in order that make up the context
	 * @throws NGramException if <code>words</code> is null or empty or contains at least one empty or null string
	 */
	@Override
	public void setContext(String[] words) throws NGramException {
		if (words == null || words.length == 0 || stringContains(words, "") || stringContains(words, null))
			throw new NGramException("Invalid words");
		
		this.context = words.toString();
	}

	/**
	 * 
	 * Simple getter method for the prediction strings
	 * 
	 * @return array of alternative next words in the phrase as predicted by the model
	 */
	@Override
	public String[] getPredictions() {
		return this.predictions;
	}

	/**
	 * 
	 * Simple setter method for the predictions string array
	 * 
	 * @param predictions - next word in the phrase as predicted by the model
	 * @throws NGramException if <code>predictions</code> is null or empty or contains at least one empty or null string
	 */
	@Override
	public void setPredictions(String[] predictions) throws NGramException {
		if (predictions == null || predictions.length == 0 || stringContains(predictions, "") || stringContains(predictions, null))
			throw new NGramException("Invalid predictions");
		
		this.predictions = predictions;
	}

	/**
	 * 
	 * Simple getter method for the probabilities
	 * 
	 * @return array of probabilities of context>prediction w.r.t. model
	 */
	@Override
	public Double[] getProbabilities() {
		return this.probabilities;
	}

	/**
	 * 
	 * Simple setter method for the probabilities 
	 * 
	 * @param probabilities - array of probabilities of context>prediction w.r.t. model
	 * @throws NGramException if <code>probabilities</code> null or contains at least one  entry which is null , zero, negative or greater than 1.0
	 */
	@Override
	public void setProbabilities(Double[] probabilities) throws NGramException {
		if (probabilities == null || invalidNumbers(probabilities)) throw new NGramException("Invalid probabilities");
		this.probabilities = probabilities;
	}
	
	public String toString(){
		String outputString = ""; 
		
		for (Integer counter = 0 ; counter < predictions.length ; counter++){
			outputString += context + " | " + predictions[counter] + " : " + probabilities[counter] + "\n";
		}
		return outputString;
	}
	

}
