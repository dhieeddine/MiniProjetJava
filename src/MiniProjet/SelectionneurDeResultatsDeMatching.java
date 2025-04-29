package MiniProjet;

import java.util.ArrayList;

public abstract class SelectionneurDeResultatsDeMatching {
	private double seuil;
	
	ArrayList<ResultatComparaison> selectionner(ArrayList<ResultatComparaison> liste ) {
		return null;
	}
	public SelectionneurDeResultatsDeMatching(double seuil) {
		super();
		this.seuil = seuil;
	}
	public double getSeuil() {
		return seuil;
	}
	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}
	

}
