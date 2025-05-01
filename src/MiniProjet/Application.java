package MiniProjet;

import java.io.*;
import java.util.*;
public class Application {
	static MoteurMatching moteur = new MoteurMatching();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		   
	
			// TODO Auto-generated method stub
			
		
			
			 while (true) {
		            afficherMenuPrincipal();
		            String choix = scanner.nextLine();
	
		            switch (choix) {
		                case "1" -> effectuerRecherche();
		                /*case "2" -> comparerDeuxListes();
		                case "3" -> dedupliquerListe();
		                case "4" -> configurerParametres();*/
		                case "5" -> {
		                    System.out.println("Fin du programme.");
		                    scanner.close();
		                    return;
		                }
		                default -> System.out.println("Choix invalide.");
		            }
		        }
		 
	 } 
	  static void afficherMenuPrincipal() {
	        System.out.println("\n===== MENU PRINCIPAL =====");
	        System.out.println("1. Effectuer une recherche");
	        System.out.println("2. Comparer deux listes");
	        System.out.println("3. Dédupliquer une liste");
	        System.out.println("4. Configurer les paramètres");
	        System.out.println("5. Quitter");
	        System.out.print("Votre choix : ");
	  }
	  
	  static void effectuerRecherche()  {
	        System.out.print("tepper le Nom à rechercher : ");
			String nom = scanner.nextLine();
	
	        System.out.print("tapper le Fichier des candidats : ");
	        String fichier = scanner.nextLine();
	        
	        
	        
	        
	        scanner.close();	
	  }

}

