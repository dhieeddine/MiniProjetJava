package MiniProjet;

public class ResultatComparaison {
	private EntitéNom entiténom;
	private double score;
	
	
	
	public EntitéNom getEntiténom() {
		return entiténom;
	}
	public void setEntiténom(EntitéNom entiténom) {
		this.entiténom = entiténom;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public ResultatComparaison(EntitéNom entiténom, double score) {
		super();
		this.entiténom = entiténom;
		this.score = score;
	}

}
