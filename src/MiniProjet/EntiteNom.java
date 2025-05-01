package MiniProjet;

public class EntitéNom {
	
	private String Nomcomplet;
	private int id;
	public EntitéNom(String nomcomplet, int id) {
		super();
		Nomcomplet = nomcomplet;
		this.id = id;
	}
	public String getNomcomplet() {
		return Nomcomplet;
	}
	public void setNomcomplet(String nomcomplet) {
		Nomcomplet = nomcomplet;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
