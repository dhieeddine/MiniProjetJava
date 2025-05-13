package MiniProjet;
import java.util.*;
public class ComparateurLevenstein implements ComparateurNoms {

	@Override
	public double comparer(EntiteNom s1, EntiteNom s2) {
		  String nom1=s1.getNomPretraite();
		    String nom2=s2.getNomPretraite();
		    if (nom1 == null || nom2 == null || nom1.isEmpty() || nom2.isEmpty()) {
		        return 0.0;
		    }

		   
		    

		    int distance = distanceLevenshtein(nom1, nom2);
		    int maxLen = Math.max(nom1.length(), nom2.length());

		    if (maxLen == 0) return 1.0; // cas particulier : deux chaînes vides

		    return 1.0 - ((double) distance / maxLen);
		}

   
public static int distanceLevenshtein(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];

    for (int i = 0; i <= s1.length(); i++) {
        for (int j = 0; j <= s2.length(); j++) {
            if (i == 0) {
                dp[i][j] = j;
            } else if (j == 0) {
                dp[i][j] = i;
            } else {
                int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                );
            }
        }
    }

    return dp[s1.length()][s2.length()];
}
}

