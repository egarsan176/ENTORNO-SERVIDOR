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
 * Servlet implementation class Factura
 */
@WebServlet("/Factura")
public class Factura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Declaro las constantes de los gastos de envío y del IVA
	 */
	private static final double _ENVIO = 4.99;	
	private static final double _IVA = 0.16;		
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Factura() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); 	//llama al doPost
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
		
		/**
		 * Si la sesión es válida, se ejecuta el código
		 */
		if(sesion != null) {
			
			/**
			 * Creo las variables que contendrán el código HTML si éstas han sido seleccionadas en el catálogo
			 */
			String detalles1 ="";
			String detalles2 ="";
			String detalles3 ="";
			String detalles4 ="";
			String detalles5 ="";
			String detalles6 ="";
			String detalles7 ="";
			String detalles8 ="";
			
			
			/**
			 * Voy comprobando cuáles son los productos de los que se ha seleccionado una cantidad y genero el fragmento HTML que le corresponde a cada uno
			 */
			if(sesion.getAttribute("c1")!= null) {
				
				detalles1 = generarCodigo(sesion.getAttribute("p1"), sesion.getAttribute("pp1"), sesion.getAttribute("c1"), sesion.getAttribute("total1"));
				
			}
			if(sesion.getAttribute("c2")!= null) {
				
				detalles2 = generarCodigo(sesion.getAttribute("p2"), sesion.getAttribute("pp2"), sesion.getAttribute("c2"), sesion.getAttribute("total2"));
				
			}
			if(sesion.getAttribute("c3")!= null) {
				
				detalles3 = generarCodigo(sesion.getAttribute("p3"), sesion.getAttribute("pp3"), sesion.getAttribute("c3"), sesion.getAttribute("total3"));
				
			}
			if(sesion.getAttribute("c4")!= null) {
				
				detalles4 = generarCodigo(sesion.getAttribute("p4"), sesion.getAttribute("pp4"), sesion.getAttribute("c4"), sesion.getAttribute("total4"));
				
			}
			if(sesion.getAttribute("c5")!= null) {
				
				detalles5 = generarCodigo(sesion.getAttribute("p5"), sesion.getAttribute("pp5"), sesion.getAttribute("c5"), sesion.getAttribute("total5"));
				
			}
			if(sesion.getAttribute("c6")!= null) {
				
				detalles6 = generarCodigo(sesion.getAttribute("p6"), sesion.getAttribute("pp6"), sesion.getAttribute("c6"), sesion.getAttribute("total6"));
				
			}
			if(sesion.getAttribute("c7")!= null) {
				
				detalles7 = generarCodigo(sesion.getAttribute("p7"), sesion.getAttribute("pp7"), sesion.getAttribute("c7"), sesion.getAttribute("total7"));
				
			}
			if(sesion.getAttribute("c8")!= null) {
				
				detalles8 = generarCodigo(sesion.getAttribute("p8"), sesion.getAttribute("pp8"), sesion.getAttribute("c8"), sesion.getAttribute("total8"));
		
			}
			
			/**
			 * Compruebo si el campo del check de los gastos de envío ha sido marcado pra añadir el gasto extra
			 */
			String checkEnvio = request.getParameter("urgente");	
			double envio = 0;
			if("on".equals(checkEnvio)) {
				envio = _ENVIO;
			}
			
			
			/**
			 * Genero la respuesta HTML que ofrece el servlet
			 */
			response.setContentType("text/html");

			PrintWriter out =response.getWriter();
			
			out.println("<DOCTYPE html><html>"
					+ "<!DOCTYPE html>\n"
					+ "<html lang=\"en\">\n"
					+ "<head>\n"
					+ "    <meta charset=\"UTF-8\">\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
					+ "    <meta name=\"viewport\" content=\"width=<device-width>, initial-scale=1.0\">\n"
					+ "    <title>Document</title>\n"
					+ "\n"
					+ "    <link rel=\"stylesheet\" href=\"CSS/hojaDeEstilo4.css\">\n"
					+ "</head>\n"
					+ "<body>\n"
					+ "\n"
					+ "  <div id=\"header\"> \n"
					+ "    <h2>Detalles Factura</h2>\n"
					+ "  </div>\n"
					+ "\n"
					+ "  <div class=\"detalle\">\n"
					+ "\n"
					+ "    <table>\n"
					+ "\n"
					+ "      <thead>\n"
					+ "        <th class=\"prod\">Nombre del Producto</th>\n"
					+ "        <th class=\"prod\">Precio Unidad</th>\n"
					+ "        <th class=\"prod\">Cantidad</th>\n"
					+ "        <th class=\"prod\">Precio Total</th>\n"
					+ "      </thead>\n"
					+ "\n"
					+ "              "+detalles1+"\n"
					+ "              "+detalles2+"\n"
					+ "              "+detalles3+"\n"
					+ "              "+detalles4+"\n"
					+ "              "+detalles5+"\n"
					+ "              "+detalles6+"\n"
					+ "              "+detalles7+"\n"
					+ "              "+detalles8+"\n"
					+ "          <tr class=\"bold\">\n"
					+ "            <td colspan=\"3\">\n"
					+ "                TOTAL:  \n"
					+ "            </td>\n"
					+ "            <td >\n"
					+ "              "+sesion.getAttribute("total")+"\n"
					+ "            </td>\n"
					+ "          </tr>\n"
					+ "\n"
					+ "          <tr class=\"bold\">\n"
					+ "            <td colspan=\"3\">\n"
					+ "                IVA (16%):  \n"
					+ "            </td>\n"
					+ "            <td >\n"
					+ "              "+calcularIVA((double) sesion.getAttribute("total"))+"\n"
					+ "            </td>\n"
					+ "          </tr>\n"
					+ "\n"
					+ "          <tr class=\"bold\">\n"
					+ "            <td colspan=\"3\">\n"
					+ "                GASTOS DE ENVÍO:  \n"
					+ "            </td>\n"
					+ "            <td>\n"
					+ "              "+envio+"\n"
					+ "            </td>\n"
					+ "          </tr>\n"
					+ "\n"
					+ "          <tr class=\"bold\">\n"
					+ "            <td colspan=\"3\">\n"
					+ "               IMPORTE TOTAL A PAGAR: \n"
					+ "            </td>\n"
					+ "            <td >\n"
					+ "              "+calcularTotalFactura(calcularIVA((double) sesion.getAttribute("total")), (double) sesion.getAttribute("total"), envio)+"\n"
					+ "            </td>\n"
					+ "          </tr>\n"
					+ "\n"
					+ "      </table>\n"
					+ "\n"
					+ "\n"
					+ "  </div>\n"
					+ "     \n"
					+ "<form method=\"POST\" action=\"/proyecto_SERVLET/CerrarSesion\">\n"

					+"<div id=\"button\">\n"
					+ "   <button >CERRAR SESIÓN</button>\n"
					+ "   </div>"
					+ "       \n"
					+ "    </form>\n"
					+ "</body>\n"
					+ "</html>");
			
			
		}else {
			/**
			 * Si la sesión no es válida redirige a página de error
			 */
			response.sendRedirect("/proyecto_SERVLET/model/expirated_session.jsp");
		}
	}
	
	/**
	 * Este método genera un fragmento de código HTML con los datos del producto escogido
	 * @param nombre 
	 * @param precioUnidad
	 * @param cantidad
	 * @param total
	 * @return un String con un fragmento de HTML
	 */
	private String generarCodigo(Object nombre, Object precioUnidad, Object cantidad, Object total) {
		
		return "        <tr>\n"
				+ "            <td class=\"prod\">\n"
				+ "                "+nombre+"   \n"
				+ "            </td>\n"
				+ "            <td class=\"prod\">\n"
				+ "              "+precioUnidad+"\n"
				+ "            </td>\n"
				+ "            <td class=\"prod\">\n"
				+ "              "+cantidad+"\n"
				+ "            </td>\n"
				+ "            <td class=\"prod\">\n"
				+ "              "+total+"\n"
				+ "            </td>\n"
				+ "        </tr>\n";
	}
	
	/**
	 * Este método calcula el IVA de la cantidad que se le pasa por parámetro
	 * @param cantidad
	 * @return double con el IVA correspondiente
	 */
	private double calcularIVA(double cantidad) {
		
		return Math.round((cantidad*_IVA)*100d) / 100d;
	}
	
	/**
	 * Este método calcula el total de la factura generada
	 * @param IVA
	 * @param total
	 * @param envio
	 * @return double con la cantidad total a pagar
	 */
	private double calcularTotalFactura(double iva, double total, double envio) {
		return Math.round((iva+total+envio)*100d) /100d;
	}

}
