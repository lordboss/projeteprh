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

@WebServlet("/enseignant")
public class enseignant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String pageRetour = "/WEB-INF/principal/index.jsp";
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
				if (form.equals("Enregistrer")) {
					// ici on enregistre un enseignant
					try {
						String pass = request.getParameter("pass").toUpperCase();
						String pass1 = request.getParameter("pass1").toUpperCase();
						String diplome=request.getParameter("diplome").toUpperCase();
						String jour=request.getParameter("jour");
						String mois=request.getParameter("mois");
						String annee=request.getParameter("annee");
						String grade=request.getParameter("grade").toUpperCase();
						String nome = request.getParameter("nome").toUpperCase();
						String login = request.getParameter("login").toUpperCase();
						if(!pass.equals(pass1)){
							request.setAttribute("nome",nome);
							request.setAttribute("login",login);
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("pass", new String(
									"<font color=\"red\">les mots de pass entrés doivent être identique</font>"));
							pageRetour = "/WEB-INF/principal/enseignant/Menu.jsp";
						}
						else{
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
										"<font color=\"red\">enseignant enregistré avec succès</font>"));
								pageRetour = "/WEB-INF/principal/enseignant/Menu.jsp";
							} else {
								session.setAttribute("admin",admin1);
								request.setAttribute("nom",admin1.getNom_Admin());
								request.setAttribute("resultat1", new String(
										"<font color=\"red\">enseignant existant déja dans le système</font>"));
								pageRetour = "/WEB-INF/principal/enseignant/Menu.jsp";
							}
						}
					} catch (Exception ex) {
						session.setAttribute("admin",admin1);
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible d'enregistrer l'enseignant</font>"));
						pageRetour = "/WEB-INF/principal/enseignant/Menu.jsp";
					}
				}
				if (form.equals("Modifier")) {
					// ici on modifie les informations d'un enseignant
					try {

						String diplome=request.getParameter("diplome").toUpperCase();
						String grade=request.getParameter("grade").toUpperCase();

						String login = request.getParameter("login").toUpperCase();
						
						
						Enseignant ens = home.rechercheEnseignant(login);
						if (ens != null) {
							Administrateur admin = home.rechercheAdministrateur(admin1.getIdentifiant_admin());
							ens.setAdministrateur(admin);
							
							ens.setGrade(grade);
							ens.setDiplome(diplome);

							home.modifieEnseignant(ens);
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat2", new String(
									"enseignant modifié avec succès"));
							pageRetour = "/WEB-INF/principal/enseignant/modifiePersonnel.jsp";
						} else {
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat2", new String(
									"modification de l'enseignant echouée"));
							pageRetour = "/WEB-INF/principal/enseignant/modifiePersonnel.jsp";
						}
						
					} catch (Exception ex) {
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat2", new String(
								"enseignant non reconnu"));
						pageRetour = "/WEB-INF/principal/enseignant/modifiePersonnel.jsp";
					}
				}
				if (form.equals("Consulter")) {
					// ici on consulte l'emploi du temps d'un personnel
					try {

						String enseignant=request.getParameter("enseignant").toUpperCase();
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
							pageRetour = "/WEB-INF/principal/enseignant/Emploi2.jsp";

						} else {
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("erreur", new String(
									"Enseignant inexistant dans la base de données"));
							pageRetour = "/WEB-INF/principal/enseignant/Emploi1.jsp";
						}
					} catch (Exception ex) {
						session.setAttribute("admin",admin1);
						request.setAttribute("erreur", new String(
								"enseignant non reconnu dans le système"));
						pageRetour = "/WEB-INF/principal/enseignant/Emploi1.jsp";
					}
				}
				if (form.equals("Dossier")) {
					// ici on consulte le dossier d'un enseignant unique
					try {

						String enseignant=request.getParameter("enseignant").toUpperCase();
						Enseignant ens=home.rechercheEnseignant(enseignant);
						String g=
								"<table width=\"60%\" class=\"tx-sharecms-pi1\">" +
								"<tr><td align=\"left\"><font color=\"black\" size=4>Noms et prénoms:</font></td>"+
								"<td align=\"left\">"+ens.getNom_Enseignant()+"</td></tr>"+
								"<tr><td align=\"left\"><font color=\"black\" size=4>Identifiant: </td>"+
								"<td align=\"left\">"+ens.getIdentifiant_enseignant()+"</td></tr>"+
								"<tr><td align=\"left\"><font color=\"black\" size=4>Date de Recrutement:</font> </td>"+
								"<td align=\"left\">"+ens.getDateRecrutement()+"</td></tr>"+
								"<tr><td align=\"left\"><font color=\"black\" size=4>Dîplome le plus élevé:</font> </td>"+
								"<td align=\"left\">"+ens.getDiplome()+"</td></tr>"+
								"<tr><td align=\"left\"><font color=\"black\" size=4>Grade:</font> </td>"+
								"<td align=\"left\">"+ens.getGrade()+"</td></tr>" +
//								"<tr><td align=\"left\"><font color=\"black\" size=4>Cours enseignés:</font> </td>"+
//								"<td align=\"left\">"+s+"</td></tr>"+
								"</table>";
							request.setAttribute("enseignant",ens);
							request.setAttribute("retour",g);
							request.setAttribute("enseignant",ens.getNom_Enseignant());
							session.setAttribute("admin", admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							pageRetour = "/WEB-INF/principal/enseignant/ficheEnseignant.jsp";

					} catch (Exception ex) {
						session.setAttribute("admin",admin1);
						request.setAttribute("erreur", new String(
								"enseignant non reconnu dans le système"));
						pageRetour = "/WEB-INF/principal/enseignant/Emploi1.jsp";
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
				case 1://menu principal/enseignant
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour="/WEB-INF/principal/enseignant/Menu.jsp";
					break;
				case 2://modification d'un personnel
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/enseignant/modifiePersonnel.jsp";
					break;
				case 3://consultation de l'emploi du temps d'un enseignant
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/enseignant/Emploi1.jsp";
					break;
				case 4://consultation du dossier du personnel
					session.setAttribute("admin",admin);
					List<Enseignant> listens=home.recupereListeEnseignant();
					int n=listens.size();
					int i=0;
					List<String> envoi=new ArrayList<String>();
					if(n==0){
						String ui="il n'y a pas d'enseignants dans le système ";
						envoi.add(ui);
					}
					else{
						while(i<n){
							Enseignant ens=listens.get(i);
							int k=i+1;
							List<Cours> cor=ens.getCours();
							int l=cor.size();
							String s="";
							if(l==0)
								s="aucun";
							else{
								int h=0;

								while(h<l){
									s=s+","+cor.get(h).getCode_Cours();
									h++;
								}
							}

							String g="<center><font  color=\"red\" size=6>"+k+"</font></center><br>"+
									"<table width=\"60%\" class=\"tx-sharecms-pi1\">" +
									"<tr><td align=\"left\"><font color=\"black\" size=4>Noms et prénoms:</font></td>"+
									"<td align=\"left\">"+ens.getNom_Enseignant()+"</td></tr>"+
									"<tr><td align=\"left\"><font color=\"black\" size=4>Identifiant: </td>"+
									"<td align=\"left\">"+ens.getIdentifiant_enseignant()+"</td></tr>"+
									"<tr><td align=\"left\"><font color=\"black\" size=4>Date de Recrutement:</font> </td>"+
									"<td align=\"left\">"+ens.getDateRecrutement()+"</td></tr>"+
									"<tr><td align=\"left\"><font color=\"black\" size=4>Dîplome le plus élevé:</font> </td>"+
									"<td align=\"left\">"+ens.getDiplome()+"</td></tr>"+
									"<tr><td align=\"left\"><font color=\"black\" size=4>Grade:</font> </td>"+
									"<td align=\"left\">"+ens.getGrade()+"</td></tr>" +
									"<tr><td align=\"left\"><font color=\"black\" size=4>Cours enseignés:</font> </td>"+
									"<td align=\"left\">"+s+"</td></tr>"+
									"</table>";
							envoi.add(g);
							i++;
						}
					}
					request.setAttribute("liste",envoi);
					request.setAttribute("nb",n);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/enseignant/DossierPersonnel.jsp";
					break;
				case 5://consultation du dossier d'un enseignant
					session.setAttribute("admin",admin);
					listens=home.recupereListeEnseignant();
					n=listens.size();
					i=0;
					envoi=new ArrayList<String>();
					if(n==0){
						String ui="il n'y a pas d'enseignants dans le système ";
						envoi.add(ui);
					}
					else{
						List<String> idenseignant=new ArrayList<String>();
						for (int p = 0; p < n; p++)
							idenseignant.add(listens.get(p).getIdentifiant_enseignant());
						request.setAttribute("idenseignant",idenseignant);
					}
					
					request.setAttribute("nb",n);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/enseignant/fiche.jsp";
					break;

				default:
					pageRetour = "/WEB-INF/principal/index.jsp";
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
