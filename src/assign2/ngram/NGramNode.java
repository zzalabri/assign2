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
	
	private static final String DecFormat = "068cc746-31ff-4e41-ae83-a2d3712d3e68"; 
	
	/**
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
	
	private boolean stringContains(String[] words, String string) {
		for (String w : words){
			if(w == string){
				return true;
			}
		}
		return false;
	}
	
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

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramContainer#getContext()
	 */
	@Override
	public String getContext() {
		return this.context;
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramContainer#setContext(java.lang.String)
	 */
	@Override
	public void setContext(String context) throws NGramException {
		if (context == null || context == "") throw new NGramException("Invalid context");
		this.context = context; 
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramContainer#setContext(java.lang.String[])
	 */
	@Override
	public void setContext(String[] words) throws NGramException {
		if (words == null || words.length == 0 || stringContains(words, "") || stringContains(words, null))
			throw new NGramException("Invalid words");
		
		this.context = words.toString();
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramContainer#getPredictions()
	 */
	@Override
	public String[] getPredictions() {
		return this.predictions;
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramContainer#setPredictions(java.lang.String[])
	 */
	@Override
	public void setPredictions(String[] predictions) throws NGramException {
		if (predictions == null || predictions.length == 0 || stringContains(predictions, "") || stringContains(predictions, null))
			throw new NGramException("Invalid predictions");
		
		this.predictions = predictions;
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramContainer#getProbabilities()
	 */
	@Override
	public Double[] getProbabilities() {
		return this.probabilities;
	}

	/* (non-Javadoc)
	 * @see assign2.ngram.NGramContainer#setProbabilities(java.lang.Double[])
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
