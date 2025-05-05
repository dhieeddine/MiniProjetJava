package MiniProjet;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SelectionneurNPremiers implements SelectionneurDeResultatsDeMatching {
    private int nombreResultats;

    public SelectionneurNPremiers(int nombreResultats) {
        this.nombreResultats = nombreResultats;
    }

    @Override
    public <T extends ResultatDeMatching> List<T> selectionner(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            return new ArrayList<>();
        }

        List<T> copie = new ArrayList<>(liste);

        
        Collections.sort(copie, new Comparator<ResultatDeMatching>() {
            @Override
            public int compare(ResultatDeMatching a, ResultatDeMatching b) {
                return Double.compare(b.getScore(), a.getScore());
            }
        });

        int taille = Math.min(nombreResultats, copie.size());
        return new ArrayList<>(copie.subList(0, taille));
    }
}