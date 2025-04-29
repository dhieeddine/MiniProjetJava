	package MiniProjet;

import java.util.ArrayList;
import java.util.List;

public class MoteurMatching {
	
	private Recuperateur recuperateur;
	private GenerateurCandidats generateurCandidats;
	private Pretraiteur pretraiteur;
	private ComparateurNoms comparateurNoms;
	private SelectionneurDeResultatsDeMatching selectionneur;
	
	 
	public MoteurMatching(Recuperateur recuperateur, GenerateurCandidats generateurCandidats, Pretraiteur pretraiteur,
			ComparateurNoms comparateurNoms, SelectionneurDeResultatsDeMatching selectionneur) {
		super();
		this.recuperateur = recuperateur;
		this.generateurCandidats = generateurCandidats;
		this.pretraiteur = pretraiteur;
		this.comparateurNoms = comparateurNoms;
		this.selectionneur = selectionneur;
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

	public Pretraiteur getPretraiteur() {
		return pretraiteur;
	}

	public void setPretraiteur(Pretraiteur pretraiteur) {
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
		listeNoms=pretraiteur.pretraiter(listeNoms);
		list2=pretraiteur.pretraiter(list2);
		
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
