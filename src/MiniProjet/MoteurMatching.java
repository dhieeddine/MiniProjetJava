	package MiniProjet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoteurMatching {
	
	
    private GenerateurCandidats generateurCandidats;
    private List<Pretraiteur> pretraiteur;
    private ComparateurNoms comparateurNoms;
    private SelectionneurDeResultatsDeMatching selectionneur;
	
	 
	
	
	public GenerateurCandidats getGenerateurCandidats() {
		return generateurCandidats;
	}




	public void setGenerateurCandidats(GenerateurCandidats generateurCandidats) {
		this.generateurCandidats = generateurCandidats;
	}




	public List<Pretraiteur> getPretraiteur() {
		return pretraiteur;
	}




	public void setPretraiteur(List<Pretraiteur> pretraiteur) {
		this.pretraiteur = pretraiteur;
	}




	public ComparateurNoms getComparateurNoms() {
		return comparateurNoms;
	}




	public void setComparateurNoms(ComparateurNoms comparateurNoms) {
		this.comparateurNoms = comparateurNoms;
	}




	public SelectionneurDeResultatsDeMatching getSelectionneur() {
		return selectionneur;
	}




	public void setSelectionneur(SelectionneurDeResultatsDeMatching selectionneur) {
		this.selectionneur = selectionneur;
	}




	public MoteurMatching(GenerateurCandidats generateurCandidats, List<Pretraiteur> pretraiteur,
			ComparateurNoms comparateurNoms, SelectionneurDeResultatsDeMatching selectionneur) {
		super();
		this.generateurCandidats = generateurCandidats;
		this.pretraiteur = pretraiteur;
		this.comparateurNoms = comparateurNoms;
		this.selectionneur = selectionneur;
	}

	public MoteurMatching() {   
		
	  // generateurCandidats = new GenerateurAleatoire(10000000);
	    generateurCandidats = new GenerateurAvecPrefix();
	    // generateurCandidats = new GenerateurDeTousLesCouples();
	   //generateurCandidats = new GenerateurParTaille();
	     pretraiteur = new ArrayList<Pretraiteur>(List.of(new PretraiteurMinuscule(),new PretraiteurSansAccents(),new PretraiteurDeTRiNom(),new PretraiteurPhonetique()));
	    // comparateurNoms = new ComparateurExact();     
	     comparateurNoms = new ComparateurLevenstein();
	     //comparateurNoms = new ComparateurJaroWinkler();
	    // comparateurNoms = new ComparateurNGram(3);
	    selectionneur = new SelectionneurAvecSeuil(0.6);
	     //selectionneur = new SelectionneurNPremiers(100);
	}
	


	public List<CoupleNomsScore> rechercher(EntiteNom entiteNom, List<EntiteNom> listeNoms){
		

		List<EntiteNom> list2= new ArrayList<>();
		
		list2.add(entiteNom);
		for(int i=0;i<pretraiteur.size();i++) {
			listeNoms=pretraiteur.get(i).pretraiter(listeNoms);
			list2=pretraiteur.get(i).pretraiter(list2);
			
		}
		
		List<CoupleDeNom> coupleDeNoms =new ArrayList<CoupleDeNom>();
		coupleDeNoms = generateurCandidats.genererCandidats(list2,listeNoms);
		List<CoupleNomsScore> resultat = new ArrayList<CoupleNomsScore>();    
		
	   
		for(int i=0; i<coupleDeNoms.size(); i++) {
			EntiteNom e = coupleDeNoms.get(i).getNom2();
			double score = comparateurNoms.comparer(e,entiteNom);
			CoupleNomsScore res = new CoupleNomsScore(entiteNom, e, score);
			resultat.add(res);
			
			
		}  
		return selectionneur.selectionner(resultat); 
		
	}
	
	
	 
	public List<CoupleNomsScore> ComparerListes(List<EntiteNom> list1 ,List<EntiteNom> list2){
		for(int i=0;i<pretraiteur.size();i++) {
			list1=pretraiteur.get(i).pretraiter(list1);
			list2=pretraiteur.get(i).pretraiter(list2);
			
		}
		List<CoupleDeNom> coupleDeNoms =new ArrayList<CoupleDeNom>();
		coupleDeNoms = generateurCandidats.genererCandidats(list2,list1);
		List<CoupleNomsScore> resultat = new ArrayList<CoupleNomsScore>();
        //System.out.printf("completer generateur!!!");
	   
		for(int i=0; i<coupleDeNoms.size(); i++) {
			
			double score = comparateurNoms.comparer(coupleDeNoms.get(i).getNom1(),coupleDeNoms.get(i).getNom2());
			CoupleNomsScore res = new CoupleNomsScore(coupleDeNoms.get(i).getNom1(), coupleDeNoms.get(i).getNom2(), score);
			resultat.add(res);
						
		}  
		
		return resultat;
	}
	public List<CoupleNomsScore> DedupliquerList(List<EntiteNom> list){
		
		
	
		
		
		
		for(int i=0;i<pretraiteur.size();i++){
			list=pretraiteur.get(i).pretraiter(list); 
			
		}
		int x=0;
		Indexeur indexeur = new IndexeurHashMap() ;
		Map<Integer,List<EntiteNom>> map = (Map<Integer, List<EntiteNom>>) indexeur.indexer(list);
		List<CoupleNomsScore> resultat=new ArrayList<CoupleNomsScore>();
		for (Map.Entry<Integer, List<EntiteNom>> entry : map.entrySet()) {
			List<EntiteNom> listNoms = entry.getValue();
			for(int i=0; i<listNoms.size() -1;i++) {
				for(int j =i+1;j<listNoms.size();j++) {
					if(listNoms.get(i)==listNoms.get(j)) continue;
					double score = comparateurNoms.comparer(listNoms.get(i),listNoms.get(j));
					
					CoupleNomsScore res = new CoupleNomsScore(listNoms.get(i),listNoms.get(j), score);
					if(score>=0.99) {
						System.out.printf("%d %s",x,res);
						x++;
						
					}
					
					//resultat.add(res); 
				}
				if(map.containsKey(listNoms.size()+1)) {
					
					List<EntiteNom> list1=new ArrayList<EntiteNom>(map.get(listNoms.size()+1));
					for(int j =0;j<list1.size();j++) {
						double score = comparateurNoms.comparer(listNoms.get(i),list1.get(j));
						CoupleNomsScore res = new CoupleNomsScore(listNoms.get(i),list1.get(j), score);
						if(score>=0.99) {
							System.out.printf("%d %s",x,res);
							x++;
							
						}
						//resultat.add(res); 
					}
				}
					if(map.containsKey(listNoms.size()-1)) {
						
						List<EntiteNom> list1=new ArrayList<EntiteNom>(map.get(listNoms.size()-1));
						for(int j =0;j<list1.size();j++) {
							double score = comparateurNoms.comparer(listNoms.get(i),list1.get(j));
							CoupleNomsScore res = new CoupleNomsScore(listNoms.get(i),list1.get(j), score);
							if(score>=0.99) {
								System.out.printf("%d %s",x,res);
								x++;
								
							}
							
							//resultat.add(res); 
						}
					
				
			         }
					if(x>40000)return resultat;
					 
			

				
				
			}
			listNoms=null;
			
		} 
		return resultat;
	}

}
