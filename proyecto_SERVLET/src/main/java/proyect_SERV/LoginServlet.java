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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response); 	//si entra en el GET, llama al doPost
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Recogemos un objeto de la sesion. Al ser true, te devuelve la sesión y si no existiera, crea una nueva
		 */
		HttpSession sesion = request.getSession(true); 
														
		
		/**
		 * Se recogen los datos de usuario y contraseña introducidos en el login
		 */
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        /**
         * Se establece el nombre de usuario en la sesión y se invalida transcurridos 15 segundos
         */
        sesion.setAttribute("userName", usuario);	
        sesion.setMaxInactiveInterval(15);	
        
        
        /**
         * Se comprueba si el usuario y la contraseña introducidos son válidos
         */
        if(validateUser(usuario, password)){ 
        
        	/**
        	 * Si el usuario y la contraseña son correctos se muestra el catálogo de productos
        	 */
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<DOCTYPE html><html>"
            		+ "<head>\n"
            		+ "<meta charset=\"UTF-8\">\n"
            		+ "<title>Catálogo</title>\n"
            		+ "</head>\n"
            		+ "<link rel=\"stylesheet\" href=\"CSS/hojaDeEstilo2.css\">\n"
            		+ "\n"
            		+ "<body>\n"
            		+ "<div id=\"header\">\n"
            		+ "<h1>Catálogo Online</h1>\n"
            		+ "</div>\n"
            		+ "<form action=\"/proyecto_SERVLET/Catalogo_servlet\" method=\"POST\" >\n"
            		+ "\n"
            		+ "    <div id=\"1\">\n"
            		+ "      <table>\n"
            		+ "        <tr>\n"
            		+ "          <td>\n"
            		+ "            <img src=\"img/1.jpg\">\n"
            		+ "          </td>\n"
            		+ "          <td>\n"
            		+ "            <h2>Kit Básico de Herramientas</h2>\n"
            		+ "            <ul>\n"
            		+ "              <li>Compuesto por 36 piezas</li>\n"
            		+ "              <li>Fabricado en hierro y madera</li>\n"
            		+ "              <li>Precio:</li>\n"
            		+ "              <p >49.99 €</p>\n"
            		+ "            </ul>\n"
            		+ "          </td>\n"
            		+ "  \n"
            		+ "        </tr>\n"
            		+ "      </table>\n"
            		+ "      \n"
            		+ "        <label>Seleccione Cantidad: </label>\n"
            		+ "        <input type=\"number\" name=\"id_1\">\n"
            		+ "      \n"
            		+ "    </div>\n"
            		+ "  \n"
            		+ "  \n"
            		+ "    <div id=\"2\">\n"
            		+ "      <table>\n"
            		+ "        <tr>\n"
            		+ "          <td>\n"
            		+ "            <img src=\"img/2.jpg\">\n"
            		+ "          </td>\n"
            		+ "          <td>\n"
            		+ "            <h2>Taladro percutor</h2>\n"
            		+ "            <ul>\n"
            		+ "              <li>Con batería inalámbrica.</li>\n"
            		+ "              <li>900W y 60 horas de autonomía</li>\n"
            		+ "              <li>Precio:</li>\n"
            		+ "              <p>69.99 €</p>\n"
            		+ "            </ul>\n"
            		+ "          </td>\n"
            		+ "  \n"
            		+ "        </tr>\n"
            		+ "      </table>\n"
            		+ "     \n"
            		+ "        <label>Seleccione Cantidad: </label>\n"
            		+ "        <input type=\"number\" name=\"id_2\">\n"
            		+ "      \n"
            		+ "    </div>\n"
            		+ "  \n"
            		+ "    <div id=\"3\">\n"
            		+ "      <table>\n"
            		+ "        <tr>\n"
            		+ "          <td>\n"
            		+ "            <img src=\"img/3.jpg\">\n"
            		+ "          </td>\n"
            		+ "          <td>\n"
            		+ "            <h2>Llave</h2>\n"
            		+ "            <ul>\n"
            		+ "              <li>Dos en una</li>\n"
            		+ "              <li>Un lado llave fija, el otro, estriada articulable</li>\n"
            		+ "              <li>Precio:</li>\n"
            		+ "              <p>19.99 €</p>\n"
            		+ "            </ul>\n"
            		+ "          </td>\n"
            		+ "  \n"
            		+ "        </tr>\n"
            		+ "      </table>\n"
            		+ "      \n"
            		+ "        <label>Seleccione Cantidad: </label>\n"
            		+ "        <input type=\"number\" name=\"id_3\">\n"
            		+ "      \n"
            		+ "    </div>\n"
            		+ "  \n"
            		+ "    <div id=\"4\">\n"
            		+ "      <table>\n"
            		+ "        <tr>\n"
            		+ "          <td>\n"
            		+ "            <img src=\"img/4.jpg\">\n"
            		+ "          </td>\n"
            		+ "          <td>\n"
            		+ "            <h2>Juego de Destornilladores</h2>\n"
            		+ "            <ul>\n"
            		+ "              <li>Con ocho puntas intercambiables</li>\n"
            		+ "              <li>Puntas imantadas</li>\n"
            		+ "              <li>Precio:</li>\n"
            		+ "              <p>29.99 €</p>\n"
            		+ "            </ul>\n"
            		+ "          </td>\n"
            		+ "  \n"
            		+ "        </tr>\n"
            		+ "      </table>\n"
            		+ "     \n"
            		+ "        <label>Seleccione Cantidad: </label>\n"
            		+ "        <input type=\"number\" name=\"id_4\">\n"
            		+ "      \n"
            		+ "    </div>\n"
            		+ "  \n"
            		+ "    <div id=\"5\">\n"
            		+ "      <table>\n"
            		+ "        <tr>\n"
            		+ "          <td>\n"
            		+ "            <img src=\"img/5.jpg\">\n"
            		+ "          </td>\n"
            		+ "          <td>\n"
            		+ "            <h2>Juego de LLaves</h2>\n"
            		+ "            <ul>\n"
            		+ "              <li>Llave fija</li>\n"
            		+ "              <li>Llave estriada</li>\n"
            		+ "              <li>Precio:</li>\n"
            		+ "              <p>19.99 €</p>\n"
            		+ "            </ul>\n"
            		+ "          </td>\n"
            		+ "  \n"
            		+ "        </tr>\n"
            		+ "      </table>\n"
            		+ "      \n"
            		+ "        <label>Seleccione Cantidad: </label>\n"
            		+ "        <input type=\"number\" name=\"id_5\">\n"
            		+ "      \n"
            		+ "    </div>\n"
            		+ "  \n"
            		+ "    <div id=\"6\">\n"
            		+ "      <table>\n"
            		+ "        <tr>\n"
            		+ "          <td>\n"
            		+ "            <img src=\"img/6.jpg\">\n"
            		+ "          </td>\n"
            		+ "          <td>\n"
            		+ "            <h2>Juego de Destornilladores</h2>\n"
            		+ "            <ul>\n"
            		+ "              <li>Compuesto por 3 piezas</li>\n"
            		+ "              <li>Punta imantada de estrella</li>\n"
            		+ "              <li>Precio:</li>\n"
            		+ "              <p>10.99 €</p>\n"
            		+ "            </ul>\n"
            		+ "          </td>\n"
            		+ "  \n"
            		+ "        </tr>\n"
            		+ "      </table>\n"
            		+ "      \n"
            		+ "        <label>Seleccione Cantidad: </label>\n"
            		+ "        <input type=\"number\" name=\"id_6\">\n"
            		+ "     \n"
            		+ "    </div>\n"
            		+ "  \n"
            		+ "    <div id=\"7\">\n"
            		+ "      <table>\n"
            		+ "        <tr>\n"
            		+ "          <td>\n"
            		+ "            <img src=\"img/7.jpg\">\n"
            		+ "          </td>\n"
            		+ "          <td>\n"
            		+ "            <h2>Tornillo con tuerca</h2>\n"
            		+ "            <ul>\n"
            		+ "              <li>Cabeza de estrella y estriado</li>\n"
            		+ "              <li>Pack 5 unidades</li>\n"
            		+ "              <li>Precio (pack):</li>\n"
            		+ "              <p>1.99 €</p>\n"
            		+ "            </ul>\n"
            		+ "          </td>\n"
            		+ "  \n"
            		+ "        </tr>\n"
            		+ "      </table>\n"
            		+ "      \n"
            		+ "        <label>Seleccione Cantidad: </label>\n"
            		+ "        <input type=\"number\" name=\"id_7\">\n"
            		+ "      \n"
            		+ "    </div>\n"
            		+ "  \n"
            		+ "    <div id=\"8\">\n"
            		+ "      <table>\n"
            		+ "        <tr>\n"
            		+ "          <td>\n"
            		+ "            <img src=\"img/8.jpg\">\n"
            		+ "          </td>\n"
            		+ "          <td>\n"
            		+ "            <h2>Tornillo</h2>\n"
            		+ "            <ul>\n"
            		+ "              <li>Estriado y rosca chapa</li>\n"
            		+ "              <li>Pack 10 unidades</li>\n"
            		+ "              <li>Precio (pack):</li>\n"
            		+ "              <p>2.5 €</p>\n"
            		+ "            </ul>\n"
            		+ "          </td>\n"
            		+ "  \n"
            		+ "        </tr>\n"
            		+ "      </table>\n"
            		+ "      \n"
            		+ "        <label>Seleccione Cantidad: </label>\n"
            		+ "        <input type=\"number\" name=\"id_8\">\n"
            		+ "      \n"
            		+ "    </div>\n"
            		+ "\n"
            		+ "    <div id=\"bot\">\n"
            		+ "      <button type=\"submit\">IR AL CARRITO</button>\n"
            		+ "    </div>\n"
            		+ "\n"
            		+ "</form>\n"
            		+ "\n"
            		+ "</body></html>");
            
        }else{
        	/**
        	 * Si el login no es correcto, la sesión se invalida y se redirige a la página de error de login
        	 */
        	sesion.invalidate();
          
        	response.sendRedirect("/proyecto_SERVLET/model/error.jsp");
        	
        }
	}
	
	
	
	
	/**
	 * Este método instancia la lista de usuarios, carga los usuarios y comprueba que el usuario y contraseña introducido existe 
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
