package MiniProjet;

import java.util.List;

public class PretraiteurPhonetique implements Pretraiteur {

	@Override
	public List<EntiteNom> pretraiter(List<EntiteNom> list) {
		// TODO Auto-generated method stub
		for( EntiteNom e : list) {
			String word = e.getNomcomplet();
			 word = word.replaceAll("h", "");
		        word = word.replaceAll("e", "");
		        word = word.replaceAll("a", "");
		        word = word.replaceAll("i", "");
		        word = word.replaceAll("o", "");
		        word = word.replaceAll("u", "");
		        word = word.replaceAll("y", "");

		        // Remplacements phonétiques simples
		        word = word.replaceAll("ph", "f");
		        word = word.replaceAll("c", "k");
		        word = word.replaceAll("q", "k");
		        word = word.replaceAll("x", "ks");
		        word = word.replaceAll("z", "s");
		        e.setNomcomplet(word);
			
		}
		return list;
	}

}
