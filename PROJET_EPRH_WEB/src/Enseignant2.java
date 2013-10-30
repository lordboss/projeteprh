

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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import manager.InterfaceProjet;
import manager.ServeurProjet;
import beans.Administrateur;
import beans.Cours;
import beans.Enseignant;

/**
 * Servlet implementation class Enseignant2
 */
@WebServlet("/Enseignant2")
public class Enseignant2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String pageRetour = "/WEB-INF/principal/index.jsp";
	DateTime date1 = new DateTime().toDateTime();
	DateTimeFormatter date2 = DateTimeFormat.forPattern("dd/MM/yyyy");
	String date = date1.toString(date2);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enseignant2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Properties ppt = null;
		Context ctx = null;
		ServeurProjet ref = null;
		InterfaceProjet home = null;

		request.setAttribute("date", date);
		String structure = "Département d'Economie Publique et Ressources Humaines (EPRH)";
		request.setAttribute("struc", structure);

		//gestion de la session
		HttpSession session = request.getSession();
		Enseignant ens=(Enseignant) session.getAttribute("ens");
		try {
			if(ens==null){
				pageRetour="/WEB-INF/principal/index.jsp";
			}
			else{
				session.setAttribute("ens",ens);
				ppt = new Properties();
				ppt.put(Context.INITIAL_CONTEXT_FACTORY,
						"org.jnp.interfaces.NamingContextFactory");
				ppt.put(Context.PROVIDER_URL, "localhost:1099");
				ctx = new InitialContext(ppt);
				home = (InterfaceProjet) ctx.lookup("ServeurProjet/remote-Client");
				int action = new Integer(request.getParameter("action"));
				switch (action) {	
				case 1://menu principal/enseignant
					request.setAttribute("nom",ens.getNom_Enseignant());
					String enseignant=ens.getIdentifiant_enseignant().toUpperCase();
					Enseignant ens2=home.rechercheEnseignant(enseignant);
					if(ens2!=null){
						List<Cours> cours=(List<Cours>)ens.getCours();
						int n=cours.size();
						List<String> result=new ArrayList<String>();
						String ligne=new String();
						int i=0;
						while(i<n){
							ligne=cours.get(i).getIntitule_Cours()+";"+cours.get(i).getSalle().getCodeSalle()+";"+cours.get(i).getCode_Cours()+";"+cours.get(i).getNiveau()+";"+cours.get(i).getHoraire().getHeure();
							result.add(ligne);
							i++;
						}  
						request.setAttribute("liste",result);
						request.setAttribute("nb",n);
						request.setAttribute("enseignant",ens.getNom_Enseignant());
						session.setAttribute("ens", ens);
						pageRetour = "/WEB-INF/principal/enseignant2/Emploi2.jsp";

					} else {
						session.setAttribute("ens",ens);
						request.setAttribute("nom",ens.getNom_Enseignant());
						request.setAttribute("erreur", new String(
								"Enseignant inexistant dans la base de données"));
						pageRetour = "/WEB-INF/principal/enseignant2/Menu2.jsp";
					}
					break;
					
				case 2:
					session.setAttribute("ens",ens);
					request.setAttribute("nom", ens.getNom_Enseignant());
					pageRetour = "/WEB-INF/principal/enseignant2/Grille1.jsp";
					break;
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Properties ppt = null;
		Context ctx = null;
		ServeurProjet ref = null;
		InterfaceProjet home = null;
		pageRetour="/WEB-INF/principal/index.jsp";

		request.setAttribute("date", date);
		HttpSession session = request.getSession();
		try{
			ppt = new Properties();
			ppt.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ppt.put(Context.PROVIDER_URL, "localhost:1099");
			ctx = new InitialContext(ppt);
			home = (InterfaceProjet) ctx.lookup("ServeurProjet/remote-Client");
			Enseignant ens=(Enseignant)session.getAttribute("ens");
			if(ens==null){
				request.setAttribute("erreur","vos paramètres d'authentification ne sont pas valide");
				pageRetour="/WEB-INF/principal/index.jsp";
			}
			else{
				String form = request.getParameter("connection");

				if (form.equals("Consulter")) {
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
							session.setAttribute("ens",ens);
							request.setAttribute("nom",ens.getNom_Enseignant());

							pageRetour = "/WEB-INF/principal/enseignant2/Grille2.jsp";
						} else {
							session.setAttribute("ens",ens);
							request.setAttribute("nom",ens.getNom_Enseignant());
							request.setAttribute("resultat5", new String(
									"<font color=\"red\">Il n'existe aucun cours pour ce niveau</font>"));
							pageRetour = "/WEB-INF/principal/enseignant2/Grille1.jsp";
						}
					} catch (Exception ex) {
						session.setAttribute("ens",ens);
						request.setAttribute("resultat2", new String(
								"<font color=\"red\">Impossible d'afficher la grille des UV pour ce niveau</font>"));
						pageRetour = "/WEB-INF/principal/enseignant2/Grille2.jsp";
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
	}



