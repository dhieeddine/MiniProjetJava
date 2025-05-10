package MiniProjet;

import java.util.*;

public class GenerateurParTaille implements GenerateurCandidats {
   
	@Override
	public List<CoupleDeNom> genererCandidats(List<EntiteNom> listeNom1, List<EntiteNom> listeNoms2) {
		// TODO Auto-generated method stub
		
		
		Indexeur indexeur = new IndexeurHashMap() ;
		Map<Integer,List<EntiteNom>> map = (Map<Integer, List<EntiteNom>>) indexeur.indexer(listeNoms2);
		List<CoupleDeNom> couple = new ArrayList<CoupleDeNom>();
		for(EntiteNom e : listeNom1) {
			int taille = e.getNomPretraite().length();
			int d =(int) Math.round(taille *1.0);
			int f =(int) Math.round(taille*1.0);
			for(int i =d; i<=f;i++) {
				if(map.containsKey(i)) {
					for(EntiteNom nom : map.get(i)) {
						couple.add(new CoupleDeNom(e,nom));
					}
					
					
				}
			}
		}
		
		
		return couple;
	}

}
