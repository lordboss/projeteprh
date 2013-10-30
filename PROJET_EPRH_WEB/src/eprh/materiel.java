package eprh;

import java.io.IOException;
import java.text.SimpleDateFormat;
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


@WebServlet("/materiel")

public class materiel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";
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

				if (form.equals("Decharger")) {
					// ici on decharge un materiel

					try {
						
						
						String codem=request.getParameter("codem").toUpperCase();
						int quantite =  Integer.parseInt((request.getParameter("quantite")));
						Materiel mat = home.rechercheMateriel(codem);
						if (mat == null) {
							
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat1", new String(
									"<font color=\"red\"> désolé mais  le materiel de code "+codem+" n'existe pas dans la base</font>"));
							pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";
							
							
						} else {
							if(mat.getQuantite()<quantite){
								session.setAttribute("admin",admin1);
								request.setAttribute("nom",admin1.getNom_Admin());
								request.setAttribute("resultat1", new String(
										"<font color=\"red\"> désolé mais  la quantité de matériel demandé est supérieure &agrave; celle demandée </font>"));
								pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";

							}
							else{
							int a=mat.getQuantite()-quantite;
							mat.setQuantite(a);
							home.enregistrerMateriel2(mat);
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat1", new String(
									"<font color=\"red\">La decharge du materiel a reussi"));
							pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";
							}
						}
					} catch (Exception ex) {
						
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible de decharger le materiel</font>"));
						pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";
					}
				}
				if (form.equals("Lister")) {
					// ici on liste le materiel en stock
					try {
						List<Materiel> listens=home.recupereMateriel();
						int n=listens.size();
						int i=0;
						List<String> envoi=new ArrayList<String>();
						if(n==0){
							String ui="il n'y a pas de materiel dans le système ";
							envoi.add(ui);
						}
						else{
							while(i<n){
								Materiel mat=listens.get(i);
								int k=i+1;
								

								String g="<center><font  color=\"red\" size=6>"+k+"</font></center><br>"+
										"<table width=\"60%\" class=\"tx-sharecms-pi1\">" +
										"<tr><td align=\"left\"><font color=\"black\" size=4>Id du materiel:</font></td>"+
										"<td align=\"left\">"+mat.getId_Materiel()+"</td></tr>"+
										"<tr><td align=\"left\"><font color=\"black\" size=4>nom du materiel: </td>"+
										"<td align=\"left\">"+mat.getNom()+"</td></tr>"+
										"<tr><td align=\"left\"><font color=\"black\" size=4>code du materiel:</font> </td>"+
										"<td align=\"left\">"+mat.getCode()+"</td></tr>"+
										"<tr><td align=\"left\"><font color=\"black\" size=4>quantite du matriel</font> </td>"+
										"<td align=\"left\">"+mat.getQuantite()+"</td></tr>"+
										"</table>";
								envoi.add(g);
								i++;
							}
						}
						request.setAttribute("liste",envoi);
						request.setAttribute("nb",n);
						request.setAttribute("nom", admin1.getNom_Admin());
						pageRetour = "/WEB-INF/principal/materiel/ListerMateriel2.jsp";session.setAttribute("admin",admin1);
							
					} catch (Exception ex) {
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible de lister le materiel</font>"));
					pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";}
				}
				
				if (form.equals("Listerc")) {
					// ici on liste les commandes
					try {
						List<Commande> listens=home.recupereCommande();
						int n=listens.size();
						int i=0;
						List<String> envoi=new ArrayList<String>();
						if(n==0){
							String ui="il n'y a pas de commande dans le système ";
							envoi.add(ui);
						}
						else{
							while(i<n){
                                 Commande mat=listens.get(i);
								int k=i+1;
								

								String g="<center><font  color=\"red\" size=6>"+k+"</font></center><br>"+
										"<table width=\"60%\" class=\"tx-sharecms-pi1\">" +
										"<tr><td align=\"left\"><font color=\"black\" size=4>Id de la commande:</font></td>"+
										"<td align=\"left\">"+mat.getId_Commande()+"</td></tr>"+
										"<tr><td align=\"left\"><font color=\"black\" size=4>materiel command&eacute;: </td>"+
										"<td align=\"left\">"+mat.getObjet_Commande()+"</td></tr>"+
										"<tr><td align=\"left\"><font color=\"black\" size=4>code du materiel:</font> </td>"+
										"<td align=\"left\">"+mat.getCode_Commande()+"</td></tr>"+
										"<tr><td align=\"left\"><font color=\"black\" size=4>quantite du matriel</font> </td>"+
										"<td align=\"left\">"+mat.getQte_Commande()+"</td></tr>"+
										"<tr><td align=\"left\"><font color=\"black\" size=4>date de la commande</font> </td>"+
										"<td align=\"left\">"+mat.getDate_Commande()+"</td></tr>"+
										"</table>";
								envoi.add(g);
								i++;
							}
						}
						request.setAttribute("liste",envoi);
						request.setAttribute("nb",n);
						request.setAttribute("nom", admin1.getNom_Admin());
						pageRetour = "/WEB-INF/principal/materiel/ListerCommande2.jsp";session.setAttribute("admin",admin1);
							
					} catch (Exception ex) {
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible de lister les commandes</font>"));
					pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";}
				}
				if (form.equals("Commander")) {
					// ici on ajoute un materiel

					try {
						
						String nom=request.getParameter("nom").toUpperCase();
						String codem=request.getParameter("codem").toUpperCase();
						int quantite = new Integer(request.getParameter("quantite"));
						Materiel mat = home.rechercheMateriel(codem);
						if (mat == null) {
							
							Materiel m = new Materiel();
							Commande co=new Commande();
							m.setCode(codem);
							if (codem.equals(null)) {
								System.out.println("le code de ce materiel est null");
							} else {
								m.setCode(codem);
								co.setCode_Commande(codem);
							}
							
							m.setNom(nom);
							co.setObjet_Commande(nom);
							m.setAdmin(admin1);
							co.setAdmin(admin1);
							m.setQuantite(quantite);
							co.setQte_Commande(quantite);
							Date aujourdhui = new Date();
			                SimpleDateFormat formater = new SimpleDateFormat("EEEE ,dd MMMMM yyyy GGG, hh:mm aaa");
			                co.setDate_Commande(formater.format(aujourdhui));
			                home.enregistreCommande(co);
					        home.enregistrerMateriel(m);
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat1", new String("commande passée avec succès"));
							pageRetour = "/WEB-INF/principal/materiel/PasserCommande.jsp";

						} else {
							int a=quantite;
							mat.setQuantite(a);
							home.enregistrerMateriel(mat);
							
							Commande co=new Commande();
							co.setObjet_Commande(nom);
							co.setCode_Commande(codem);
							co.setAdmin(admin1);
							co.setQte_Commande(quantite);
							Date aujourdhui = new Date();
			                SimpleDateFormat formater = new SimpleDateFormat("EEEE ,dd MMMMM yyyy GGG, hh:mm aaa");
			                co.setDate_Commande(formater.format(aujourdhui));
			                home.enregistreCommande(co);
					        
							session.setAttribute("admin",admin1);
							request.setAttribute("nom",admin1.getNom_Admin());
							request.setAttribute("resultat1", new String(
									"<font color=\"red\">Ce materiel existe déja dans le sytème consequemment l'ancienne quantité a été ajoutée sur la nouvelle"));
							pageRetour = "/WEB-INF/principal/materiel/PasserCommande.jsp";
						}
					} catch (Exception ex) {
						
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible de passer votre commande</font>"));
						pageRetour = "/WEB-INF/principal/materiel/PasserCommande.jsp";
					}
				}
				
					this.getServletContext().getRequestDispatcher(pageRetour)
			.forward(request, response);
			}}
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
				case 1://menu principal/materiel
					session.setAttribute("admin",admin);
					
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";
					break;
				case 2://liste du materiel en stock
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/materiel/ListerMateriel.jsp";
					break;
				
				case 3://passer une commande
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/materiel/PasserCommande.jsp";
					break;
					
				case 4://lister les commandes
					session.setAttribute("admin",admin);
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/materiel/ListerCommande.jsp";
					break;	
				default:
					session.setAttribute("admin",admin);
					
					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/materiel/AjoutMateriel.jsp";
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
