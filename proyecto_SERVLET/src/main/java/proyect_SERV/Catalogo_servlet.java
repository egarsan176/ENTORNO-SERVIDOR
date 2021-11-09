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
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);	//si entra en el GET, llama al doPost
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
			 * llamo al método que almacena los nombres de los productos en la sesión
			 */
			almacenarNombreProductosSesion(sesion);	
			
			/**
			 * Recupero las cantidades de cada producto introducidas en el catálogo y las voy almacenando en la sesión si tienen algún valor introducido mayor que 0
			 */
			try {
				String cantidad1 = request.getParameter("id_1");
				if(!"".equals(cantidad1) && Integer.parseInt(cantidad1) > 0 && cantidad1!= null) {	
					sesion.setAttribute("c1", Integer.parseInt(cantidad1));
				}
				
				
				String cantidad2 = request.getParameter("id_2");
				if(!"".equals(cantidad2) && Integer.parseInt(cantidad2) > 0 && cantidad2!= null) {
					sesion.setAttribute("c2", Integer.parseInt(cantidad2));	
				}
				
				String cantidad3 = request.getParameter("id_3");
				if(!"".equals(cantidad3) && Integer.parseInt(cantidad3) > 0 && cantidad3!= null) {				
					sesion.setAttribute("c3", Integer.parseInt(cantidad3));
				}
				
				String cantidad4 = request.getParameter("id_4");
				if(!"".equals(cantidad4) && Integer.parseInt(cantidad4) > 0 && cantidad4!= null) {
					sesion.setAttribute("c4", Integer.parseInt(cantidad4));
				}
				
				String cantidad5 = request.getParameter("id_5");
				if(!"".equals(cantidad5) && Integer.parseInt(cantidad5) > 0 && cantidad5!= null) {
					sesion.setAttribute("c5", Integer.parseInt(cantidad5));
				}
				
				
				String cantidad6 = request.getParameter("id_6");
				if(!"".equals(cantidad6) && Integer.parseInt(cantidad6) > 0 && cantidad6!= null) {				
					sesion.setAttribute("c6", Integer.parseInt(cantidad6));
				}
				
				String cantidad7 = request.getParameter("id_7");
				if(!"".equals(cantidad7) && Integer.parseInt(cantidad7) > 0 && cantidad7!= null) {
					sesion.setAttribute("c7", Integer.parseInt(cantidad7));
				}
				
				String cantidad8 = request.getParameter("id_8");
				if(!"".equals(cantidad8) && Integer.parseInt(cantidad8) > 0 && cantidad8!= null) {
					sesion.setAttribute("c8", Integer.parseInt(cantidad8));
				}
				
				/***
				 * Guardo en un array los precios de todos los productos y los voy almacenando en la sesión
				 */
				double [] listadoPrecios = {49.99, 69.99, 19.99, 29.99, 19.99, 10.99, 1.99, 2.5};
				sesion.setAttribute("pp1", listadoPrecios[0]);
				sesion.setAttribute("pp2", listadoPrecios[1]);
				sesion.setAttribute("pp3", listadoPrecios[2]);
				sesion.setAttribute("pp4", listadoPrecios[3]);
				sesion.setAttribute("pp5", listadoPrecios[4]);
				sesion.setAttribute("pp6", listadoPrecios[5]);
				sesion.setAttribute("pp7", listadoPrecios[6]);
				sesion.setAttribute("pp8", listadoPrecios[7]);
				
				
				/**
				 * Guardo en un array los precios totales (los precios totales de las cantidades seleccionadas) y los almaceno en la sesión
				 * Para calcular los precios hago uso del método calcularPrecio(String cantidad, double precio)
				 */

				double [] totalPrecios = {calcularPrecio(cantidad1, listadoPrecios[0]), 
											calcularPrecio(cantidad2, listadoPrecios[1]),
											calcularPrecio(cantidad3, listadoPrecios[2]),
											calcularPrecio(cantidad4, listadoPrecios[3]),
											calcularPrecio(cantidad5, listadoPrecios[4]),
											calcularPrecio(cantidad6, listadoPrecios[5]),
											calcularPrecio(cantidad7, listadoPrecios[6]),
											calcularPrecio(cantidad8, listadoPrecios[7])};
				sesion.setAttribute("total1", totalPrecios[0]);
				sesion.setAttribute("total2", totalPrecios[1]);
				sesion.setAttribute("total3", totalPrecios[2]);
				sesion.setAttribute("total4", totalPrecios[3]);
				sesion.setAttribute("total5", totalPrecios[4]);
				sesion.setAttribute("total6", totalPrecios[5]);
				sesion.setAttribute("total7", totalPrecios[6]);
				sesion.setAttribute("total8", totalPrecios[7]);

				/**
				 * Recorro el array de totalPrecios para averiguar el coste total del pedido y lo almaceno en la sesión
				 */
				double costeTotal = 0;
				
				for(int i=0; i< totalPrecios.length;i++) {
				costeTotal += totalPrecios[i];
				}
				sesion.setAttribute("total", Math.round((costeTotal)*100d)/100d);	//guardo en la sesion el coste total de todos los productos seleccionados
				
				/**
				 * Creo las variables que contendrán el código HTML si éstas han sido seleccionadas en el catálogo
				 */
				String product1 = "";
				String product2 = "";
				String product3 = "";
				String product4 = "";
				String product5 = "";
				String product6 = "";
				String product7 = "";
				String product8 = "";
				
				
				/**
				 * Voy comprobando cuáles son los productos de los que se ha seleccionado una cantidad y genero el fragmento HTML que le corresponde a cada uno
				 */
				if(sesion.getAttribute("c1")!= null) {
					
					product1 = generarHTML(sesion.getAttribute("p1"), sesion.getAttribute("pp1"), sesion.getAttribute("c1"), sesion.getAttribute("total1"));
					
				}
				if(sesion.getAttribute("c2")!= null) {
					
					product2 = generarHTML(sesion.getAttribute("p2"), sesion.getAttribute("pp2"), sesion.getAttribute("c2"), sesion.getAttribute("total2"));
					
				}
				if(sesion.getAttribute("c3")!= null) {
					
					product3 = generarHTML(sesion.getAttribute("p3"), sesion.getAttribute("pp3"), sesion.getAttribute("c3"), sesion.getAttribute("total3"));
					
				}
				if(sesion.getAttribute("c4")!= null) {
					
					product4 = generarHTML(sesion.getAttribute("p4"), sesion.getAttribute("pp4"), sesion.getAttribute("c4"), sesion.getAttribute("total4"));
					
				}
				if(sesion.getAttribute("c5")!= null) {
					
					product5 = generarHTML(sesion.getAttribute("p5"), sesion.getAttribute("pp5"), sesion.getAttribute("c5"), sesion.getAttribute("total5"));
					
				}
				if(sesion.getAttribute("c6")!= null) {
					
					product6 = generarHTML(sesion.getAttribute("p6"), sesion.getAttribute("pp6"), sesion.getAttribute("c6"), sesion.getAttribute("total6"));
					
				}
				if(sesion.getAttribute("c7")!= null) {
					
					product7 = generarHTML(sesion.getAttribute("p7"), sesion.getAttribute("pp7"), sesion.getAttribute("c7"), sesion.getAttribute("total7"));
					
				}
				if(sesion.getAttribute("c8")!= null) {
					
					product8 = generarHTML(sesion.getAttribute("p8"), sesion.getAttribute("pp8"), sesion.getAttribute("c8"), sesion.getAttribute("total8"));
			
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
						+ "    <title>Resumen</title>\n"
						+ "\n"
						+ "    <link rel=\"stylesheet\" href=\"CSS/hojaDeEstilo3.css\">\n"
						+ "</head>\n"
						+ "<body>\n"
						+ "\n"
						+ "  <div id=\"header\"> \n"
						+ "    <h2>Resumen del Pedido:</h2>\n"
						+ "  </div>\n"
						+ "\n"
						+ "    <form method=\"POST\" action=\"/proyecto_SERVLET/Factura\">\n"
						+ "\n"
						+ "      <table>\n"
						+ "        \n"
						+ "      <thead>\n"
						+ "        <th>Nombre del Producto</th>\n"
						+ "        <th>Precio Unidad</th>\n"
						+ "        <th>Cantidad</th>\n"
						+ "        <th>Precio Total</th>\n"
						+ "      </thead>\n"
						+ "\n"
						+ ""+product1+"\n"
						+ ""+product2+"\n"
						+ ""+product3+"\n"
						+ ""+product4+"\n"
						+ ""+product5+"\n"
						+ ""+product6+"\n"
						+ ""+product7+"\n"
						+ ""+product8+"\n"
						+ "\n"
						+ "      </table>\n"
						+ "\n"
						+ "      <p>Seleccione opciones de envío:</p>\n"
						+ "\n"
						+ "        <input type=\"checkbox\" name=\"estandar\">Envío estándar (gratuito)\n"
						+ "        <br>\n"
						+ "        <input type=\"checkbox\" name=\"urgente\">Envío urgente (+4.99€)\n"
						+ "        <br>\n"
						+ "        <div id=\"button\">\n"
						+ "          <button>GENERAR FACTURA</button>\n"
						+ "        </div>\n"
						+ "        \n"
						+ "    </form>\n"
						+ "</body>\n"
						+ "</html></html>");
			} catch (Exception e) {
				response.sendRedirect("/proyecto_SERVLET/model/expirated_session.jsp");
			}
			
		
			
		}else {
			/**
			 * Si la sesión no es válida redirige a página de error
			 */
			response.sendRedirect("/proyecto_SERVLET/model/expirated_session.jsp");
		}
			
	}
	
	/**
	 * Este método guarda en la sesión los nombres de los productos del catálogo
	 * @param sesion
	 */
	private void almacenarNombreProductosSesion(HttpSession sesion) {
		
		String [] listadoProductos = {"Kit Básico de Herramientas", "Taladro percutor", "Llave", "Juego de Destornilladores",
										"Juego de LLaves", "Juego de Destornilladores", "Tornillo con tuerca", "Tornillo"};
		sesion.setAttribute("p1", listadoProductos[0]);
		sesion.setAttribute("p2", listadoProductos[1]);
		sesion.setAttribute("p3", listadoProductos[2]);
		sesion.setAttribute("p4", listadoProductos[3]);
		sesion.setAttribute("p5", listadoProductos[4]);
		sesion.setAttribute("p6", listadoProductos[5]);
		sesion.setAttribute("p7", listadoProductos[6]);
		sesion.setAttribute("p8", listadoProductos[7]);
		
	}

	/**
	 * Este método alcula el precio a pagar de x producto según las cantidades pedidas
	 * @param cantidad
	 * @param precio
	 * @return precio total de una cantidad de x producto
	 */
	private double calcularPrecio(String cantidad, double precio) {

		
		if(cantidad.equals("")) {
			cantidad = "0";
		}
		
		int cantidad2 = Integer.parseInt(cantidad);	//tengo que parsearlo porque la cantidad la recupera como String
		
		return cantidad2*precio;
		
	}
	
	/**
	 * Este método genera un fragmento de código HTML con los datos de los productos
	 * @param nombreProducto
	 * @param precioUnidad
	 * @param cantidadProducto
	 * @param precioTotal
	 * @return Un string con el código HTML
	 */
	private String generarHTML(Object nombreProducto, Object precioUnidad, Object cantidadProducto,  Object precioTotal) {
	
		return "<tr>\n"
		+ "<td>\n"
		+ ""+nombreProducto+"   \n"
		+ "</td>\n"
		+ "<td >\n"
		+ ""+precioUnidad+"\n"
		+ "</td>\n"
		+ " <td >\n"
		+ ""+cantidadProducto+"\n"
		+ "</td>\n"
		+ "<td >\n"
		+ ""+precioTotal+"\n"
		+ "</td>\n"
		+ "</tr>\n";
	}
	

}
