package MiniProjet;

import java.util.ArrayList;
import java.util.List;

public class GenerateurDeTousLesCouples implements GenerateurCandidats {

	@Override
	public List<CoupleDeNom> genererCandidats(List<EntiteNom> listeNom1, List<EntiteNom> listeNoms2) {
		// TODO Auto-generated method stub
		List<CoupleDeNom> listeCouples = new ArrayList<CoupleDeNom>();
		for( EntiteNom e1 : listeNom1) {
			for (EntiteNom e2 : listeNoms2) {
					listeCouples.add(new CoupleDeNom(e1,e2));
			}
			
		}
		
		return listeCouples;
	}

}
