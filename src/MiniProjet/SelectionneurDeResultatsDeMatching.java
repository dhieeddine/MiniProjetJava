package MiniProjet;

import java.util.List;

public interface SelectionneurDeResultatsDeMatching {
	<T extends ResultatDeMatching> List<T> selectionner(List<T> liste);
	
	
}
