package eprh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import beans.*;

import manager.InterfaceProjet;
import manager.ServeurProjet;

@WebServlet("/eprh")
public class EPRH extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String pageRetour = "/WEB-INF/index.jsp";
	DateTime date1 = new DateTime().toDateTime();
	DateTimeFormatter date2 = DateTimeFormat.forPattern("dd/MM/yyyy");
	String date = date1.toString(date2);

	// fin gestion date

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Properties ppt = null;
		Context ctx = null;
		ServeurProjet ref = null;
		InterfaceProjet home = null;


		request.setAttribute("date", date);
		String structure = "Département d'Economie Publique et Ressources Humaines (EPRH)";
		request.setAttribute("struc", structure);

		//gestion de la session
		HttpSession session = request.getSession();
		try {
			ppt = new Properties();
			ppt.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ppt.put(Context.PROVIDER_URL, "localhost:1099");
			ctx = new InitialContext(ppt);
			home = (InterfaceProjet) ctx.lookup("ServeurProjet/remote-Client");
			String form = request.getParameter("connection");
			if (form.equals("Connecter")) {
				// ici l'administrateur se connecte
				try {
					String identifiant = request.getParameter("identifiant");
					String pass = request.getParameter("password");
					Administrateur admin = home
							.rechercheAdministrateur(identifiant);
					if (!pass.equals(admin.getPassword())) {
						request.setAttribute(
								"erreur",
								new String(
										"erreur sur le mot de pass ou sur l'identifiant"));
						pageRetour = "/WEB-INF/principal/index.jsp";
					} else {
						session.setAttribute("admin",admin);
						request.setAttribute("idadmin",
								admin.getIdentifiant_admin());
						request.setAttribute("nom", admin.getNom_Admin());
						pageRetour = "/WEB-INF/Menu.jsp";
					}
				} catch (Exception ex) {
					request.setAttribute("erreur", new String(
							"administrateur non reconnu"));
					pageRetour = "/WEB-INF/principal/index.jsp";
				}
			}
			if (form.equals("Enregistrer")) {
				// ici on enregistre un enseignant
				Administrateur admin1=(Administrateur) session.getAttribute("admin");
				try {

					String diplome=request.getParameter("diplome");
					String jour=request.getParameter("jour");
					String mois=request.getParameter("mois");
					String annee=request.getParameter("annee");
					String grade=request.getParameter("grade");
					String nome = request.getParameter("nome");
					String login = request.getParameter("login");
					String pass = request.getParameter("pass");
					Enseignant ens = home.rechercheEnseignant(login);
					if (ens == null) {
						String s=annee+"-"+mois+"-"+jour;
						Administrateur admin = home.rechercheAdministrateur(admin1.getIdentifiant_admin());
						Enseignant enseignant = new Enseignant();
						enseignant.setAdministrateur(admin);
						enseignant.setIdentifiant_enseignant(login);
						enseignant.setDateRecrutement(s);
						enseignant.setDiplome(diplome);
						enseignant.setGrade(grade);
						enseignant.setPassword(pass);
						enseignant.setNom_Enseignant(nome);
						home.enregistreEnseignant(enseignant);
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"enseignant enregistré avec succès"));
						pageRetour = "/WEB-INF/Menu.jsp";
					} else {
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"enseignant existant déja dans le système"));
						pageRetour = "/WEB-INF/Menu.jsp";
					}
				} catch (Exception ex) {
					session.setAttribute("admin",admin1);
					request.setAttribute("resultat1", new String(
							"impossible d'enregistrer l'enseignant"));
					pageRetour = "/WEB-INF/Menu.jsp";
				}
			}

			if (form.equals("Modifier")) {
				// ici on modifie les informations d'un enseignant
				Administrateur admin1=(Administrateur) session.getAttribute("admin");
				try {

					String diplome=request.getParameter("diplome");
					String grade=request.getParameter("grade");

					String login = request.getParameter("login");
					String pass = request.getParameter("pass");
					Enseignant ens = home.rechercheEnseignant(login);
					if (ens != null) {
						Administrateur admin = home.rechercheAdministrateur(admin1.getIdentifiant_admin());
						ens.setAdministrateur(admin);
						ens.setPassword(pass);
						ens.setGrade(grade);
						ens.setDiplome(diplome);

						home.modifieEnseignant(ens);
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat2", new String(
								"enseignant modifié avec succès"));
						pageRetour = "/WEB-INF/Menu.jsp";
					} else {
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat2", new String(
								"modification de l'enseignant echouée"));
						pageRetour = "/WEB-INF/Menu.jsp";
					}
				} catch (Exception ex) {
					session.setAttribute("admin",admin1);
					request.setAttribute("resultat2", new String(
							"enseignant non reconnu"));
					pageRetour = "/WEB-INF/Menu.jsp";
				}
			}

			if (form.equals("Creer")) {
				// ici on crèe un cours
				Administrateur admin1=(Administrateur) session.getAttribute("admin");
				try {

					String heure=request.getParameter("heure");
					String code = request.getParameter("codec");
					String intitule = request.getParameter("intitule");
					int niveau = new Integer(request.getParameter("niveau"));
					String enseignant = request.getParameter("enseignant");
					Cours cour = home.rechercheCours(code);
					if (cour == null) {
						Horaire ho=home.rechercheHoraire(heure);
						Enseignant ens = home.rechercheEnseignant(enseignant);
						Cours c = new Cours();
						c.setCode_Cours(code);
						if (code.equals(null)) {
							System.out.println("le code de ce cours est null");
						} else {
							c.setCode_Cours(code);
							System.out.println("le code de ce cours est  "
									+ c.getCode_Cours());
						}
						c.setIntitule_Cours(intitule);
						c.setNiveau(niveau);
						c.setEnseignant(ens);
						c.setHoraire(ho);
						home.enregistrerCours(c);
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat3", new String(
								"Cours enregistré avec succès"));
						pageRetour = "/WEB-INF/Menu.jsp";

					} else {
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat3", new String(
								"Ce cour existe déja dans le sytème"));
						pageRetour = "/WEB-INF/Menu.jsp";
					}
				} catch (Exception ex) {
					session.setAttribute("admin",admin1);
					request.setAttribute("resultat3", new String(
							"impossible d'enregistrer le cours"));
					pageRetour = "/WEB-INF/Menu.jsp";
				}
			}
			if (form.equals("Valider")) {
				// ici on modifie les informations d'un cours
				Administrateur admin1=(Administrateur) session.getAttribute("admin");
				try {

					String code = request.getParameter("code");
					String intitule = request.getParameter("intitule");
					String enseignants = request.getParameter("enseignant");
					String heure=request.getParameter("heure");

					Cours cours = home.rechercheCours(code);
					request.setAttribute("cours",cours);
					if (cours != null) {
						cours.setIntitule_Cours(intitule);
						Enseignant ens=home.rechercheEnseignant(enseignants); 
						cours.setEnseignant(ens);
						Horaire ho=home.rechercheHoraire(heure);
						cours.setHoraire(ho);
						home.modifieCours(cours);
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat4", new String(
								"cours modifié avec succès"));
						pageRetour = "/WEB-INF/Menu.jsp";
					} else {
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat4", new String(
								"modification du cours echouée"));
						pageRetour = "/WEB-INF/Menu.jsp";
					}
				} catch (Exception ex) {
					session.setAttribute("admin",admin1);
					request.setAttribute("resultat4", new String(
							"cours non reconnu"));
					pageRetour = "/WEB-INF/Menu.jsp";
				}
			}
			if (form.equals("OK")) {
				// ici on crèe une horaire
				Administrateur admin1=(Administrateur) session.getAttribute("admin");
				try {

					String jour=request.getParameter("jour");
					String debut=request.getParameter("debut");
					String fin=request.getParameter("fin");
					if(debut.equals(fin)){
						request.setAttribute("erreur", new String(
								"vérifiez les données que vous avez entrée"));
						pageRetour = "/WEB-INF/creerHoraire.jsp";

					}
					else{
						String ensemble=jour+" : "+debut+" heures-"+fin+" heures";
						Horaire h=home.rechercheHoraire(ensemble);
						if(h==null){
							h=new Horaire();
							h.setHeure(ensemble);
							home.enregistrerHoraire(h);
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat15", new String(
									"Horaire enregistré avec succès"));
							pageRetour = "/WEB-INF/Menu.jsp";

						} else {
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat15", new String(
									"Cette horaire existe déja dans le sytème"));
							pageRetour = "/WEB-INF/Menu.jsp";
						}
					}
				} catch (Exception ex) {
					session.setAttribute("admin",admin1);
					request.setAttribute("erreur", new String(
							"vérifiez les données que vous avez entrée"));
					pageRetour = "/WEB-INF/creerHoraire.jsp";
				}
			}
			if (form.equals("Consulter")) {
				// ici on consulte l'emploi du temps d'un personnel
				Administrateur admin1=(Administrateur) session.getAttribute("admin");
				try {

					String enseignant=request.getParameter("enseignant");
					Enseignant ens=home.rechercheEnseignant(enseignant);
					if(ens!=null){
						List<Cours> cours=(List<Cours>)ens.getCours();
						int n=cours.size();
						List<String> result=new ArrayList<String>();
						String ligne=new String();
						int i=0;
						while(i<n){
							ligne=cours.get(i).getIntitule_Cours()+";"+cours.get(i).getCode_Cours()+";"+cours.get(i).getNiveau()+";"+cours.get(i).getHoraire().getHeure();
							result.add(ligne);
							i++;
						}  
						request.setAttribute("liste",result);
						request.setAttribute("nb",n);
						request.setAttribute("enseignant",ens.getNom_Enseignant());
						session.setAttribute("admin", admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						pageRetour = "/WEB-INF/Emploi2.jsp";

					} else {
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat12", new String(
								"Enseignant inexistant dans la base de données"));
						pageRetour = "/WEB-INF/Menu.jsp";
					}
				} catch (Exception ex) {
					session.setAttribute("admin",admin1);
					request.setAttribute("erreur", new String(
							"enseignant non reconnu dans le système"));
					pageRetour = "/WEB-INF/Emploi1.jsp";
				}
			}

			this.getServletContext().getRequestDispatcher(pageRetour)
			.forward(request, response);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
				pageRetour="/WEB-INF/index.jsp";
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
				case 1://création d'un personnel
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/enregistrePersonnel.jsp";
					break;
				case 2://modification d'un personnel
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/modifiePersonnel.jsp";
					break;
				case 3://création d'un cours
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
					List<String> heure = new ArrayList<String>();
					for (int i = 0; i < m; i++) {
						String tout=horaire.get(i).getHeure();
						heure.add(tout);

					}
					request.setAttribute("heure",heure);
					request.setAttribute("listeenseignant", listeenseignant);
					request.setAttribute("idenseignant", idenseignant);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/creerCour.jsp";
					break;
				case 4://mise à jour d'un cours
					session.setAttribute("admin",admin);
					liste = (List<Enseignant>) home.recupereListeEnseignant();
					idenseignant = new ArrayList<String>();
					n = liste.size();
					for (int i = 0; i < n; i++)
						idenseignant.add(liste.get(i).getIdentifiant_enseignant());
					horaire = (List<Horaire>) home
							.recupereListeHoraire();
					m = horaire.size();

					heure = new ArrayList<String>();
					for (int i = 0; i < m; i++) {
						String tout=horaire.get(i).getHeure();
						heure.add(tout);

					}


					request.setAttribute("heure", horaire);

					request.setAttribute("idenseignant", idenseignant);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/MiseAJourCours.jsp";
					break;
				case 15://création d'une nouvelle horaire
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/creerHoraire.jsp";
					break;
				case 12://consultation de l'emploi du temps d'un enseignant
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/Emploi1.jsp";
					break;
				case 16://consultation du dossier du personnel
					session.setAttribute("admin",admin);
					List<Enseignant> listens=home.recupereListeEnseignant();
					n=listens.size();
					int i=0;
					List<String> envoi=new ArrayList<String>();
					while(i<n){
						Enseignant ens=listens.get(i);
						int k=i+1;
						String s="<center><font color=\"red\" size=6>"+k+"</font></center><br><b><font color=\"blue\" >Noms et prénoms:</font></b> "+ens.getNom_Enseignant()+";<b><font color=\"blue\">Identifiant:</font></b> "+ens.getIdentifiant_enseignant()
								+";<b><font color=\"blue\">Date de Recrutement:</font></b> "+ens.getDateRecrutement()+";<b><font color=\"blue\" >Diplôme le plus élevé: </font></b>"+ens.getDiplome()
								+";<b><font color=\"blue\">Grade:</font></b> "
								+ens.getGrade();
						envoi.add(s);
						i++;
					}
					request.setAttribute("liste",envoi);
					request.setAttribute("nb",n);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/DossierPersonnel.jsp";
					break;
				default:
					pageRetour = "/WEB-INF/index.jsp";

				}

				this.getServletContext().getRequestDispatcher(pageRetour)
				.forward(request, response);
			}
		} catch (Exception e) {

		}
	}

}
