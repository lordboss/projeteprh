package eprh;

import java.io.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.InterfaceProjet;
import manager.ServeurProjet;
import beans.Administrateur;

@WebServlet("/Dowload")
public class Dowload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TAILLE_TAMPON=10234;	// 10ko
	public static String chemin=new String("/home/foko/eprh/");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties ppt = null;
		Context ctx = null;
		ServeurProjet ref = null;
		InterfaceProjet home = null;
		String pageRetour = "/WEB-INF/principal/index.jsp";
		String fichierRequis=request.getParameter("fichierRequis");
		File fichier=new File(chemin,fichierRequis);
		if(!fichier.exists()){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;	        	
		}
		String type = getServletContext().getMimeType( fichier.getName() );
		/* Si le type de fichier est inconnu, alors on initialise un type
	        par défaut */
		if ( type == null ) {
			type = "application/octet-stream";
		}
		final int DEFAULT_BUFFER_SIZE = 10240; // 10 ko

		/* Initialise la réponse HTTP */
		response.reset();
		response.setBufferSize( DEFAULT_BUFFER_SIZE );
		response.setContentType( type );
		response.setHeader( "Content-Length", String.valueOf(
				fichier.length() ) );
		response.setHeader( "Content-Disposition", "attachment; filename=\""
				+ fichier.getName() + "\"" );
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		//gestion de la session
		HttpSession session = request.getSession();
		try{
			
			Administrateur admin1=(Administrateur)session.getAttribute("admin");
			if(admin1==null){
				request.setAttribute("erreur","vos paramètres d'authentification ne sont pas valide");
				pageRetour="/WEB-INF/principal/index.jsp";
				
			}
			else{
				/* Ouvre les flux */
				entree = new BufferedInputStream( new FileInputStream( fichier), TAILLE_TAMPON );
				sortie = new BufferedOutputStream( response.getOutputStream(),TAILLE_TAMPON );
				/* Lit le fichier et écrit son contenu dans la réponse HTTP */
				byte[] tampon = new byte[TAILLE_TAMPON];
				int longueur;
				while ( ( longueur= entree.read( tampon ) ) > 0 ) {
					sortie.write( tampon, 0, longueur );
				}
				session.setAttribute("admin",admin1);
				request.setAttribute("nom",admin1.getNom_Admin());
				pageRetour="/WEB-INF/principal/pv/ConsulterPv.jsp";
			}
			this.getServletContext().getRequestDispatcher(pageRetour)
			.forward(request, response);

		} finally {
			try {
				sortie.close();
			} catch ( IOException ignore ) {
			}
			try {
				entree.close();
			} catch ( IOException ignore ) {
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties ppt = null;
		Context ctx = null;
		ServeurProjet ref = null;
		InterfaceProjet home = null;
		String pageRetour = "/WEB-INF/principal/index.jsp";
		String fichierRequis=request.getParameter("fichierRequis");
		File fichier=new File(chemin,fichierRequis);
		if(!fichier.exists()){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;	        	
		}
		String type = getServletContext().getMimeType( fichier.getName() );
		/* Si le type de fichier est inconnu, alors on initialise un type
	        par défaut */
		if ( type == null ) {
			type = "application/octet-stream";
		}
		final int DEFAULT_BUFFER_SIZE = 10240; // 10 ko

		/* Initialise la réponse HTTP */
		response.reset();
		response.setBufferSize( DEFAULT_BUFFER_SIZE );
		response.setContentType( type );
		response.setHeader( "Content-Length", String.valueOf(
				fichier.length() ) );
		response.setHeader( "Content-Disposition", "attachment; filename=\""
				+ fichier.getName() + "\"" );
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		//gestion de la session
		HttpSession session = request.getSession();
		try{
			
			Administrateur admin1=(Administrateur)session.getAttribute("admin");
			if(admin1==null){
				request.setAttribute("erreur","vos paramètres d'authentification ne sont pas valide");
				pageRetour="/WEB-INF/principal/index.jsp";
				
			}
			else{
				/* Ouvre les flux */
				entree = new BufferedInputStream( new FileInputStream( fichier), TAILLE_TAMPON );
				sortie = new BufferedOutputStream( response.getOutputStream(),TAILLE_TAMPON );
				/* Lit le fichier et écrit son contenu dans la réponse HTTP */
				byte[] tampon = new byte[TAILLE_TAMPON];
				int longueur;
				while ( ( longueur= entree.read( tampon ) ) > 0 ) {
					sortie.write( tampon, 0, longueur );
				}
				session.setAttribute("admin",admin1);
				request.setAttribute("nom",admin1.getNom_Admin());
				pageRetour="/WEB-INF/principal/pv/ConsulterPv.jsp";
			}
			this.getServletContext().getRequestDispatcher(pageRetour)
			.forward(request, response);

		} finally {
			try {
				sortie.close();
			} catch ( IOException ignore ) {
			}
			try {
				entree.close();
			} catch ( IOException ignore ) {
			}
		}

	}

}
