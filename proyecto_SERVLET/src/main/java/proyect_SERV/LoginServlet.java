package proyect_SERV;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response); 	//llama al doPost
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession(true); //te devuelve la sesion que haya en el momento como un "objeto"
														//si dentro tiene true, te devuelve la sesion, y si no existe, crea una nueva
		

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        
        if(validateUser(usuario, password) && sesion.getAttribute("userName") == null){ 
            //si coincide usuario y password y además no hay sesión iniciada entra
            
        	sesion.setMaxInactiveInterval(10);	//invalidar la sesion transcurridos 10 segundos
        	
           	sesion.setAttribute("userName", usuario);	//establece el nombre del usuario en la sesion
            //redirijo al catálogo
            response.sendRedirect("/proyecto_SERVLET/model/catalogo.html");
            
        }else{
        	sesion.invalidate();
            //si el login falla redirige a página de error
        	response.sendRedirect("/proyecto_SERVLET/model/error.jsp");
        	
        }
	}
	
	
	
	
	/**
	 * Este método instancia la lista de usuarios, le carga los usuarios y comprueba que el usuario introducido existe 
	 * @param usuario
	 * @param password
	 * @return true si el usuario y su contraseña existen en el HASHMAP, false si no existen
	 */
	private boolean validateUser(String usuario, String password) {
		
		Listado_Usuarios listado = new Listado_Usuarios();
		listado.addDatos();

		return listado.isContain(usuario, password);
	}
	

	

}
