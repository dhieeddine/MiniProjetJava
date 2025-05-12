package MiniProjet;

import java.util.*;

public class GenerateurAvecPrefix implements GenerateurCandidats {

	@Override
	public List<CoupleDeNom> genererCandidats(List<EntiteNom> listeNom1, List<EntiteNom> listeNoms2) {
		MoteurMatching moteur =  new MoteurMatching();
	    Map<String, List<EntiteNom>> prefixIndex = new HashMap<>();
	    int prefixLength = 3;
	    for (EntiteNom e : listeNoms2) {
	        String nom = e.getNomPretraite();
	        if (nom.length() >= prefixLength) {
	            String prefix = nom.substring(0, prefixLength);
	            if (!prefixIndex.containsKey(prefix)) {
	                prefixIndex.put(prefix, new ArrayList<>());
	            }
	            prefixIndex.get(prefix).add(e);
	        }
	    }

	    List<CoupleDeNom> couples = new ArrayList<>();
	    Set<String> addedPairs = new HashSet<>();
        int c =0;

	    for (EntiteNom e1 : listeNom1) {
	        String nom = e1.getNomPretraite();
	        if (nom.length() >= prefixLength) {
	            String prefix = nom.substring(0, prefixLength);
	            List<EntiteNom> candidats = prefixIndex.getOrDefault(prefix, Collections.emptyList());
	            for (EntiteNom e2 : candidats) {
	            	double score = moteur.comparateurNoms.comparer(e2.getNomPretraite(),e1.getNomPretraite());
	    			if(score>=0.95  ) { 
	    				if(e2.getId()!=e1.getId()) {
	    					System.out.printf("%d (%s , %s) score= %f\n",c,e1,e2, score);
	    				
	    				c++;
	    				}
	    			}
	            	
	                String key = e1.getNomPretraite() + "||" + e2.getNomPretraite();
	                if (!addedPairs.contains(key)) {
	                    couples.add(new CoupleDeNom(e1, e2));
	                    addedPairs.add(key);
	                }
	            }
	        }
	    }

	    return couples;
	}


}
