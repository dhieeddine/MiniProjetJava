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
		
	     generateurCandidats = new GenerateurDeTousLesCouples();
	     pretraiteur = new ArrayList<>(List.of(new PretraiteurMinuscule()));
	     comparateurNoms = new ComparateurExact();
	     selectionneur = new SelectionneurNPremiers(10);
	}


	public List<NomScore> rechercher(String nom, List<EntiteNom> listeNoms){
		

		List<EntiteNom> list2= new ArrayList<>();
		EntiteNom entiteNom= new EntiteNom(nom, "-1");
		list2.add(entiteNom);
		for(int i=0;i<pretraiteur.size();i++) {
			listeNoms=pretraiteur.get(i).pretraiter(listeNoms);
			list2=pretraiteur.get(i).pretraiter(list2);
			
		}
		
		List<CoupleDeNom> coupleDeNoms =new ArrayList<>();
		coupleDeNoms = generateurCandidats.genererCandidats(list2,listeNoms);
		List<NomScore> resultat = new ArrayList<NomScore>();
		
	   
		for(int i=0; i<coupleDeNoms.size(); i++) {
			EntiteNom e = new EntiteNom(coupleDeNoms.get(i).getNom2().getNomcomplet() , coupleDeNoms.get(i).getNom2().getId());
			double score = comparateurNoms.comparer(e.getNomcomplet(),entiteNom.getNomcomplet());
			NomScore res = new NomScore( score,e);
			resultat.add(res);
			
			
		}  
		return resultat;
		
	}
	
	
	 
	public List<CoupleNomsScore> ComparerListes(List<EntiteNom> list1 ,List<EntiteNom> list2){
		ArrayList<CoupleNomsScore> resultat=new ArrayList<CoupleNomsScore>();
		for (EntiteNom nom :list1 ) {
			List<NomScore> listNomsScores=rechercher(nom.getNomcomplet(),list2);
			
			for(NomScore nomScore: listNomsScores) {
				CoupleNomsScore res=new CoupleNomsScore(nom,nomScore.getNom(),nomScore.getScore());
				resultat.add(res);
			}
			
		}
		return resultat;
	}
	public List<CoupleNomsScore> DedupliquerList(List<EntiteNom> list){
		List<CoupleNomsScore> resultat=new ArrayList<CoupleNomsScore>();
		for (int i=0;i<list.size()-1;i++){
			List<NomScore> listNomsScores=rechercher(list.get(i).getNomcomplet(),list.subList(i+1,list.size()));
			for(NomScore nomScore: listNomsScores) {
				CoupleNomsScore res=new CoupleNomsScore(list.get(i),nomScore.getNom(),nomScore.getScore());
				resultat.add(res);
			}
			
		}
		return resultat;
	}

}
