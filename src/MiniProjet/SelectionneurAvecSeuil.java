package MiniProjet;

import java.util.List;
import java.util.ArrayList;
public class SelectionneurAvecSeuil implements SelectionneurDeResultatsDeMatching {
	private double seuil;

	public double getSeuil() {
		return seuil;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	public SelectionneurAvecSeuil(double seuil) {
		super();
		this.seuil = seuil;
	}

	@Override
	public  List<CoupleNomsScore> selectionner(List<CoupleNomsScore> liste) {
	    if (liste == null || liste.isEmpty()) {
	        return new ArrayList<CoupleNomsScore>();
	    }

	    List<CoupleNomsScore> resultatsFiltres = new ArrayList<CoupleNomsScore>();
	    for (CoupleNomsScore resultat : liste) {
	        if (resultat.score() >= this.seuil) {
	            resultatsFiltres.add(resultat); 
	        }
	    }
	    return resultatsFiltres; 
	}

}
