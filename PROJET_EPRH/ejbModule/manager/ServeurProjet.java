package manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.*;

@Stateless(name = "Client", mappedName = "ServeurProjet/remote-Client")
public class ServeurProjet implements InterfaceProjet {
	@PersistenceContext
	EntityManager em;

	@Override
	public boolean enregistreEnseignant(Enseignant e) {
		try {
			Enseignant ens = (Enseignant) em
					.createQuery("select e from Enseignant where e.nomEnseignant="
							+ e.getNom_Enseignant());
			System.out
			.println("Enseignant existant déja dans la base de données");
			return false;
		} catch (Exception ex) {
            e.setCon(0);
			em.persist(e);
			System.out.println("enseignant enregistré avec succès");
			return true;
		}

	}

	@Override
	public boolean enregistreAdministrateur(Administrateur a) {
		try {
			Administrateur admin = (Administrateur) em
					.createQuery("select a from Administrateur where a.Nom_Admin="
							+ a.getNom_Admin());
			System.out
			.println("Administrateur existant déja dans la base de données");
			return false;
		} catch (Exception e) {
			em.persist(a);
			System.out.println("Administrateur enregistré avec succès");
			return true;
		}
	}


	@Override
	public boolean enregistrerCommuniquer(Communiquer c) {
		try {
			Communiquer communiquer = (Communiquer) em
					.createQuery("select c from Communiquer where c.Contenu_Communiquer="
							+ c.getContenuCommuniquer());
			System.out
			.println("communiquer existant déjà dans la base de données");
			return false;
		} catch (Exception e) {
			em.persist(c);
			System.out.println("communiquer enregistrée");
			return true;
		}
	}


	@Override
	public boolean enregistrerCours(Cours c) {
		try {
			Cours cours = (Cours) em
					.createQuery("select c from Cours where c.Code_Cours="
							+ c.getCode_Cours());
			System.out.println("cours existant déjà dans la base de données");
			return false;
		} catch (Exception e) {
			em.persist(c);
			System.out.println("cours enregistrée");
			return true;
		}
	}

	@Override
	public boolean enregistrerHoraire(Horaire h) {
		try {
			Horaire horaire = (Horaire) em
					.createQuery("select h from Horaire where h.heure="
							+ h.getHeure());
			System.out.println("date existant déjà dans la base de données");
			return false;
		} catch (Exception e) {
			em.persist(h);
			System.out.println("date enregistrée");
			return true;
		}
	}
	
	@Override
	public boolean enregistrerSalle(Salle salle) {
		try {
			Salle s= (Salle) em
					.createQuery("select s from Salle where s.codesalle="
							+ salle.getCodeSalle());
			System.out.println("salle existant déjà dans la base de données");
			return false;
		} catch (Exception e) {
			em.persist(salle);
			System.out.println("salle enregistrée");
			return true;
		}
	}

	@Override
	public boolean enregistrerPV(Proces_Verbaux pv) {
		try {
			Proces_Verbaux pv1 = (Proces_Verbaux) em
					.createQuery("select p from Proces_Verbaux where p.IntitulePv="
							+ pv.getIntitulePv());
			System.out
			.println("procès verbal existant déjà dans la base de données");
			return false;
		} catch (Exception e) {
			em.persist(pv);
			System.out.println("procès verbal enregistrée");
			return true;
		}
	}

	@Override
	public Horaire rechercheHoraire(String id) {
		try {
			Query q = em
					.createQuery("SELECT h FROM Horaire h WHERE h.Heure=:id");
			Horaire h = (Horaire) q.setParameter("id", id)
					.getSingleResult();

			return h;
		} catch (Exception e) {

			System.out
			.println("Horaire inconnus dans la BD \n\nmessage    "
					+ e.getMessage() + "\n\n erreur  " + e);
			return null;
		}
	}

	@Override
	public Administrateur rechercheAdministrateur(String id) {
		try {

			Query q = em
					.createQuery("SELECT a FROM Administrateur a WHERE a.identifiantAdmin=:id");
			Administrateur a = (Administrateur) q.setParameter("id", id)
					.getSingleResult();

			return a;
		} catch (Exception e) {

			System.out
			.println("Administrateur inconnus dans la BD \n\nmessage    "
					+ e.getMessage() + "\n\n erreur  " + e);
			return null;
		}
	}

