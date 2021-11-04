package proyect_SERV;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Catalogo_servlet
 */
@WebServlet("/Catalogo_servlet")
public class Catalogo_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalogo_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession(false); //con false para que si se ha caducado la sesion anterior, no cree una nueva
		
		if(sesion.getAttribute("userName") != null) {
			doPost(request, response);
		}else {
			response.sendRedirect("/proyecto_SERVLET/model/index_login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out =response.getWriter();
		
		out.print("<DOCTYPE html><html>"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=<device-width>, initial-scale=1.0\">\n"
				+ "    <title>Document</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <p>Hola</p>\n"
				+ "    <h1>Adioooos</h1>\n"
				+ "</body></html>");
		
	}

}
