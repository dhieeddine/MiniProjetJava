package MiniProjet;

import java.util.*;

public class GenerateurAvecPrefix implements GenerateurCandidats {

	@Override
	public List<CoupleDeNom> genererCandidats(List<EntiteNom> listeNom1, List<EntiteNom> listeNoms2) {
	    // Indexer listeNoms2 par préfixe
	    Map<String, List<EntiteNom>> prefixIndex = new HashMap<>();
	    int prefixLength = 3; // Choisissez la taille du préfixe à comparer

	    for (EntiteNom e : listeNoms2) {
	        String nom = e.getNomPretraite();
	        if (nom.length() >= prefixLength) {
	            String prefix = nom.substring(0, prefixLength).toLowerCase();
	            prefixIndex.computeIfAbsent(prefix, k -> new ArrayList<>()).add(e);
	        }
	    }

	    List<CoupleDeNom> couples = new ArrayList<>();
	    Set<String> addedPairs = new HashSet<>();

	    for (EntiteNom e1 : listeNom1) {
	        String nom = e1.getNomPretraite();
	        if (nom.length() >= prefixLength) {
	            String prefix = nom.substring(0, prefixLength).toLowerCase();
	            List<EntiteNom> candidats = prefixIndex.getOrDefault(prefix, Collections.emptyList());

	            for (EntiteNom e2 : candidats) {
	                String key = e1.getNomPretraite() + "||" + e2.getNomPretraite();
	                if (!addedPairs.contains(key)) {
	                    couples.add(new CoupleDeNom(e1, e2));
	                    addedPairs.add(key);
	                }
	            }
	        }
	    }
	    addedPairs=null;
	    prefixIndex=null;
	    
	    return couples;
	}


}
