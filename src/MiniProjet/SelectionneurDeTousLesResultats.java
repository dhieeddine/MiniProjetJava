package MiniProjet;

import java.util.ArrayList;
import java.util.List;

public class SelectionneurDeTousLesResultats implements SelectionneurDeResultatsDeMatching {

	@Override
	public List<CoupleNomsScore> selectionner(List<CoupleNomsScore> liste) {
		 if (liste == null || liste.isEmpty()) {
		        return new ArrayList<CoupleNomsScore>();
		    }

		
		return liste;
	}

}
