package MiniProjet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexeurHashMap implements Indexeur {

	@Override 
	public Map<Integer, List<EntiteNom>> indexer(List<EntiteNom> noms) {
		 Map<Integer, List<EntiteNom>> index = new HashMap<Integer, List<EntiteNom>>();

	        for (EntiteNom nom : noms) {
	            if (nom == null) continue;

	            int taille = nom.getNomPretraite().length();

	            if (!index.containsKey(taille)) {
	                index.put(taille, new ArrayList<>());
	            }

	            index.get(taille).add(nom);
	        }

	        return index;
}
}
	
