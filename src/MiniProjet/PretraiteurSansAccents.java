package MiniProjet;

import java.util.List;
import java.text.Normalizer;
public class PretraiteurSansAccents implements Pretraiteur {

	@Override
	public List<EntiteNom> pretraiter(List<EntiteNom> list) {
		// TODO Auto-generated method stub
		for (EntiteNom nom : list) {
			nom.setNomcomplet(Normalizer.normalize(nom.getNomcomplet(), Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));
		}
		return list;
	}

}
