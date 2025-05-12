package MiniProjet;

import java.util.*;

public class PretraiteurDeTRiNom implements Pretraiteur {

	@Override
	public List<EntiteNom> pretraiter(List<EntiteNom> list) {
		// TODO Auto-generated method stub
		
		for(EntiteNom e : list) {
		       String nomComplet = e.getNomPretraite();
		       
			
		        if (nomComplet == null || nomComplet.isBlank()) continue;
		       

		        String[] parties = nomComplet.trim().split("\\s+");
		        Arrays.sort(parties);

		        e.setNomPretraite(String.join(" ", parties));
		    
		}
		
		
		return list;
	}

}
