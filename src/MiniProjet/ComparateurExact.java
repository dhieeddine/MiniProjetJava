package MiniProjet;

public class ComparateurExact implements ComparateurNoms {

	@Override
	public double comparer(EntiteNom nom1, EntiteNom nom2) {
		// TODO Auto-generated method stub
		if (nom1 == null || nom2 == null) {
            return 0.0;
        }
		if(nom1.equals(nom2)) {
			return 1;
		} 
		return 0;
	}

}
