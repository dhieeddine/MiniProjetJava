package MiniProjet;

public class comparateurJaroWinkler implements ComparateurNoms {

	@Override
	     public double comparer(String s1, String s2) {
	        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) return 0.0;

	        // 1. Convertir en minuscules pour une comparaison insensible à la casse
	        s1 = s1.toLowerCase();
	        s2 = s2.toLowerCase();

	        // 2. Longueurs
	        int len1 = s1.length();
	        int len2 = s2.length();

	        // 3. Distance de comparaison
	        int matchDistance = Math.max(len1, len2) / 2 - 1;

	        // 4. Tableaux pour marquer les correspondances
	        boolean[] s1Matches = new boolean[len1];
	        boolean[] s2Matches = new boolean[len2];

	        int matches = 0;
	        for (int i = 0; i < len1; i++) {
	            int start = Math.max(0, i - matchDistance);
	            int end = Math.min(i + matchDistance + 1, len2);
	            for (int j = start; j < end; j++) {
	                if (!s2Matches[j] && s1.charAt(i) == s2.charAt(j)) {
	                    s1Matches[i] = true;
	                    s2Matches[j] = true;
	                    matches++;
	                    break;
	                }
	            }
	        }

	        if (matches == 0) return 0.0;

	        // 5. Calcul des transpositions
	        int t = 0;
	        int k = 0;
	        for (int i = 0; i < len1; i++) {
	            if (s1Matches[i]) {
	                while (!s2Matches[k]) k++;
	                if (s1.charAt(i) != s2.charAt(k)) t++;
	                k++;
	            }
	        }
	        double transpositions = t / 2.0;

	        // 6. Jaro score
	        double jaro = ((matches / (double) len1)
	                     + (matches / (double) len2)
	                     + ((matches - transpositions) / matches)) / 3.0;

	        // 7. Jaro-Winkler adjustment (prefix scale)
	        int prefix = 0;
	        int maxPrefixLength = 4;
	        while (prefix < Math.min(len1, len2) &&
	               s1.charAt(prefix) == s2.charAt(prefix) &&
	               prefix < maxPrefixLength) {
	            prefix++;
	        }

	        double winklerBoost = 0.1 * prefix * (1 - jaro);

	        return jaro + winklerBoost;
	    }

	  
	}

	

