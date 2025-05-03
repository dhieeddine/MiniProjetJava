package MiniProjet;

public class CoupleNomsScore {
	@Override
	public String toString() {
		return "CoupleNomsScore [nom1= " + nom1 + ", nom2= " + nom2 + ", score= " + score + "]";
	}
	private EntiteNom nom1;
	private EntiteNom nom2;
	private double score;
	public CoupleNomsScore(EntiteNom nom1, EntiteNom nom2, double score) {
		super();
		this.nom1 = nom1;
		this.nom2 = nom2;
		this.score = score;
	}
	public EntiteNom getNom1() {
		return nom1;
	}
	public void setNom1(EntiteNom nom1) {
		this.nom1 = nom1;
	}
	public EntiteNom getNom2() {
		return nom2;
	}
	public void setNom2(EntiteNom nom2) {
		this.nom2 = nom2;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	

}
