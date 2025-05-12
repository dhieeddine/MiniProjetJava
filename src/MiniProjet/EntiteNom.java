package MiniProjet;

public class EntiteNom {
	
	private String Nomcomplet;
	private String id;
	private String nomPretraite;
	
	
	public String getNomPretraite() {
		return nomPretraite;
	}
	public void setNomPretraite(String nomPretraite) {
		this.nomPretraite = nomPretraite;
	}

	public EntiteNom(String nomcomplet, String id) {
		super();
		Nomcomplet = nomcomplet;
		this.id = id;
		this.nomPretraite = nomcomplet;
	}
	public String getNomcomplet() {
		return Nomcomplet;
	}
	@Override
	public String toString() {
		return "[ " + Nomcomplet + ", id= " + id + " ]";
	}
	public void setNomcomplet(String nomcomplet) {
		Nomcomplet = nomcomplet;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}