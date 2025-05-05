package MiniProjet;

import java.io.*;
import java.util.*;
public class Application {
	static MoteurMatching moteur = new MoteurMatching();
	static Scanner scanner = new Scanner(System.in);
	static Recuperateur recuperateur = new RecuperateurCSV();

	public static void main(String[] args) {
		   
	
			// TODO Auto-generated method stub
			
		
			
		 while (true) {
		            afficherMenuPrincipal();
		            String choix = scanner.nextLine();
	
		            switch (choix) {
		                case "1" -> effectuerRecherche();
		                case "2" -> comparerDeuxListes();
		                case "3" -> effectuerDedupliquerListe();
		                case "4" -> configurerParametres();
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
	        System.out.println("\n===== MENU PRINCIPAL =====\n");
	        System.out.println("1. Effectuer une recherche\n");
	        System.out.println("2. Comparer deux listes\n");
	        System.out.println("3. Dédupliquer une liste\n");
	        System.out.println("4. Configurer les paramètres\n");
	        System.out.println("5. Quitter\n");
	        System.out.print("Votre choix : ");
	  }
	  
	    static void afficherMenuConfiguration() {
	        // TODO 
	    	    System.out.println("\n===== MENU CONFIGURATION =====");
	    	    System.out.println("1. Choisir les prétraitements\r\n");
		        System.out.println("2. Choisir une mesure de comparaison\r\n");
		        System.out.println("3. Choisir un generateur de candidats\r\n");
		        System.out.println("4. Choisir un selectionneur\r\n");
		        System.out.println("5. Quitter");
		        System.out.print("Votre choix : ");
	    }
 
	    
	  static void configurerParametres() {
		 
          while (true) {
        	  afficherMenuConfiguration();
              String choix = scanner.nextLine();
        	  switch (choix) {
              case "1" -> choixPretraiteurs();
              case "2" -> choixComparateur();
              case "3" -> choixGenerateurDeCandidats();
              case "4" -> choixSelectionneur();
              case "5" -> {
                
                  return;
              }
              default -> System.out.println("Choix invalide.");
             }
          }

          
		  
	  }
	  static void afficherMenuGenerateurDeCandidats() {
	        // TODO 
	    	    System.out.println("\n===== MENU GENERATEUR DE CANDIDATS =====");
	    	    System.out.println("1. Choisir tous combinisants possibles\r\n");
	    	    System.out.println("2. Choisir generateur aleatoire\r\n");

		        System.out.println("3. Choisir generateur par taille de noms \r\n");
		        System.out.println("4. Quitter");
		        System.out.print("Votre choix : ");
	    }
	  
	  static void choixGenerateurDeCandidats() {
		 
        while(true) {
        	 afficherMenuGenerateurDeCandidats();
             String choix = scanner.nextLine();
        	  switch (choix) {
              case "1" -> generateurTous();
              case "2" -> generateurAleatoire();
              case "3" -> generateurParTaille();
              case "4" -> {
                  
                  return;
              }
              default -> System.out.println("Choix invalide.");
          } 
        }

      
	  }
	  
	  static void generateurTous() {
		  moteur.setGenerateurCandidats(new GenerateurDeTousLesCouples());
		  
	  }
	  static void generateurAleatoire() {
		  System.out.println("\nchoisir le nombre de candidats à generer : ");
		  int n = scanner.nextInt();
		  moteur.setGenerateurCandidats(new GenerateurAleatoire(n));
	  }
	  
	  
	  static void generateurParTaille() {
		  System.out.println("\nchoisir le taille max de difference: ");
		  int n = scanner.nextInt();
		  moteur.setGenerateurCandidats(new GenerateurParTaille(n));
	  }
	  
	  
	  static void afficherMenuComparateur() {
	        // TODO 
	    	    System.out.println("\n===== MENU COMPARATEUR =====");
	    	    System.out.println("1. Choisir comparateur exact\r\n");
		        System.out.println("2. Choisir comparateur avec N-Gram \r\n");
		        System.out.println("3. Quitter");
		        System.out.print("Votre choix : ");
	    }
	  
	  static void choixComparateur() {
		  

          while(true) {
        	  afficherMenuComparateur();
              String choix = scanner.nextLine();
        	  switch (choix) {
              case "1" -> choixComparateurExact();
              case "2" -> choixComparateurNGram();
              case "3" -> {
                 
                  return;
              }
              default -> System.out.println("Choix invalide.");
          }
          }
		  
		  
	  }
	  static void choixComparateurExact() {
		  moteur.setComparateurNoms(new ComparateurExact());
		  
	  }
	  static void choixComparateurNGram() {
		  System.out.println("\nchoisir le nombre de decomposition : ");
		  int n = scanner.nextInt();
		  moteur.setComparateurNoms(new ComparateurNGram(n));
		  
	  }
	  
	  
	  
	  
	  static void afficheMenuPretraiteur() {
	        System.out.println("\n===== MENU PRETRAITEUR =====\n");
	        System.out.println("Le pretraiteur par defaut est le pretraiteur en minuscule\n");
	        System.out.println("1.suprimer le pretraiteur en minuscule\n");
	        System.out.println("2. Encodage phonétique\n");
	        System.out.println("3. Suprimer les accents et les caractaires speciciaux\n");
	        System.out.println("4. Quitter\n");
	        System.out.print("Votre choix : ");
		  
	  }
	  static void choixPretraiteurs() {
		  afficheMenuPretraiteur();
          String choix = scanner.nextLine();

         while(true) {
        	 switch (choix) {
             case "1" -> suprimerpretraiterMinuscule();
             case "2" -> pretraiterPhonetique();
             case "3" -> pretraiterAccents();
             
             case "4" -> {
                 return;
             }
             default -> System.out.println("Choix invalide.");
         }
         }
		  
	  }
	  static void pretraiterPhonetique(){
		  moteur.getPretraiteur().add(new PretraiteurPhonetique());
		  
	  }
	  static void pretraiterAccents() {
		  moteur.getPretraiteur().add(new PretraiteurSansAccents()); 
		
	  }
	  static void suprimerpretraiterMinuscule() {
		  
		  
		  for (Pretraiteur p : moteur.getPretraiteur()) {
			  if(p instanceof PretraiteurMinuscule) {
				  moteur.getPretraiteur().remove(p);
			  }
			  
		  }
		  
		  
	  }
	  
	  static void afficheMenuSelectionneur() {
	        System.out.println("\n===== MENU SELECTIONNEUR =====\n");
	        System.out.println("1.Choisir selectionneur de tous les resultats\n");
	        System.out.println("2.Choisir selectionneur aves seuil\n");
	        System.out.println("3.Choisir selectionneur des N premiers\n");
	        System.out.println("4. Quitter\n");
	        System.out.print("Votre choix : ");
		  
	  }
	  
	  
	  static void choixSelectionneur() {
		  afficheMenuSelectionneur();
          String choix = scanner.nextLine();

          while(true) {
        	  switch (choix) {
              case "1" -> SelectionneurDeTousLesResultats();
              case "2" -> SelectionneurAvecSeuil();
              case "3" -> SelectionneurNPremiers();
              case "4" -> {
                  
                  return;
              }
              default -> System.out.println("Choix invalide.");
        	  }
          }
        
	  }
      
	  static void SelectionneurDeTousLesResultats() {
		  moteur.setSelectionneur(new SelectionneurDeTousLesResultats());
		  
	  }
	  static void SelectionneurAvecSeuil() {
		  moteur.setSelectionneur(new SelectionneurAvecSeuil(0));
	  }
	  static void SelectionneurNPremiers() {
		    System.out.println("Entrez le nombre de resultats a selectionner : ");
		    int n = scanner.nextInt();
		    scanner.nextLine(); 
		    moteur.setSelectionneur(new SelectionneurNPremiers(n));
	  
	  }
	  
	  static void effectuerRecherche()  {
		  System.out.print("Entrez le nom à rechercher : ");
		    String nomOriginal = scanner.nextLine();
		    
		    System.out.print("Entrez le chemin du fichier des candidats : ");
		    String chemin = scanner.nextLine();
		    
		    List<EntiteNom> listeOriginale = recuperateur.recuperer(chemin);
		    
		    if (listeOriginale == null || listeOriginale.isEmpty()) {
		        System.out.println("Erreur : la liste des candidats est vide ou n'a pas pu être chargée.");
		        return;
		    }
		    
		    List<EntiteNom> listePourTraitement = new ArrayList<>();
		    for (EntiteNom entite : listeOriginale) {
		        listePourTraitement.add(new EntiteNom(entite.getNomcomplet(), entite.getId()));
		    }
		    
		    List<NomScore> resultats = moteur.rechercher(nomOriginal, listePourTraitement);
		    
		    System.out.println("\nRésultats pour : " + nomOriginal);
		    for (NomScore ns : resultats) {
		        EntiteNom entiteOriginale = null;
		        for (EntiteNom e : listeOriginale) {
		            if (e.getId().equals(ns.getNom().getId())) {
		                entiteOriginale = e;
		                break;
		            }
		        }
		        
		        if (entiteOriginale != null) {
		            System.out.printf("Nom: %s (Score: %.2f)%n", 
		                entiteOriginale.getNomcomplet(), 
		                ns.getScore());
		        }
		    
		    }
		
	        
	        
	        	
	  }
	  
	  static void comparerDeuxListes() {
	        // TODO
		  System.out.println("donner le chemin du liste 1");
		  String chemin = scanner.next();
		  List<EntiteNom> list1 =new ArrayList<EntiteNom>();
	      list1 = recuperateur.recuperer(chemin);
	      if (list1 == null || list1.isEmpty()) {
	            System.out.println("Erreur : la liste 1  des candidats est vide ou n’a pas pu être chargée.");
	            return;
	        }
	        
	     System.out.println("donner le chemin du liste 2");
		 chemin = scanner.next();
		 List<EntiteNom> list2 =new ArrayList<EntiteNom>();
		 list2 = recuperateur.recuperer(chemin);
		 if (list2 == null || list2.isEmpty()) {
	            System.out.println("Erreur : la liste 2 des candidats est vide ou n’a pas pu être chargée.");
	            return;
	        }
		 List<CoupleNomsScore> resultat = new ArrayList<>();
	       resultat = moteur.ComparerListes(list1, list2);
		 
		 
	        for(CoupleNomsScore e : resultat) {
	        	System.out.println(e.toString());
	        }
	        resultat = moteur.getSelectionneur().selectionner(resultat);
	        if (resultat == null || resultat.isEmpty()) {
	            System.out.println("Aucun résultat trouvé pour le nom spécifié.");
	            return;      
	        }
		 for(CoupleNomsScore couple : resultat) {
			 System.out.println(couple.toString());
		 }
		  
	    }

	    static void effectuerDedupliquerListe() {
	        // TODO
	    	 System.out.println("donner le chemin du liste à dupliquer : ");
	    	 String chemin = scanner.next();
	    	 List<EntiteNom> listeOriginale = recuperateur.recuperer(chemin);
			    
			    if (listeOriginale == null || listeOriginale.isEmpty()) {
			        System.out.println("Erreur : la liste des candidats est vide ou n'a pas pu être chargée.");
			        return;
			    }
			    List<CoupleNomsScore> resultat = new ArrayList<>(moteur.DedupliquerList(listeOriginale));
			    if (resultat == null || resultat.isEmpty()) {
		            System.out.println("Aucun résultat trouvé pour le nom spécifié.");
		            return;      
		        }
				 for(CoupleNomsScore couple : resultat) {
					 System.out.println(couple.toString());
				 }
	    }

	
}

