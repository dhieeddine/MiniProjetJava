package MiniProjet;

public class NomScore {

	
	
	private EntiteNom nom;
	private double score;
	
	
	@Override
	public String toString() {
		return "NomScore ["+ nom + ", score=" + score + "]";
	}
	public NomScore(EntiteNom nom, double score) {
		super();
		this.nom = nom;
		this.score = score;
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
