package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}
	
	public class Marche{

		private Etal[] etals;
		private int quantite;
		
		private Marche(int quantite) {
			etals = new Etal[quantite];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		
		int trouverEtalLibre() {
			int indiceEtal=0;
			while(etals[indiceEtal].isEtalOccupe()){
				indiceEtal++;
			}
			return indiceEtal;
			
		}
		
		Etal[] trouverEtals(String produit){
			Etal[] nvTab = null;
			int indiceEtal=0;
			while(etals[indiceEtal].contientProduit(produit)) {
				nvTab[indiceEtal]=etals[indiceEtal];
				indiceEtal++;
			}
			
			return nvTab;
		}
		
		Etal trouverVendeur(Gaulois gaulois){
			int indiceEtal=0;
			while(etals[indiceEtal].getVendeur() != gaulois) {
				indiceEtal++;
			}
			
			return etals[indiceEtal];
			
		}
		
		String afficherMarcher() {
			int indiceEtal=0;
			
			while(etals[indiceEtal].isEtalOccupe()) {
				etals[indiceEtal].afficherEtal();
				indiceEtal++;
			}
			int nbEtalVide=quantite-indiceEtal;
			return "il rest" + nbEtalVide+" étals non utilisées dans le marche.\n";
		}
	}	
	
	

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int indiceEtal=0;
		Marche marche = new Marche(1);
		marche.utiliserEtal(indiceEtal,vendeur,produit,nbProduit);
		indiceEtal++;
		return vendeur.getNom()+"cherche un endroit pour vendre"+nbProduit + produit+"Le vendeur"+vendeur.getNom()+"vend des"+ produit + "à l'étal n°"+ indiceEtal;
		
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		Marche marche=new Marche(1);
		marche.trouverEtals(produit);
		return ;
	}
	
	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}