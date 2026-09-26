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
	private int tailleM;

	public Village(String nom, int nbVillageoisMaximum,int tailleM) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.tailleM =tailleM;
		marche = new Marche(tailleM);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	
	private class Marche {
		private Etal[] etals;
		private int nbEtal;
		
		public Marche(int nbEtal) {
			this.nbEtal=nbEtal;
			etals = new Etal[nbEtal];
		    for (int i = 0; i < nbEtal; i++) {
		        etals[i] = new Etal();
		    }
		}
		
		void UtiliserEtal(int indiceEtal,Gaulois vendeur,String produit,int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		
			
		}
		
		int trouverEtalLibre() {
			for (int i=0;i<nbEtal;i++) {
				if( !etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
			
		}
		
		Etal[] trouverEtals(String produit) {
			int tailleT=0;
			for (int i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					tailleT++;
				}
			}
			Etal[] newtab=new Etal[tailleT];
			int indiceJ=0;
			for (int i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					newtab[indiceJ]=etals[i];
					indiceJ++;
				}
			}
			return newtab;
		}
		
		Etal trouverVendeur(Gaulois gaulois) {
			int indice=0;
			while(etals[indice].getVendeur()!= gaulois) {
				indice+=1;
			}
			
			return etals[indice];
		}
		
		String afficherMarcher() {
			int indice=0;
			StringBuilder str=new StringBuilder() ;
			while(etals[indice].isEtalOccupe()) {
				str.append(etals[indice].afficherEtal());
				indice+=1;
				
			}
			int reste=this.nbEtal - indice;
			System.out.println("il reste " + reste + " étals non utilisé par le marché.\n");
			return str.toString();
			
		}
		
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
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		
		System.out.println("le " +vendeur.getNom()+ " cherche un endroit pour vendre " + nbProduit + " "+ produit);
		int indiceEtal = marche.trouverEtalLibre();
		marche.UtiliserEtal(indiceEtal, vendeur, produit, nbProduit);
		
		return " Le vendeur "+ vendeur.getNom() + " vend des fleurs à l'étal n°" + indiceEtal;
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder ch = new StringBuilder();
		Etal[] tab = marche.trouverEtals(produit);
		//creer tableau avec des etals contenant x produit->parcourir le tableau et afficher prenom
		
		if (tab.length==0) {
			return "Il n'y a pas de vendeur qui propose des fleurs au marché";
		}else {
			System.out.println("les vendeurs qui proposent des "+ produit+ " :");
			for (int i=0;i<tab.length;i++) {
				ch.append("-"+tab[i].getVendeur().getNom());
				ch.append("\n");
				
			}
		}
		
		
		
		return ch.toString();
		
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