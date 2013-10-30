package eprh;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index1")
public class Index1 extends HttpServlet {
	public String structure;
	private static final long serialVersionUID = 1L;
	//Gestion de la date
 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	 DateTime date1= new DateTime().toDateTime();
	 DateTimeFormatter date2=DateTimeFormat.forPattern("dd/MM/yyyy");
	 String date=date1.toString(date2);
	 //fin gestion date
	 
	 request.setAttribute("date",date);
	
   this.getServletContext().getRequestDispatcher("/WEB-INF/principal/index.jsp" ).forward( request, response );
  }
}
