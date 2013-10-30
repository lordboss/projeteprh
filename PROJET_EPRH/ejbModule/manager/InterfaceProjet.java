package manager;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;
import beans.*;
@Remote
public interface InterfaceProjet {

	/*enregistrement des données*/

	public boolean enregistreEnseignant(Enseignant e);
	public boolean enregistreAdministrateur(Administrateur a);
	public boolean enregistreCommande(Commande c);
	public boolean enregistrerCommuniquer(Communiquer c);
	public boolean enregistrerCours(Cours c);
	public boolean enregistrerHoraire(Horaire h);
	public boolean enregistrerPV(Proces_Verbaux pv);
	public boolean enregistrerSalle(Salle salle);
	public boolean enregistrerMateriel(Materiel m) ;

	/*récupération des données par unité*/

	public Enseignant rechercheEnseignant(String login);
	public Horaire rechercheHoraire(String id);
	public Administrateur rechercheAdministrateur(String identifiant);
	public Commande rechercheCommande(int id);
	public Communiquer rechercheCommuniquer(int id);
	public Cours rechercheCours(String code);
	public Proces_Verbaux recherchePV(int id);
	public Salle rechercheSalle(String code);
	public List<Materiel> recupereMateriel(); 
	public Materiel rechercheMateriel(String code); 
	public Proces_Verbaux recherchePV(String annee);
	public Enseignant rechercheEnseignant(int login);
	public Administrateur rechercheAdministrateur(int id);

	/*modification des données*/

	public boolean modifieEnseignant(Enseignant e);
	public boolean modifieAdministrateur(Administrateur a);
	public boolean modifieCommande(Commande c);
	public boolean modifieCommuniquer(Communiquer c);
	public boolean modifieCours(Cours c);
	public boolean modifieHoraire(Horaire h);
	public boolean modifiePV(Proces_Verbaux pv);
	public boolean modifieSalle(Salle salle);

	/*récupération des données par liste*/
	public List<Enseignant> recupereListeEnseignant();
	public List<Horaire> recupereListeHoraire();
	public List<Salle> recupereSalle();
	public List<Cours> recupereCours();
	public List<Cours> recupereCours(int niveau);
	public List<Proces_Verbaux> RecupereListePv();
	public List<Communiquer> recupereCommuniquer();
	/* fonction qui récupère l'ensemble des cours dispensés par un enseignant*/
	
	public List<Commande> recupereCommande();
	public boolean enregistrerMateriel2(Materiel m);
	
}
