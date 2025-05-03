package MiniProjet;

import java.util.*;

public class ComparateurNGram implements ComparateurNoms {
	private int n;


	public ComparateurNGram(int n) {
		super();
		this.n = n;
	}
	@Override
	public double comparer(String s1, String s2) {
		// TODO Auto-generated method stub
		 Set<String> ngrams1 = getNGrams(s1, n);
	        Set<String> ngrams2 = getNGrams(s2, n);

	        Set<String> intersection = new HashSet<>(ngrams1);
	        intersection.retainAll(ngrams2);

	        Set<String> union = new HashSet<>(ngrams1);
	        union.addAll(ngrams2);

	        return (double) intersection.size() / union.size(); // Jaccard similarity
	}
	 public static Set<String> getNGrams(String word, int n) {
	        Set<String> ngrams = new HashSet<>();
	        word = word.toLowerCase();
	        for (int i = 0; i <= word.length() - n; i++) {
	            ngrams.add(word.substring(i, i + n));
	        }
	        return ngrams;
	   


}
}
