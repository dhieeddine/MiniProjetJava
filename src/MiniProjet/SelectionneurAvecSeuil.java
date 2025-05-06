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
	public <T extends ResultatDeMatching> List<T> selectionner(List<T> liste) {
	    if (liste == null || liste.isEmpty()) {
	        return new ArrayList<T>();
	    }

	    List<T> resultatsFiltres = new ArrayList<>();
	    for (T resultat : liste) {
	        if (resultat.getScore() >= seuil) {
	            resultatsFiltres.add(resultat);
	        }
	    }
	    return resultatsFiltres;
	}

}
