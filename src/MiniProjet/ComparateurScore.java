package MiniProjet;

import java.util.Comparator;

public class ComparateurScore implements Comparator<CoupleNomsScore> {

    @Override
    public int compare(CoupleNomsScore a, CoupleNomsScore b) {
        return Double.compare(b.getScore(), a.getScore());
    }
}