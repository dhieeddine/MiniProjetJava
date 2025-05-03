package MiniProjet;
import java.io.*;
import java.util.*;

public class RecuperateurCSV implements Recuperateur {

	@Override
	public List<EntiteNom> recuperer(String chemin) {
		
		
		        List<EntiteNom> entites = new ArrayList<>();

		        try (BufferedReader reader = new BufferedReader(new FileReader(chemin))) {
		            String ligne;
		            boolean premiereLigne = true;

		            while ((ligne = reader.readLine()) != null) {
		                // Ignorer l'en-tête
		                if (premiereLigne) {
		                    premiereLigne = false;
		                    continue;
		                }

		                String[] parties = ligne.split(",");

		                if (parties.length >= 2) {
		                    String nomComplet = parties[0];
		                    String id = parties[1];
		                    entites.add(new EntiteNom(nomComplet, id));
		                }
		            }
		        } catch (IOException e) {
		            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
		        }

		        return entites;
	}

}
