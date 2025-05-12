package MiniProjet;

import java.io.*;
import java.util.*;
public class Application {
	static MoteurMatching moteur = new MoteurMatching();
	
	static Recuperateur recuperateur = new RecuperateurCSV();
	static  Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		   
	
			// TODO Auto-generated method stub
			
		//D:/Desktop/listesNoms/peps_names_658k.csv
			
		 while (true) {
			        afficherMenuPrincipal();
			        //Scanner scanner = new Scanner(System.in);
			
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
		  moteur.setGenerateurCandidats(new GenerateurParTaille());
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
		  scanner.nextLine();
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
		    while (true) {
		        afficheMenuPretraiteur();
		        String choix = scanner.nextLine();

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
		    Iterator<Pretraiteur> it = moteur.getPretraiteur().iterator();
		    while (it.hasNext()) {
		        Pretraiteur p = it.next();
		        if (p instanceof PretraiteurMinuscule) {
		            it.remove(); 
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
		    while (true) {
		        afficheMenuSelectionneur();
		        String choix = scanner.nextLine();

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
		    
		   EntiteNom nom = new EntiteNom(nomOriginal,"-1");
		    List<EntiteNom> listeOriginale = recuperateur.recuperer();
		    long start = System.currentTimeMillis();
           
		    
		    if (listeOriginale == null || listeOriginale.isEmpty()) {
		        System.out.println("Erreur : la liste des candidats est vide ou n'a pas pu être chargée.");
		        return;
		    }
		    
		   
		    
		    List<CoupleNomsScore> resultats = moteur.rechercher(nom, listeOriginale);
		    long end = System.currentTimeMillis();
		    
		    System.out.println("\nRésultats pour : " + nom.getNomcomplet());
		    for (CoupleNomsScore ns : resultats) {
		        
		        
		        
		            System.out.printf("Nom: %s (Score: %.2f)%n", 
		                ns.nom2(), 
		                ns.score());
		    }
            System.out.println("Execution time: " + (end - start) + " ms");
            resultats.clear();
	        
	        
	        	
	  }
	  
	  static void comparerDeuxListes() {
	        // TODO
		 
		  List<EntiteNom> list1 =new ArrayList<EntiteNom>();
	      list1 = recuperateur.recuperer();
	      if (list1 == null || list1.isEmpty()) {
	            System.out.println("Erreur : la liste 1  des candidats est vide ou n’a pas pu être chargée.");
	            return;
	        }
	        
	     
		 List<EntiteNom> list2 =new ArrayList<EntiteNom>();
		 list2 = recuperateur.recuperer();
		 if (list2 == null || list2.isEmpty()) {
	            System.out.println("Erreur : la liste 2 des candidats est vide ou n’a pas pu être chargée.");
	            return;
	        }
		 long start = System.currentTimeMillis();
        
		 List<CoupleNomsScore> resultat = new ArrayList<>();
	       resultat = moteur.ComparerListes(list1, list2);
		 
		 
	     
	       resultat = moteur.getSelectionneur().selectionner(resultat);
	       long end = System.currentTimeMillis();
	      /*  if (resultat == null || resultat.isEmpty()) {
	            System.out.println("Aucun résultat trouvé pour le nom spécifié.");
	            return;      
	        }
		 for(CoupleNomsScore couple : resultat) {
			 System.out.println(couple);

		  
	    }*/
		 resultat.clear();
         System.out.println("Execution time: " + (end - start) + " ms");
	  }

	    static void effectuerDedupliquerListe() {
	        // TODO
	    	 
	    	 List<EntiteNom> listeOriginale = recuperateur.recuperer();
			    
			    if (listeOriginale == null || listeOriginale.isEmpty()) {
			        System.out.println("Erreur : la liste des candidats est vide ou n'a pas pu être chargée.");
			        return;
			    }
			    long start = System.currentTimeMillis();
			   List<CoupleNomsScore> resultat = new ArrayList<>(moteur.DedupliquerList(listeOriginale));
			   /* if (resultat == null || resultat.isEmpty()) {
		            System.out.println("Aucun résultat trouvé pour le nom spécifié.");
		            return;      
		        }
			    resultat = moteur.getSelectionneur().selectionner(resultat);
				 for(CoupleNomsScore couple : resultat) {
					 System.out.println(couple);
					 
				 }
				 resultat.clear();*/
				 long end = System.currentTimeMillis();
		            System.out.println("Execution time: " + (end - start) + " ms");
	    }

	
}