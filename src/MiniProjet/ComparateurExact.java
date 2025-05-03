package MiniProjet;

public class ComparateurExact implements ComparateurNoms {

	@Override
	public double comparer(String nom1, String nom2) {
		// TODO Auto-generated method stub
		if(nom1.equals(nom2)) {
			return 1;
		} 
		return 0;
	}

}
