package MiniProjet;

public class NomScore {
	private double score;
	private EntiteNom nom;
	public NomScore(double score, EntiteNom nom) {
		super();
		this.score = score;
		this.nom = nom;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public EntiteNom getNom() {
		return nom;
	}
	public void setNom(EntiteNom nom) {
		this.nom = nom;
	}

}
