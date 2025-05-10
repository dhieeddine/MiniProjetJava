package MiniProjet;

import java.util.List;

public class PretraiteurSansCaracteresSpeciaux implements Pretraiteur {

	@Override
	public List<EntiteNom> pretraiter(List<EntiteNom> list) {
		// TODO Auto-generated method stub
		for(EntiteNom nom : list) {
			nom.setNomPretraite(nom.getNomPretraite().replaceAll("[^\\p{L} ]", "").trim().replaceAll(" +", " "));
		   		}
		return list;
	}

}
