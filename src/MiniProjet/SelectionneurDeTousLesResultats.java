package MiniProjet;

import java.util.List;

public class SelectionneurDeTousLesResultats implements SelectionneurDeResultatsDeMatching {

	@Override
	public <T extends ResultatDeMatching> List<T> selectionner(List<T> liste) {
		
		return liste;
	}

}
