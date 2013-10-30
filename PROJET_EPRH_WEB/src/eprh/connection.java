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

@WebServlet("/conect")
public class connection extends HttpServlet {
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
			String form = request.getParameter("connection");
			if (form.equals("Connecter")) {
				String identifiant = request.getParameter("identifiant").toUpperCase();
				String pass = request.getParameter("password").toUpperCase();
				String qualite=request.getParameter("qualite");
				
				if(qualite.equals("administrateur")){
				// ici l'administrateur se connecte
				try {
					Administrateur admin = home
							.rechercheAdministrateur(identifiant);
					if (!pass.equals(admin.getPassword())) {
						request.setAttribute(
								"erreur",
								new String(
										"erreur sur le mot de passe"));
						pageRetour = "/WEB-INF/principal/index.jsp";
					} else {
						session.setAttribute("admin",admin);
						request.setAttribute("idadmin",
								admin.getIdentifiant_admin());
						request.setAttribute("nom", admin.getNom_Admin());
						pageRetour = "/WEB-INF/principal/enseignant/Menu.jsp";
					}
				} catch (Exception ex) {
					request.setAttribute("erreur", new String(
							"administrateur non reconnu"));
					pageRetour = "/WEB-INF/principal/index.jsp";
				}
			}
				else{
					
					if(qualite.equals("enseignant")){
						// ici l'enseignant se connecte
						try {
							Enseignant ens= home.rechercheEnseignant(identifiant);
							if (!pass.equals(ens.getPassword())) {
								request.setAttribute(
										"erreur",
										new String(
												"erreur sur le mot de passe"));
								pageRetour = "/WEB-INF/principal/index.jsp";
							} else {
								session.setAttribute("ens",ens);
								request.setAttribute("idens",
										ens.getIdentifiant_enseignant());
								request.setAttribute("nom", ens.getNom_Enseignant());
								
								if(ens.getCon()!=0){
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
//											"<tr><td align=\"left\"><font color=\"black\" size=4>Cours enseignés:</font> </td>"+
//											"<td align=\"left\">"+s+"</td></tr>"+
											"</table>";
										request.setAttribute("retour",g);
									
									pageRetour = "/WEB-INF/principal/enseignant2/Menu2.jsp";
								}
								else
									pageRetour = "/WEB-INF/principal/index2.jsp";
							}
						} catch (Exception ex) {
							request.setAttribute("erreur", new String(
									"enseignant non reconnu"));
							pageRetour = "/WEB-INF/principal/index.jsp";
						}
					}
				}
				
				
			}
			else{
				if(form.equals("modifier")){
					String idens=request.getParameter("idens");
					String pass=request.getParameter("pass1");
					try{
						Enseignant ens=new Enseignant();
						ens=home.rechercheEnseignant(idens);
						ens.setPassword(pass);
						boolean res=home.modifieEnseignant(ens);
						session.setAttribute("ens",ens);
						request.setAttribute("idens",
								ens.getIdentifiant_enseignant());
						request.setAttribute("nom", ens.getNom_Enseignant());
						if(res){
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
//									"<tr><td align=\"left\"><font color=\"black\" size=4>Cours enseignés:</font> </td>"+
//									"<td align=\"left\">"+s+"</td></tr>"+
									"</table>";
								request.setAttribute("retour",g);
							pageRetour = "/WEB-INF/principal/enseignant2/Menu2.jsp";
						}
					}
					catch(Exception e){
						System.out.println("erreur"+e);
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

		//gestion de la session
		HttpSession session = request.getSession();
		try{
			ppt = new Properties();
			ppt.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ppt.put(Context.PROVIDER_URL, "localhost:1099");
			ctx = new InitialContext(ppt);
			home = (InterfaceProjet) ctx.lookup("ServeurProjet/remote-Client");
			Administrateur admin=(Administrateur)session.getAttribute("admin");
			if(admin==null){
				request.setAttribute("erreur","vos coordonnées ne sont pas valide");
				pageRetour="/WEB-INF/principal/index.jsp";
			}
			else{
				request.setAttribute("nom", admin.getNom_Admin());
				pageRetour="/WEB-INF/principal/enseignant/Menu.jsp";
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
