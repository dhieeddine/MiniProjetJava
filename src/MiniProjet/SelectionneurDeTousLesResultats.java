package MiniProjet;

import java.util.ArrayList;
import java.util.List;

public class SelectionneurDeTousLesResultats implements SelectionneurDeResultatsDeMatching {

	@Override
	public <T extends ResultatDeMatching> List<T> selectionner(List<T> liste) {
		 if (liste == null || liste.isEmpty()) {
		        return new ArrayList<T>();
		    }

		
		return liste;
	}

}
