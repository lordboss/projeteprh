package eprh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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




@WebServlet("/pv")
public class pv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String pageRetour = "/WEB-INF/principal/cours/creerCours.jsp";
	DateTime date1 = new DateTime().toDateTime();
	DateTimeFormatter date2 = DateTimeFormat.forPattern("dd/MM/yyyy");
	String date = date1.toString(date2);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chemin=new String("/home/foko/eprh/");
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
					// ici on enregistre un pv

					try {
						String intitule=request.getParameter("intitule");
						String jour=request.getParameter("jour");
						String mois= request.getParameter("mois");
						String annee=request.getParameter("annee");
						String heure=request.getParameter("heure");
						String semestre=request.getParameter("semestre");


						String fichier=request.getParameter("fichier");
						String fiche=request.getParameter("fichie");



						Proces_Verbaux pv=new Proces_Verbaux();
						pv.setAdministrateur(admin1);
						pv.setIntitulePv(intitule);
						System.out.println("\n\n\n "+pv.getIntitulePv()+"\n\n\n");
						pv.setJourPv(jour);
						pv.setMoisPv(mois);
						pv.setAnneePv(annee);
						pv.setHeure(heure);
						pv.setSemestre(semestre);



						File f=new File(fiche);
						pv.setNomFichier(fichier);
						System.out.println("\n\n\n path:"+f.getAbsolutePath()+"\n\n\n");				



						copierFichier(f.getAbsolutePath(), chemin+fichier);
						//  						copierFichier(f.getAbsolutePath(), "/pv/"+fichier);
						home.enregistrerPV(pv);
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">pv enregistré avec succès</font>"));
						pageRetour = "/WEB-INF/principal/pv/EnregistrerPv.jsp";

					} catch (Exception ex) {
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						request.setAttribute("resultat1", new String(
								"<font color=\"red\">impossible d'enregistrer le pv</font>"));
						pageRetour = "/WEB-INF/principal/pv/EnregistrerPv.jsp";
					}
				}

				if (form.equals("Consulter")) {
					// ici on visualise les pv par année

					try {
						Proces_Verbaux pv;
						String annee=request.getParameter("annee");
                        List<Proces_Verbaux> listepv=new ArrayList<Proces_Verbaux>();
                        listepv=home.RecupereListePv();
                        int n=listepv.size();
                        List<String> semestre1=new ArrayList<String>();
                        List<String> semestre2=new ArrayList<String>();
                        List<String> semestre3=new ArrayList<String>();
						String ligne=new String();
						int i=0;
						while(i<n){
							pv=listepv.get(i);
							ligne=pv.getIntitulePv()+";<a href=\"Dowload?fichierRequis="+pv.getNomFichier()+"\">"+pv.getNomFichier()+"</a>;"+pv.getJourPv()+"-"+pv.getMoisPv()+"-"+pv.getAnneePv()+";"+pv.getHeure()+" heure";
							if(pv.getAnneePv().equals(annee)){
								if(pv.getSemestre().equals("1")){
									semestre1.add(ligne);
								}
								else{
									if(pv.getSemestre().equals("2")){
										semestre2.add(ligne);
									}
									else{
										semestre3.add(ligne);
									}
								}
							}
							
							i++;
						}  
						request.setAttribute("semestre1",semestre1);
						request.setAttribute("semestre2",semestre2);
						request.setAttribute("semestre3",semestre3);

						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						pageRetour = "/WEB-INF/principal/pv/ConsulterPv2.jsp";
						
					} catch (Exception ex) {
						session.setAttribute("admin",admin1);
						request.setAttribute("nom",admin1.getNom_Admin());
						
						pageRetour = "/WEB-INF/principal/pv/ConsulterPv.jsp";
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
				case 2:
					/*ici on gère la récupération des pv par intititulé*/
					List<Proces_Verbaux> list=home.RecupereListePv();
					int nb=list.size();
					if(nb!=0){
						int u=0;
						List<String> listePv=new ArrayList<String>();
						while(u<nb){
							listePv.add(list.get(u).getNomFichier().toString());
							u++;
						}
						request.setAttribute("listePv",listePv);
						session.setAttribute("listePv",listePv);
						pageRetour = "/WEB-INF/principal/pv/ConsulterPv.jsp";
					}
					else{
						request.setAttribute("resultat1","<font color=\"red\">il n'existe pas encore de pv dans la bd</font>");
						pageRetour = "/WEB-INF/principal/pv/EnregistrerPv.jsp";
					}
					request.setAttribute("nom", admin.getNom_Admin());
					break;
				case 3:
					/*ici on gère la recupération de pv par année académique*/
					list=home.RecupereListePv();	
					nb=list.size();
					if(nb!=0){
						int u=0;
						List<String> listePv=new ArrayList<String>();
						while(u<nb){
							listePv.add(list.get(u).getAnneePv());
							u++;
						}
						request.setAttribute("listePv",listePv);
						session.setAttribute("listePv",listePv);
						pageRetour = "/WEB-INF/principal/pv/ConsulterPv1.jsp";
					}
					else{
						request.setAttribute("resultat1","<font color=\"red\">il n'existe pas encore de pv dans la bd</font>");
						pageRetour = "/WEB-INF/principal/pv/EnregistrerPv.jsp";
					}
					request.setAttribute("nom", admin.getNom_Admin());
					break;

				default:
					session.setAttribute("admin",admin);

					request.setAttribute("nom", admin.getNom_Admin());
					pageRetour = "/WEB-INF/principal/pv/EnregistrerPv.jsp";
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
	public static void copierFichier(String source, String destination) throws IOException {
		//		méthode qui permet d'uploader un fichier
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			byte buffer[] = new byte[1024];
			int taille = 0;
			fis = new FileInputStream(source);
			fos = new FileOutputStream(destination);
			while ((taille = fis.read(buffer)) != -1) {
				System.out.println(taille);
				fos.write(buffer, 0, taille);
			}
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}



}
