package MiniProjet;

public class EntiteNom {
	
	private String Nomcomplet;
	private String id;
	public EntiteNom(String nomcomplet, String id) {
		super();
		Nomcomplet = nomcomplet;
		this.id = id;
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