	@Override
	public Enseignant rechercheEnseignant(String login) {
		try {
			System.out.println("\n"+login+"\n");
			Query q = em
					.createQuery("SELECT ens FROM Enseignant ens WHERE ens.identifiantEnseignant=:id");
			Enseignant ens = (Enseignant) q.setParameter("id", login)
					.getSingleResult();
			return ens;
		} catch (Exception e) {

			System.out.println("Enseignant inconnus dans la BD");
			return null;
		}
	}
	@Override
	public Salle rechercheSalle(String code) {
		try {
			System.out.println("\n"+code+"\n");
			Query q = em
					.createQuery("SELECT s FROM Salle s WHERE s.codeSalle=:id");
			Salle s = (Salle) q.setParameter("id", code)
					.getSingleResult();
			return s;
		} catch (Exception e) {

			System.out.println("Salle inconnus dans la BD");
			return null;
		}
	}


	@Override
	public Communiquer rechercheCommuniquer(int id) {
		try {
			Communiquer a = (Communiquer) em.find(Communiquer.class, id);

			return a;
		} catch (Exception e) {

			System.out.println("Communiquer inconnus dans la BD");
			return null;
		}
	}

	@Override
	public Cours rechercheCours(String code) {
		try {
			Query q = em
					.createQuery("SELECT ens FROM Cours ens WHERE ens.code_cours=:id");
			Cours ens = (Cours) q.setParameter("id", code).getSingleResult();
			return ens;
		} catch (Exception e) {

			System.out.println("Cours inconnus dans la BD\n"
					+ e.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public Proces_Verbaux recherchePV(int id) {
		try {
			Proces_Verbaux a = (Proces_Verbaux) em.find(Proces_Verbaux.class,
					id);

			return a;
		} catch (Exception e) {

			System.out.println("Procès verbaux inconnus dans la BD");
			return null;
		}
	}
	
	@Override
	public Proces_Verbaux recherchePV(String annee) {
		try {
			Query q = em
					.createQuery("SELECT pv FROM Proces_Verbaux pv WHERE pv.anneePv=:id");
			Proces_Verbaux pv = (Proces_Verbaux) q.setParameter("id", annee).getSingleResult();
			return pv;
		} catch (Exception e) {

			System.out.println("il n'ya pas de Proces verbaux dans la BD\n\nmessage    "
					+ e);
			return null;
		}
	}

	
	@Override
	public Administrateur rechercheAdministrateur(int id) {
		try {

			Query q = em
					.createQuery("SELECT a FROM Administrateur a WHERE a.IdAdmin=:id");
			Administrateur a = (Administrateur) q.setParameter("id", id)
					.getSingleResult();

			return a;
		} catch (Exception e) {

			System.out
			.println("Administrateur inconnus dans la BD \n\nmessage    "
					+ e.getMessage() + "\n\n erreur  " + e);
			return null;
		}
	}
	
	@Override
	public Enseignant rechercheEnseignant(int login) {
		try {
			System.out.println("\n"+login+"\n");
			Query q = em
					.createQuery("SELECT ens FROM Enseignant ens WHERE ens.IdEnseignant=:id");
			Enseignant ens = (Enseignant) q.setParameter("id", login)
					.getSingleResult();
			return ens;
		} catch (Exception e) {

			System.out.println("Enseignant inconnus dans la BD");
			return null;
		}
	}
	
	
	@Override
	public boolean modifieAdministrateur(Administrateur a) {
		try {
			Administrateur admin=this.rechercheAdministrateur(a.getIdentifiant_admin());
			admin.setNom_Admin(a.getNom_Admin());
			admin.setId_Admin(a.getId_Admin());
			em.merge(admin);
			return true;
		} catch (Exception e) {
			System.out.println("administrateur non exixtant dans la BD");
			return false;

		}
	}

	@Override
	public boolean modifieEnseignant(Enseignant e) {
		try {

			Enseignant ens=this.rechercheEnseignant(e.getIdentifiant_enseignant());
			ens.setGrade(e.getGrade());
			ens.setDiplome(e.getDiplome());
			ens.setPassword(e.getPassword().toUpperCase());
			ens.setCon(1);
			em.merge(ens);
			return true;
		} catch (Exception ec) {
			System.out.println("enseignant non exixtant dans la BD");
			return false;

		}
	}

	@Override
	public boolean modifieCommande(Commande c) {
		try {
			Commande com=this.rechercheCommande(c.getId_Commande());
			com.setDate_Commande(c.getDate_Commande());
			com.setObjet_Commande(c.getObjet_Commande());
	        com.setQte_Commande(c.getQte_Commande());
	        em.merge(com);
			return true;
		} catch (Exception ec) {
			System.out.println("commande non exixtant dans la BD");
			return false;

		}
	}

	@Override
	public boolean modifieCommuniquer(Communiquer c) {
		try {

			
			Communiquer com=this.rechercheCommuniquer(c.getId_Communiquer());
			if(com.getDate_Communiquer()!=c.getDate_Communiquer())
				com.setDate_Communiquer(c.getDate_Communiquer());
			if(com.getContenuCommuniquer()!=c.getContenuCommuniquer())
				com.setContenuCommuniquer(c.getContenuCommuniquer());
			if(com.getEtatCommunique()!=c.getEtatCommunique())	{	
				com.setEtatCommunique(c.getEtatCommunique());
				System.out.println("etat"+c.getEtatCommunique());
			}
			if(com.getDestination()!=c.getDestination())
				com.setDestination(c.getDestination());
			if(com.getemmeteur()!=c.getemmeteur())
				com.setemmeteur(c.getemmeteur());
			if(com.getObjetCommunique()!=c.getObjetCommunique())
				com.setObjetCommunique(c.getObjetCommunique());
			if(com.getSuprimeur()!=c.getSuprimeur())
				com.setSuprimeur(c.getSuprimeur());
			if(com.getLastdate()!=c.getLastdate())
				com.setLastdate(c.getLastdate());
			
			em.merge(com);
			return true;
		} catch (Exception ec) {
			System.out.println("communiqué non exixtant dans la BD");
			return false;

		}
	}
	@Override
	public boolean modifieCours(Cours c) {
		try {
			Cours cour = this.rechercheCours(c.getCode_Cours());
			cour.setEnseignant(c.getEnseignant());
			cour.setHoraire(c.getHoraire());
			cour.setIntitule_Cours(c.getIntitule_Cours());
			cour.setSalle(c.getSalle());
			cour.setNbCredit(c.getNbCredit());
			em.merge(cour);
			return true;
		} catch (Exception ec) {
			System.out.println("cours non exixtant dans la BD");
			return false;

		}
	}

	@Override
	public boolean modifieHoraire(Horaire h) {
		try {
			Horaire hor=this.rechercheHoraire(h.getHeure());
			hor.setHeure(h.getHeure());
			em.merge(hor);
			return true;
		} catch (Exception ec) {
			System.out.println("horaire non exixtant dans la BD");
			return false;

		}
	}

	@Override
	public boolean modifieSalle(Salle salle) {
		try {
			Salle s=this.rechercheSalle(salle.getCodeSalle());
			s.setNomSalle(salle.getNomSalle());
			em.merge(s);
			return true;
		} catch (Exception ec) {
			System.out.println("salle non exixtant dans la BD");
			return false;

		}
	}
	
	@Override
	public boolean modifiePV(Proces_Verbaux pv) {
		try {
			Proces_Verbaux prv=this.recherchePV(pv.getIdPv());
			prv.setAnneePv(pv.getAnneePv());
			prv.setMoisPv(pv.getMoisPv());
			prv.setJourPv(pv.getJourPv());
			prv.setSemestre(pv.getSemestre());
			prv.setHeure(pv.getHeure());
			prv.setIntitulePv(pv.getIntitulePv());
			em.merge(prv);
			return true;
		} catch (Exception ec) {
			System.out.println("proces verbal non exixtant dans la BD");
			return false;

		}
	}

	@Override
	public List<Enseignant> recupereListeEnseignant() {
		try {

			List<Enseignant> liste = em.createQuery(
					"SELECT ens FROM Enseignant ens ORDER BY daterecrutement").getResultList();
			return liste;
		} catch (Exception e) {

			System.out
			.println("il n'ya pas d'enseignant dans la BD \n\nmessage    "
					+ e.getMessage() + "\n\n erreur  " + e);
			return null;
		}
	}

	@Override
	public List<Horaire> recupereListeHoraire() {

		try {
			List<Horaire> liste1 = em.createQuery(
					"SELECT h FROM Horaire h").getResultList();
			return liste1;
		} catch (Exception e) {
			System.out
			.println("il n'ya pas d'Horaire dans la BD\n\nmessage    "
					+ e);
			return null;
		}
	}

	
	@Override
	public List<Salle> recupereSalle() {

		try {
			List<Salle> liste1 = em.createQuery(
					"SELECT s FROM Salle s").getResultList();
			return liste1;
		} catch (Exception e) {
			System.out
			.println("il n'ya pas d'Horaire dans la BD\n\nmessage    "
					+ e);
			return null;
		}
	}

	@Override
	public List<Cours> recupereCours(int niveau) {
		try {
			
			
			System.out.println("\n"+niveau+"\n");
			Query q = em
					.createQuery("SELECT c FROM Cours c WHERE c.Niveau=:id");
			List<Cours> c = (List<Cours>) q.setParameter("id",niveau)
					.getResultList();
			return c;
			
			
		} catch (Exception e) {

			System.out.println("pas de cours disponible pour ce niveau");
			return null;
		}
	}
	@Override
	public List<Cours> recupereCours(){
		try {
			List<Cours> liste1 = em.createQuery(
					"SELECT c FROM Cours c").getResultList();
			return liste1;
		} catch (Exception e) {
			System.out
			.println("il n'ya pas de cours dans la BD\n\nmessage    "
					+ e);
			return null;
		}
		
	}
	
	@Override
	public boolean enregistrerMateriel(Materiel m) {
		try {
			Materiel m1 =rechercheMateriel(m.getCode());
			if(m1==null){em.persist(m);System.out.println("materiel enregistré");return true;}
			else{
			
			m1.setQuantite(m1.getQuantite()+m.getQuantite());
			em.merge(m1);
			System.out
			.println("ce code est le code du materiel existant déjà dans la base de données l'ancienne quantite sera donc ajoutée à la nouvelle  "+m1.getQuantite()+"  "+m.getQuantite());
			return true;
			}
		} catch (Exception e) {
			
			System.out.println("materiel non enregistré");
			return false;
		}
	}

	@Override
	public boolean enregistrerMateriel2(Materiel m) {
		try {
			Materiel m1 =rechercheMateriel(m.getCode());
			if(m1==null){em.persist(m);System.out.println("materiel enregistré");return true;}
			else{
			int a=m.getQuantite();
			m1.setQuantite(a);
			em.merge(m1);
			System.out
			.println("ce code est le code du materiel existant déjà dans la base de données l'ancienne quantite sera donc ajoutée à la nouvelle  ");
			return true;
			}
		} catch (Exception e) {
			
			System.out.println("materiel non enregistré");
			return false;
		}
	}
	
	
	
	@Override
	public boolean enregistreCommande(Commande c) {
		
			try {
				Commande co = (Commande) em
						.createQuery("select c from Commande where c.IdCommande="
								+ c.getId_Commande());
				System.out
				.println("Commande existant déja dans la base de données");
				return false;
			} catch (Exception e) {
				em.persist(c);
				System.out.println("commande enregistrée avec succès");
				return true;
			}
	}

@Override
	public Commande rechercheCommande(int id) {
		try {
			Commande com = (Commande) em.find(Commande.class, id);

			return com;
		} catch (Exception e) {

			System.out.println("Commande inconnus dans la BD");
			return null;
		}
	}


@Override
	public Materiel rechercheMateriel(String code) {
		try {
			Query q = em
					.createQuery("SELECT ens FROM Materiel ens WHERE ens.Code=:id");
			Materiel ens = (Materiel) q.setParameter("id", code).getSingleResult();
			return ens;
		} catch (Exception e) {

			System.out.println("materiel inconnu dans la BD\n"
					+ e.getLocalizedMessage());
			return null;
		}
	}
	
	
	@Override
	public List<Materiel> recupereMateriel() {

		try {
			List<Materiel> mat1 = em.createQuery(
					"SELECT m FROM Materiel m").getResultList();
			return mat1;
		} catch (Exception e) {
			System.out
			.println("il n'ya pas de materiel dans la BD\n\nmessage    "
					+ e);
			return null;
		}
	}
	
	@Override
	public List<Commande> recupereCommande() {

		try {
			List<Commande> mat1 = em.createQuery(
					"SELECT m FROM Commande m").getResultList();
			return mat1;
		} catch (Exception e) {
			System.out
			.println("il n'ya pas de materiel dans la BD\n\nmessage    "
					+ e);
			return null;
		}
	}

	
	@Override
	public List<Proces_Verbaux> RecupereListePv() {

		try {
			List<Proces_Verbaux> mat1 = em.createQuery(
					"SELECT p FROM Proces_Verbaux p").getResultList();
			return mat1;
		} catch (Exception e) {
			System.out
			.println("il n'ya pas de Proces verbaux dans la BD\n\nmessage    "
					+ e);
			return null;
		}
	}
	
	
	public List<Communiquer> recupereCommuniquer(){
		try {
			List<Communiquer> liste1 = em.createQuery(
					"SELECT c FROM Communiquer c").getResultList();
			return liste1;
		} catch (Exception e) {
			System.out
			.println("il n'ya pas de Communiquer dans la BD\n\nmessage    "
					+ e);
			return null;
		}

	}


}
