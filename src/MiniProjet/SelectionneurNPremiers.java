package MiniProjet;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SelectionneurNPremiers implements SelectionneurDeResultatsDeMatching {
    private int nombreResultats;
 
    public SelectionneurNPremiers(int nombreResultats) {
        this.nombreResultats = nombreResultats;
    }

    @Override
    public  List<CoupleNomsScore> selectionner(List<CoupleNomsScore> liste) {
        if (liste == null || liste.isEmpty()) {
            return new ArrayList<>();
        }

        List<CoupleNomsScore> copie = new ArrayList<CoupleNomsScore>(liste);

        
        Collections.sort(copie, new ComparateurScore());

        int taille = Math.min(nombreResultats, copie.size());
        return new ArrayList<>(copie.subList(0, taille));
    }
    
}