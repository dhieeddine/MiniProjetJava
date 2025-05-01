	package MiniProjet;

import java.util.ArrayList;
import java.util.List;

public class MoteurMatching {
	
	private Recuperateur recuperateur;
    private GenerateurCandidats generateurCandidats;
    private List<Pretraiteur> pretraiteur;
    private ComparateurNoms comparateurNoms;
    private SelectionneurDeResultatsDeMatching selectionneur;
	
	 
	public MoteurMatching(Recuperateur recuperateur, GenerateurCandidats generateurCandidats, List<Pretraiteur> pretraiteur,
			ComparateurNoms comparateurNoms, SelectionneurDeResultatsDeMatching selectionneur) {
		super();
		this.recuperateur = recuperateur;
		this.generateurCandidats = generateurCandidats;
		this.pretraiteur.addAll(pretraiteur);
		this.comparateurNoms = comparateurNoms;
		this.selectionneur = selectionneur;
	}
	public MoteurMatching() {
		 recuperateur = new RecuperateurCSV();
	     generateurCandidats = new GenerateurBasic();
	     pretraiteur = new ArrayList<>(List.of(new PretraiteurBasic()));
	     comparateurNoms = new ComparateurExact();
	     selectionneur = new SelectionneurNPremiers(10);
	}
	
	public Recuperateur getRecuperateur() {
		return recuperateur;
	}

	public void setRecuperateur(Recuperateur recuperateur) {
		this.recuperateur = recuperateur;
	}

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

	
	 
	
	public ArrayList<ResultatComparaison> rechercher(String nom, String chemin){
		
		ArrayList<EntitéNom> listeNoms= recuperateur.recuperer(chemin);
		ArrayList<EntitéNom> list2= new ArrayList<>();
		EntitéNom entitéNom= new EntitéNom(nom, -1);
		list2.add(entitéNom);
		for(int i=0;i<pretraiteur.size();i++) {
			listeNoms=pretraiteur.get(i).pretraiter(listeNoms);
			list2=pretraiteur.get(i).pretraiter(list2);
			
		}
		
		
		listeNoms = generateurCandidats.genererCandidats(listeNoms);
		ArrayList<ResultatComparaison> resultat = new ArrayList<>();
	   
		for(int i=0; i<listeNoms.size(); i++) {
			EntitéNom e = new EntitéNom(listeNoms.get(i).getNomcomplet() , listeNoms.get(i).getId());
			double score = comparateurNoms.comparer(e.getNomcomplet(),entitéNom.getNomcomplet());
			ResultatComparaison res = new ResultatComparaison(e , score);
			resultat.add(res);
			
			
			
		} 
		
		
		
		return selectionneur.selectionner(resultat);
		
		 
		
	}
	   
	

}
