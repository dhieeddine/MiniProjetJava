package MiniProjet;

import java.util.List;
import java.util.ArrayList;

public class SelectionneurNPremiers implements SelectionneurDeResultatsDeMatching {
	private int nombreResultats;

	public SelectionneurNPremiers(int nombreResultats) {
		this.nombreResultats = nombreResultats;
	}

	public List<NomScore> selectionner(List<NomScore> liste) {
		List<NomScore> copie = new ArrayList<>(liste);
		for (NomScore ns : copie) {
	        System.out.println("Nom : " + ns.getNom() + ", Score : " + ns.getScore());
	    }
		copie.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
		int taille = Math.min(nombreResultats, copie.size());
	    List<NomScore> lesNPremiers = new ArrayList<>();

	    for (int i = 0; i < taille; i++) {
	    	lesNPremiers.add(copie.get(i));
	    }

	    return lesNPremiers;
		
	}

}
