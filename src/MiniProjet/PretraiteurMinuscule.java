package MiniProjet;

import java.util.*;

public class PretraiteurMinuscule implements Pretraiteur {

	@Override
	public List<EntiteNom> pretraiter(List<EntiteNom> list) {
		// TODO Auto-generated method stub
		for(EntiteNom nom  : list) {
			 nom.setNomcomplet(nom.getNomcomplet().toLowerCase());
		}
		return list; 
	
	}

	
}
