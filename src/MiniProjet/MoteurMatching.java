	package MiniProjet;

import java.util.ArrayList;
import java.util.List;

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
		
	     generateurCandidats = new GenerateurAleatoire(50);
	     pretraiteur = new ArrayList<Pretraiteur>(List.of(new PretraiteurMinuscule(),new PretraiteurSansAccents(), new PretraiteurPhonetique(),new PretraiteurSansCaracteresSpeciaux()));
	     comparateurNoms = new ComparateurExact();
	     selectionneur = new SelectionneurNPremiers(20);
	}


	public List<CoupleNomsScore> rechercher(String nom, List<EntiteNom> listeNoms){
		

		List<EntiteNom> list2= new ArrayList<>();
		EntiteNom entiteNom= new EntiteNom(nom, "-1");
		list2.add(entiteNom);
		for(int i=0;i<pretraiteur.size();i++) {
			listeNoms=pretraiteur.get(i).pretraiter(listeNoms);
			list2=pretraiteur.get(i).pretraiter(list2);
			
		}
		
		List<CoupleDeNom> coupleDeNoms =new ArrayList<CoupleDeNom>();
		coupleDeNoms = generateurCandidats.genererCandidats(list2,listeNoms);
		List<CoupleNomsScore> resultat = new ArrayList<CoupleNomsScore>();
		
	   
		for(int i=0; i<coupleDeNoms.size(); i++) {
			EntiteNom e = coupleDeNoms.get(i).getNom1();
			double score = comparateurNoms.comparer(e.getNomPretraite(),entiteNom.getNomPretraite());
			CoupleNomsScore res = new CoupleNomsScore(entiteNom, e, score);
			resultat.add(res);
			
			
		}  
		return selectionneur.selectionner(resultat); 
		
	}
	
	
	 
	public List<CoupleNomsScore> ComparerListes(List<EntiteNom> list1 ,List<EntiteNom> list2){
		List<CoupleDeNom> coupleDeNoms =new ArrayList<CoupleDeNom>();
		coupleDeNoms = generateurCandidats.genererCandidats(list2,list1);
		List<CoupleNomsScore> resultat = new ArrayList<CoupleNomsScore>();
		
	   
		for(int i=0; i<coupleDeNoms.size(); i++) {
			
			double score = comparateurNoms.comparer(coupleDeNoms.get(i).getNom2().getNomPretraite(),coupleDeNoms.get(i).getNom2().getNomPretraite());
			CoupleNomsScore res = new CoupleNomsScore(coupleDeNoms.get(i).getNom1(), coupleDeNoms.get(i).getNom2(), score);
			resultat.add(res);
						
		}  
		
		return resultat;
	}
	public List<CoupleNomsScore> DedupliquerList(List<EntiteNom> list){
		for(int i=0;i<pretraiteur.size();i++) {
			list=pretraiteur.get(i).pretraiter(list); 
			
		}
		List<CoupleNomsScore> resultat=new ArrayList<CoupleNomsScore>();
		for (int i=0;i<list.size()-1;i++){
			List<CoupleNomsScore> listNomsScores=rechercher(list.get(i).getNomPretraite(),list.subList(i+1,list.size()));
			for(CoupleNomsScore nomScore: listNomsScores) {
				resultat.add(nomScore);
			}
			
		} 
		return resultat;
	}

}
