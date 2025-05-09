package MiniProjet;

import java.util.Comparator;

public class ComparateurScore implements Comparator<CoupleNomsScore> {

    @Override
    public int compare(CoupleNomsScore a, CoupleNomsScore b) {
        // Tri décroissant : b avant a si b a un score plus élevé
        return Double.compare(b.getScore(), a.getScore());
    }
}