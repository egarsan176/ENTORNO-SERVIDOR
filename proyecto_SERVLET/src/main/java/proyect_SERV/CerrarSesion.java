package proyect_SERV;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CerrarSesion
 */
@WebServlet("/CerrarSesion")
public class CerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerrarSesion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Recupero la sesión con false para que si la sesión anterior ha caducado, no cree una sesión nueva
		 */
		HttpSession sesion = request.getSession(false);
		
		if(sesion!=null) {
			/**
			 * Invalido la sesión
			 */
			sesion.invalidate();
			response.sendRedirect("/proyecto_SERVLET/model/index_login.jsp");
			
		}else {
			
			response.sendRedirect("/proyecto_SERVLET/model/index_login.jsp");
		}

		
	}

}
