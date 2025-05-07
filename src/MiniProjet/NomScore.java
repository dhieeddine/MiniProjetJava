package MiniProjet;

public class NomScore implements ResultatDeMatching {

	
	
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
	@Override
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
	@Override
	public int compareTo(ResultatDeMatching o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
