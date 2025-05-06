package MiniProjet;

import java.util.List;
import java.util.ArrayList;
import java.text.Normalizer;
public class PretraiteurSansAccents implements Pretraiteur {

	@Override
	public List<EntiteNom> pretraiter(List<EntiteNom> list) {
		// TODO Auto-generated method stub
		if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
		for (EntiteNom nom : list) {
			String texte = nom.getNomcomplet();
			

	        // 1. Supprimer les accents (ex: é → e)
	        String sansAccents = Normalizer.normalize(texte, Normalizer.Form.NFD)
	            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

	        

	        // 3. Optionnel : enlever les espaces en trop
	        nom.setNomcomplet(sansAccents.trim().replaceAll(" +", " "));
		}
		return list;
	}

}
