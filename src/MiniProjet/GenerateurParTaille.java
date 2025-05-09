package MiniProjet;



import java.util.*;


public class GenerateurParTaille implements GenerateurCandidats {
	int maxDistance = 0;

	public GenerateurParTaille(int maxDistance) {
		super();
		this.maxDistance = maxDistance;
	}

	@Override
	public List<CoupleDeNom> genererCandidats(List<EntiteNom> listeNom1, List<EntiteNom> listeNoms2) {
		// TODO Auto-generated method stub
		List<CoupleDeNom> listeCouples = new ArrayList<CoupleDeNom>();
		for( EntiteNom e1 : listeNom1) {
			for (EntiteNom e2 : listeNoms2) {
				if(Math.abs(e1.getNomPretraite().length()- e2.getNomPretraite().length())<= maxDistance) {
					listeCouples.add(new CoupleDeNom(e1,e2));
			    } 
			}
			
		}
		
		return listeCouples; 
	}

}
