package MiniProjet;

public class CoupleDeNom {
	@Override
	public String toString() {
		return "CoupleDeNom [ " + nom1 + ",  " + nom2 + "]";
	}
	private EntiteNom nom1;
	private EntiteNom nom2;
	public CoupleDeNom(EntiteNom nom1, EntiteNom nom2) {
		super();
		this.nom1 = nom1;
		this.nom2 = nom2;
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
	

}
