package MiniProjet;

import java.util.HashSet;
import java.util.Set;

public class ComparateurNGram implements ComparateurNoms {
    private final int n;

    public ComparateurNGram(int n) {
        this.n = n;
    }

    @Override
    public double comparer(String nom1, String nom2) {
        if (nom1 == null || nom2 == null || nom1.isEmpty() || nom2.isEmpty()) {
            return 0.0;
        }

        Set<String> ngrams1 = generateNGrams(nom1, n);
        Set<String> ngrams2 = generateNGrams(nom2, n);

        Set<String> intersection = new HashSet<>(ngrams1);
        intersection.retainAll(ngrams2);

        Set<String> union = new HashSet<>(ngrams1);
        union.addAll(ngrams2);

        double score = (2.0 * intersection.size()) / (ngrams1.size() + ngrams2.size());
        return Math.round(score * 100.0) / 100.0; 
    } 

    private Set<String> generateNGrams(String str, int n) {
        Set<String> ngrams = new HashSet<>();
        for (int i = 0; i <= str.length() - n; i++) {
            ngrams.add(str.substring(i, i + n));
        }
        return ngrams;
    }
}