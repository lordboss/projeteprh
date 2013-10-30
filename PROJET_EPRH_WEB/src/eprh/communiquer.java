package eprh;

import java.io.IOException;
//jPDFWriter
import java.util.ArrayList;
import java.util.Date;
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

@WebServlet("/communiquer")
public class communiquer<C> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Date NULL = null;
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

						DateTime date1 = new DateTime().toDateTime();
						DateTimeFormatter date2 = DateTimeFormat.forPattern("dd/MM/yyyy");
						String datecom = date1.toString(date2);
						String ContenuCom=request.getParameter("contenuc").toUpperCase();
						String Destinationcom = request.getParameter("destinataire").toUpperCase();
						String ObjetCom = request.getParameter("objetc").toUpperCase();
						System.out.println("merci justo");
						String EtatCom ="en cour";
						System.out.println("merci justo");
						int emmeteur=admin1.getId_Admin();
						String emmet=admin1.getNom_Admin();
						

						Communiquer com = null;

						if (com == null) {
							
							Communiquer c = new Communiquer();

							c.setDate_Communiquer(datecom);
							c.setContenuCommuniquer(ContenuCom);
							c.setDestination(Destinationcom);
							c.setObjetCommunique(ObjetCom);
							c.setEtatCommunique(EtatCom);
							c.setemmeteur(emmeteur);
							c.setSuprimeur(emmet);
							c.setLastdate(datecom);

							home.enregistrerCommuniquer(c);

							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat1", new String(
									"<font color=\"red\">Communiqué enregistré avec succès</font>"));
							pageRetour = "/WEB-INF/principal/communiquer/creeCommunique.jsp";

						}
						else {
							/*request.setAttribute("resultat1", new String(
									"<font color=\"red\">Ce communiqué existe déja dans le sytème"));
							pageRetour = "/WEB-INF/principal/communiquer/creeCommunique.jsp";*/
						}
					} catch (Exception ex) {
						request.setAttribute("salle",session.getAttribute("salle"));
						request.setAttribute("heure",session.getAttribute("heure"));
						request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
						request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible d'enregistrer le Communiqué</font>"));
						pageRetour = "/WEB-INF/principal/communiquer/creeCommunique.jsp";
					}
				}
				if (form.equals("supprimer")) {
					// ici on suppumime un sommuniquer


					try {

						DateTime date1 = new DateTime().toDateTime();
						DateTimeFormatter date2 = DateTimeFormat.forPattern("dd/MM/yyyy");
						String datecom = date1.toString(date2);
						int id=Integer.parseInt(request.getParameter("id"));

						//int emmeteur=admin1.getId_Admin();


						Communiquer c = new Communiquer();
						c=home.rechercheCommuniquer(id);
						c.setEtatCommunique("plus en cour");
						c.setLastdate(datecom);
						c.setSuprimeur(admin1.getNom_Admin());
						//c.rechercheCommuniquer(c.getId_Communiquer());
						//home.enregistrerCommuniquer(c);
						home.modifieCommuniquer(c);

						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("message", new String(
								"<font color=\"red\">Communiqué supprime avec succès</font>"));
						pageRetour = "/WEB-INF/principal/communiquer/suprCommunique.jsp";



					} catch (Exception ex) {
						request.setAttribute("salle",session.getAttribute("salle"));
						request.setAttribute("heure",session.getAttribute("heure"));
						request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
						request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("message", new String(
								"<font color=\"red\">impossible d'enregistrer le Communiqué</font>"));
						pageRetour = "/WEB-INF/principal/communiquer/creeCommunique.jsp";
					}
				}

				if (form.equals("modifier")) {
					// ici on modifie les informations d'un communiquer


					try {

						DateTime date1 = new DateTime().toDateTime();
						DateTimeFormatter date2 = DateTimeFormat.forPattern("dd/MM/yyyy:hh:mm:ss");
						String datecom = date1.toString(date2);
						String ContenuCom=request.getParameter("contenuc").toUpperCase();
						String Destinationcom = request.getParameter("destinataire").toUpperCase();
						String ObjetCom = request.getParameter("objetc").toUpperCase();
						int idcom = (Integer)Integer.parseInt(request.getParameter("id"));
						System.out.println("merci justo");
						String EtatCom =request.getParameter("etatcom");
						System.out.println("merci justo");
						int emmeteur=admin1.getId_Admin();

						Communiquer com = null;

						if (com == null) {

							Communiquer c = new Communiquer();
							c.setId_Communiquer(idcom);
							c.setDate_Communiquer(datecom);
							c.setContenuCommuniquer(ContenuCom);
							c.setDestination(Destinationcom);
							c.setObjetCommunique(ObjetCom);
							c.setEtatCommunique(EtatCom);
							c.setemmeteur(emmeteur);
							c.setLastdate(datecom);
							c.setSuprimeur(admin1.getNom_Admin());

							boolean bo = home.modifieCommuniquer(c);
							if(bo=true){
								session.setAttribute("admin",admin1);
								request.setAttribute("nom",admin1.getNom_Admin());
								request.setAttribute("resultat1", new String(
										"<font color=\"red\">successful update</font>"));
							}
							else{
								request.setAttribute("resultat1", new String(
										"<font color=\"red\">communiqué non exixtant dans la BD</font>"));
							}
							pageRetour = "/WEB-INF/principal/communiquer/modifieCommunique.jsp";


						}
						else {
							/*request.setAttribute("resultat1", new String(
									"<font color=\"red\">Ce communiqué existe déja dans le sytème"));
							pageRetour = "/WEB-INF/principal/communiquer/creeCommunique.jsp";*/
						}
					} catch (Exception ex) {
						request.setAttribute("salle",session.getAttribute("salle"));
						request.setAttribute("heure",session.getAttribute("heure"));
						request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
						request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible d'enregistrer le Communiqué</font>"));
						pageRetour = "/WEB-INF/principal/communiquer/creeCommunique.jsp";
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
				case 0://menu principal/cours
					System.out.println("merci justo timber");
					session.setAttribute("admin",admin);
					request.setAttribute("contenuc",session.getAttribute("contenuc"));
					request.setAttribute("destinataire",session.getAttribute("destinataire"));
					request.setAttribute("objetc", session.getAttribute("objetc"));
					request.setAttribute("nom", admin.getNom_Admin());
					//request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
					//request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/communiquer/creeCommunique.jsp";
					break;
				case 2://menu principal/cours
					session.setAttribute("admin",admin);
					List<Communiquer> listcom=home.recupereCommuniquer();
					int nb=listcom.size();
					ArrayList<String> envoi=new ArrayList<String>();
					if(nb==0){
						String ui="il n'y a pas de communiquer dans le système ";
						envoi.add(ui);
						request.setAttribute("message",ui);
					}
					else{
						/*nb--;
						int debu=listcom.get(0).getId_Communiquer();
						int fin=listcom.get(nb).getId_Communiquer();
						List<String> idcom=new ArrayList<String>();



					request.setAttribute("nb",nb);
					request.setAttribute("debu",debu);
					request.setAttribute("fin",fin);*/
						int [] ident=new int[nb];
						for (int i=0;i<nb;i++){
							ident[i]=listcom.get(i).getId_Communiquer();
							System.out.println(ident[i]);
						}
						request.setAttribute("id",ident);
						session.setAttribute("id",ident);
						System.out.println("tableau");

						request.setAttribute("nom", admin.getNom_Admin());
						String ui="valider pour supprimer le communiquer ";
						request.setAttribute("message",ui);

						pageRetour = "/WEB-INF/principal/communiquer/suprCommunique.jsp";
					}
					break;


				case 1://mise à jour d'un cours
					List<Communiquer> com=home.recupereCommuniquer();
					int pi = com.size();
					int debut=(com.get(0).getId_Communiquer());
					int fin=(com.get(pi-1).getId_Communiquer());
					System.out.println(fin);
					int [] comm = new int[pi];
					for (int i = 0; i < pi; i++) {
						comm[i]=(com.get(i).getId_Communiquer());
					}
					request.setAttribute("debut",comm);
					session.setAttribute("debut",comm);
					request.setAttribute("pi",pi);
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					/*request.setAttribute("salle",session.getAttribute("salle"));
					request.setAttribute("heure",session.getAttribute("heure"));
					request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
					request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
					request.setAttribute("nom", admin.getNom_Admin());*/
					pageRetour = "/WEB-INF/principal/communiquer/modifieCommunique.jsp";
					break;
				case 4://création d'une nouvelle horaire
					session.setAttribute("admin",admin);
					request.setAttribute("salle",session.getAttribute("salle"));
					request.setAttribute("heure",session.getAttribute("heure"));
					request.setAttribute("listeenseignant", session.getAttribute("listeenseignant"));
					request.setAttribute("idenseignant", session.getAttribute("idenseignant"));
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/communiquer/creeCommunique.jsp";
					break;
				case 3://création d'une nouvelle salle de classe
					List<Communiquer> com1=home.recupereCommuniquer();
					int pi1 = com1.size();
					List<String> co = new ArrayList<String>();
					if(pi1==0){
						String ui="il n'y a pas de communique dans le système ";
						co.add(ui);
					}
					else{

						for (int i = 0; i < pi1; i++) {
							Communiquer tout=com1.get(i);
							int k=i+1;
							String pers=home.rechercheAdministrateur(tout.getemmeteur()).getNom_Admin();
							
							if(pers==null)	
								pers=(home.rechercheEnseignant(tout.getemmeteur())).getNom_Enseignant();
								
							String g="<center><font  color=\"red\" size=6>"+k+"</font></center><br>"+
									"<table width=\"60%\" class=\"tx-sharecms-pi1\">" +
									"<tr><td align=\"left\"><font color=\"black\" size=4>Identifiant du Communiqué:</font></td>"+
									"<td align=\"left\"><font color=\"red\">"+tout.getId_Communiquer()+"</font></td></tr>"+
									"<tr><td align=\"left\"><font color=\"black\" size=4>Publié le: </td>"+
									"<td align=\"left\">"+tout.getDate_Communiquer()+"</td></tr>"+
									"<tr><td align=\"left\"><font color=\"black\" size=4>par: </font> </td>"+
									"<td align=\"left\">Me/Mlle/Mr."+pers+"</td></tr>"+

"<tr><td align=\"left\"><font color=\"black\" size=4>Mise à jour le: </td>"+
"<td align=\"left\">"+tout.getLastdate()+"</td></tr>"+
"<tr><td align=\"left\"><font color=\"black\" size=4>par: </font> </td>"+
"<td align=\"left\">Me/Mlle/Mr. "+tout.getSuprimeur()+"</td></tr>"+

									"<tr><td align=\"left\"><font color=\"black\" size=4>conserné :</font> </td>"+
									"<td align=\"left\">"+tout.getDestination()+"</td></tr>"+
									"<tr><td align=\"left\"><font color=\"black\" size=4>objet:</font> </td>"+
									"<td align=\"left\">"+tout.getObjetCommunique()+"</td></tr>" +
									"<tr bgcolor=\"white\"><td align=\"\"><font color=\"green\" size=4>Message :</font> </td>"+
									"<td align=\"left\"><font color=\"green\" size=4>"+tout.getContenuCommuniquer()+"</font></td></tr>" +
									"<tr><td align=\"left\"><font color=\"black\" size=4>Communiqué :</font> </td>"+
									"<td align=\"left\">"+tout.getEtatCommunique()+"</td></tr>" +

									"</table>";
							co.add(g);
						}
					}

					request.setAttribute("liste",co);
					session.setAttribute("liste",co);
					request.setAttribute("nb",pi1);
					session.setAttribute("nb",pi1);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/communiquer/list1COmmuniquejsp.jsp";
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
					List<Horaire>horaire = (List<Horaire>) home
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

	private Object rechercheAdministrateur(int getemmeteur) {
		// TODO Auto-generated method stub
		return null;
	}


}
