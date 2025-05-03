package MiniProjet;

import java.util.*;

public class GenerateurBasic implements GenerateurCandidats {
	int rand=0;
	
	
	public GenerateurBasic(int rand) {
		super();
		this.rand = rand;
	}

	@Override
	public List<CoupleDeNom> genererCandidats(List<EntiteNom> listeNoms1,List<EntiteNom> listeNoms2) {
		// TODO Auto-generated method stub
		List<CoupleDeNom> listCouples = new ArrayList<CoupleDeNom>();
		Random random = new Random();
		
	    for(int i=0;i< rand; i++) {
	    	listCouples.add(new CoupleDeNom(listeNoms2.get(random.nextInt(listeNoms2.size())) , listeNoms1.get(random.nextInt(listeNoms1.size()))));
	    }
		
		return listCouples;
	}


	

}
