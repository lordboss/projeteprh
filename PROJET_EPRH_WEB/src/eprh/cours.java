package eprh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.InterfaceProjet;
import manager.ServeurProjet;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import beans.*;

@WebServlet("/cours")
public class cours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String pageRetour = "/WEB-INF/principal/cours/creerCours.jsp";
	DateTime date1 = new DateTime().toDateTime();
	DateTimeFormatter date2 = DateTimeFormat.forPattern("dd/MM/yyyy");
	String date = date1.toString(date2);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties ppt = null;
		Context ctx = null;
		ServeurProjet ref = null;
		InterfaceProjet home = null;
		pageRetour="/WEB-INF/principal/index.jsp";

		request.setAttribute("date", date);

		//gestion de la session
		HttpSession session = request.getSession();
		try{
			ppt = new Properties();
			ppt.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ppt.put(Context.PROVIDER_URL, "localhost:1099");
			ctx = new InitialContext(ppt);
			home = (InterfaceProjet) ctx.lookup("ServeurProjet/remote-Client");
			Administrateur admin1=(Administrateur)session.getAttribute("admin");
			if(admin1==null){
				request.setAttribute("erreur","vos paramètres d'authentification ne sont pas valide");
				pageRetour="/WEB-INF/principal/index.jsp";
			}
			else{
				String form = request.getParameter("connection");

				if (form.equals("Creer")) {
					// ici on crèe un cours

					try {
						int nbCredit=new Integer(request.getParameter("credit"));
						String salle=request.getParameter("salle").toUpperCase();
						String heure=request.getParameter("heure");
						String code = request.getParameter("codec").toUpperCase();
						String intitule = request.getParameter("intitule").toUpperCase();
						int niveau = new Integer(request.getParameter("niveau"));
						String enseignant = request.getParameter("enseignant").toUpperCase();
						Cours cour = home.rechercheCours(code);
						if (cour == null) {
							Horaire ho=home.rechercheHoraire(heure);
							Enseignant ens = home.rechercheEnseignant(enseignant);
							Salle s=home.rechercheSalle(salle);
							Cours c = new Cours();
							c.setCode_Cours(code);
							if (code.equals(null)) {
								System.out.println("le code de cette salle est null");
							} else {
								c.setCode_Cours(code);
								c.setSalle(s);
								System.out.println("le code de cette salle est  "
										+ s.getCodeSalle());
							}
							c.setNbCredit(nbCredit);
							c.setIntitule_Cours(intitule);
							c.setNiveau(niveau);
							c.setSalle(s);
							c.setEnseignant(ens);
							c.setHoraire(ho);
							home.enregistrerCours(c);
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat1", new String(
									"<font color=\"red\">Cours enregistré avec succès</font>"));
							pageRetour = "/WEB-INF/principal/cours/creerCours.jsp";

						} else {
							request.setAttribute("salle",session.getAttribute("salle"));
							request.setAttribute("heure",session.getAttribute("heure"));
							request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
							request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat1", new String(
									"<font color=\"red\">Ce cour existe déja dans le sytème"));
							pageRetour = "/WEB-INF/principal/cours/creerCours.jsp";
						}
					} catch (Exception ex) {
						
						request.setAttribute("salle",session.getAttribute("salle"));
						request.setAttribute("heure",session.getAttribute("heure"));
						request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
						request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible d'enregistrer le cours</font>"));
						pageRetour = "/WEB-INF/principal/cours/creerCours.jsp";
					}
				}
				if (form.equals("Modifier")) {
					// ici on modifie les informations d'un cours
					try {
						int credit=new Integer(request.getParameter("credit"));
						String sal=request.getParameter("salle").toUpperCase();
						String code = request.getParameter("code").toUpperCase();
						String intitule = request.getParameter("intitule").toUpperCase();
						String enseignants = request.getParameter("enseignant").toUpperCase();
						String heure=request.getParameter("heure").toUpperCase();

						Cours cours = home.rechercheCours(code);
						request.setAttribute("cours",cours);
						if (cours != null) {

							cours.setIntitule_Cours(intitule);
							Enseignant ens=home.rechercheEnseignant(enseignants); 
							cours.setEnseignant(ens);
							Horaire ho=home.rechercheHoraire(heure);
							cours.setHoraire(ho);
							Salle sa=home.rechercheSalle(sal);
							cours.setSalle(sa);
							cours.setNbCredit(credit);
							home.modifieCours(cours);
							request.setAttribute("codecour",session.getAttribute("codecour"));
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat2", new String(
									"<font color=\"red\">cours modifié avec succès</font>"));
							pageRetour = "/WEB-INF/principal/cours/MiseAJourCours.jsp";
						} else {
							request.setAttribute("codecour",session.getAttribute("codecour"));
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat2", new String(
									"<font color=\"red\">modification du cours echouée</font>"));
							pageRetour = "/WEB-INF/principal/cours/MiseAJourCours.jsp";
						}
					} catch (Exception ex) {
						request.setAttribute("codecour",session.getAttribute("codecour"));
						session.setAttribute("admin",admin1);
						request.setAttribute("resultat2", new String(
								"<font color=\"red\">cours non reconnu</font>"));
						pageRetour = "/WEB-INF/principal/cours/MiseAJourCours.jsp";
					}
				}
				if (form.equals("Consulter")) {
					// ici on affiche la grille des uv d'un niveau donné
					try {
						int niveau=new Integer(request.getParameter("niveau"));

						List<Cours> cours = home.recupereCours(niveau);
						int o=cours.size();
						if (o > 0) {
							List<String> result=new ArrayList<String>();
							String ligne=new String();
							int i=0;
							while(i<o){
								ligne=cours.get(i).getIntitule_Cours()+";"+cours.get(i).getCode_Cours()+";"+cours.get(i).getNbCredit()+";"+cours.get(i).getHoraire().getHeure();
								result.add(ligne);
								i++;
							}  
							request.setAttribute("liste",result);
							request.setAttribute("niveau",niveau);
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());

							pageRetour = "/WEB-INF/principal/cours/Grille2.jsp";
						} else {
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat5", new String(
									"<font color=\"red\">Il n'existe aucun cours pour ce niveau</font>"));
							pageRetour = "/WEB-INF/principal/cours/Grille1.jsp";
						}
					} catch (Exception ex) {
						session.setAttribute("admin",admin1);
						request.setAttribute("resultat2", new String(
								"<font color=\"red\">Impossible d'afficher la grille des UV pour ce niveau</font>"));
						pageRetour = "/WEB-INF/principal/cours/Grille2.jsp";
					}
				}
				if (form.equals("OK")) {
					// ici on crèe une horaire
					try {
                        
						String jour=request.getParameter("jour").toUpperCase();
						int debut=new Integer(request.getParameter("debut"));
						int fin=new Integer(request.getParameter("fin"));
						if(debut>=fin){
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat3", new String(
									"vérifiez les données que vous avez entrée"));
							pageRetour = "/WEB-INF/principal/cours/creerHoraire.jsp";

						}
						else{
							String ensemble=jour+" : "+debut+" heures-"+fin+" heures";
							Horaire h=home.rechercheHoraire(ensemble);
							if(h==null){
								h=new Horaire();
								h.setHeure(ensemble);
								home.enregistrerHoraire(h);
								List<Horaire>horaire = (List<Horaire>) home
										.recupereListeHoraire();
								int m = horaire.size();
								List<String>heure = new ArrayList<String>();
								for (int i = 0; i < m; i++) {
									String tout=horaire.get(i).getHeure();
									heure.add(tout);

								}
								session.setAttribute("heure",heure);
								session.setAttribute("admin",admin1);
								request.setAttribute("nom",admin1.getNom_Admin());
								request.setAttribute("resultat3", new String(
										"Horaire enregistré avec succès"));
								pageRetour = "/WEB-INF/principal/cours/creerHoraire.jsp";

							} else {
								request.setAttribute("heure",session.getAttribute("heure"));
								request.setAttribute("salle",session.getAttribute("salle"));
								request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
								request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
								session.setAttribute("admin",admin1);
								request.setAttribute("nom",admin1.getNom_Admin());
								request.setAttribute("resultat3", new String(
										"Cette horaire existe déja dans le sytème"));
								pageRetour = "/WEB-INF/principal/cours/creerHoraire.jsp";
							}
						}
					} catch (Exception ex) {
						request.setAttribute("heure",session.getAttribute("heure"));
						request.setAttribute("salle",session.getAttribute("salle"));
						request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
						request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
						session.setAttribute("admin",admin1);
						request.setAttribute("erreur", new String(
								"vérifiez les données que vous avez entrée"));
						pageRetour = "/WEB-INF/creerHoraire.jsp";
					}
				}
				if (form.equals("Valider")) {
					// ici on crèe une salle

					try {

						String name=request.getParameter("name").toUpperCase();
						String code = request.getParameter("code").toUpperCase();
						Salle salle = home.rechercheSalle(code);
						if (salle == null) {
							salle=new Salle();
							salle.setCodeSalle(code);
							salle.setNomSalle(name);
							home.enregistrerSalle(salle);
							List<Salle> sall = (List<Salle>) home.recupereSalle();
							int po = sall.size();
							List<String> sal = new ArrayList<String>();
							for (int i = 0; i < po; i++) {
								String tout=sall.get(i).getCodeSalle();
								sal.add(tout);
							}
							session.setAttribute("salle",sal);
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat4", new String(
									"<font color=\"red\">Salle enregistrée avec succès</font>"));
							pageRetour = "/WEB-INF/principal/cours/NewSalle.jsp";

						} else {
							request.setAttribute("salle",session.getAttribute("salle"));
							request.setAttribute("heure",session.getAttribute("heure"));
							request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
							request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat4", new String(
									"<font color=\"red\">Cette salle existe déja dans le sytème"));
							pageRetour = "/WEB-INF/principal/cours/NewSalle.jsp";
						}
					} catch (Exception ex) {
						request.setAttribute("heure",session.getAttribute("heure"));
						request.setAttribute("salle",session.getAttribute("salle"));
						request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
						request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat4", new String(
								"<font color=\"red\">impossible d'enregistrer la salle</font>"));
						pageRetour = "/WEB-INF/principal/cours/NewSalle.jsp";
					}
				}
			}
			this.getServletContext().getRequestDispatcher(pageRetour)
			.forward(request, response);
		}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties ppt = null;
		Context ctx = null;
		ServeurProjet ref = null;
		InterfaceProjet home = null;

		request.setAttribute("date", date);
		String structure = "Département d'Economie Publique et Ressources Humaines (EPRH)";
		request.setAttribute("struc", structure);

		//gestion de la session
		HttpSession session = request.getSession();
		Administrateur admin=(Administrateur) session.getAttribute("admin");
		try {
			if(admin==null){
				pageRetour="/WEB-INF/principal/index.jsp";
			}
			else{
				session.setAttribute("admin",admin);
				ppt = new Properties();
				ppt.put(Context.INITIAL_CONTEXT_FACTORY,
						"org.jnp.interfaces.NamingContextFactory");
				ppt.put(Context.PROVIDER_URL, "localhost:1099");
				ctx = new InitialContext(ppt);
				home = (InterfaceProjet) ctx.lookup("ServeurProjet/remote-Client");

				int action = new Integer(request.getParameter("action"));
				switch (action) {	
				case 1://menu principal/cours
					session.setAttribute("admin",admin);
					request.setAttribute("salle",session.getAttribute("salle"));
					request.setAttribute("heure",session.getAttribute("heure"));
					request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
					request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/cours/creerCours.jsp";
					break;
				case 2://mise à jour d'un cours
					List<Cours> cour=home.recupereCours();
					int pi = cour.size();
					List<String> cou = new ArrayList<String>();
					for (int i = 0; i < pi; i++) {
						String tout=cour.get(i).getCode_Cours();
						cou.add(tout);
					}
					request.setAttribute("codecour",cou);
					session.setAttribute("codecour", cou);
					session.setAttribute("admin",admin);
					request.setAttribute("salle",session.getAttribute("salle"));
					request.setAttribute("heure",session.getAttribute("heure"));
					request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
					request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/cours/MiseAJourCours.jsp";
					break;
				case 3://création d'une nouvelle horaire
					session.setAttribute("admin",admin);
					request.setAttribute("salle",session.getAttribute("salle"));
					request.setAttribute("heure",session.getAttribute("heure"));
					request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
					request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/cours/creerHoraire.jsp";
					break;
				case 4://création d'une nouvelle salle de classe
					session.setAttribute("admin",admin);
					request.setAttribute("salle",session.getAttribute("salle"));
					request.setAttribute("heure",session.getAttribute("heure"));
					request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
					request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/cours/NewSalle.jsp";
					break;

				case 5://consultation de la grille d'UV
					session.setAttribute("admin",admin);
					request.setAttribute("salle",session.getAttribute("salle"));
					request.setAttribute("heure",session.getAttribute("heure"));
					request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
					request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/cours/Grille1.jsp";
					break;
				default:
					session.setAttribute("admin",admin);
					List<Enseignant> liste = (List<Enseignant>) home
							.recupereListeEnseignant();
					List<String> listeenseignant = new ArrayList<String>();
					List<String> idenseignant = new ArrayList<String>();
					int n = liste.size();
					for (int i = 0; i < n; i++)
						listeenseignant.add(liste.get(i).getNom_Enseignant());
					for (int i = 0; i < n; i++)
						idenseignant.add(liste.get(i).getIdentifiant_enseignant());
					List<Horaire> horaire = (List<Horaire>) home
							.recupereListeHoraire();
					int m = horaire.size();
					List<String>heure = new ArrayList<String>();
					for (int i = 0; i < m; i++) {
						String tout=horaire.get(i).getHeure();
						heure.add(tout);

					}
					List<Salle> salle = (List<Salle>) home.recupereSalle();
					int po = salle.size();
					List<String> sal = new ArrayList<String>();
					for (int i = 0; i < po; i++) {
						String tout=salle.get(i).getCodeSalle();
						sal.add(tout);
					}
					session.setAttribute("salle",sal);
					session.setAttribute("heure",heure);
					session.setAttribute("listeenseignant",listeenseignant);
					session.setAttribute("idenseignant",idenseignant);
					request.setAttribute("heure",heure);
					request.setAttribute("listeenseignant", listeenseignant);
					request.setAttribute("idenseignant", idenseignant);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/cours/creerCours.jsp";
				}




			}
			this.getServletContext().getRequestDispatcher(pageRetour)
			.forward(request, response);
		}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
