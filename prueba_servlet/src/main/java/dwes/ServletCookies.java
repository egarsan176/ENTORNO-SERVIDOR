package dwes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletcookies")
public class ServletCookies extends HttpServlet {
	// Metodo para GET

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	//modifico la cabecera de respuesta y digo que será un código html
		response.setHeader("Cache-Control", "no-cache");	//modificamos la cabecera para que no cachee la página sino que la recargue entera

		Cookie[] cookies = request.getCookies();	//las cookies viajan en el request
		Cookie contador = buscaCookie("contador", cookies);

		if (contador == null) {	//quiere decir que la cookie no existe
			// Creamos la cookie con el contador

			Cookie cookie = new Cookie("contador", "1");
			cookie.setMaxAge(180); //en segundos
			response.addCookie(cookie);

			// Mostramos el mensaje de primera visita

			PrintWriter out = response.getWriter();
			out.println("<HTML>");
			out.println("<BODY>");
			out.println("Primera visita");
			out.println("<BR>");
			out.println("</BODY>");
			out.println("</HTML>");

		} else {

			// Obtenemos el valor actual del contador

			int cont = Integer.parseInt(contador.getValue());
			cont++;

			// Modificamos el valor de la cookie
			// incrementando el contador

			Cookie cookie = new Cookie("contador", "" + cont);
			cookie.setMaxAge(180);
			response.addCookie(cookie);

			// Mostramos el numero de visitas

			PrintWriter out = response.getWriter();
			out.println("<HTML>");
			out.println("<BODY>");
			out.println("Visita numero " + cont);
			out.println("<BR>");
			out.println("</BODY>");
			out.println("</HTML>");
		}
	}

	// Busca la cookie 'nombre'
	// en el array de cookies indicado.
	// Devuelve null si no esta

	private Cookie buscaCookie(String nombre, Cookie[] cookies) {	//recibe el nombre de la cookie y un array (que contiene todas las cookies del navegador) y la buscamos
		if (cookies == null)	
			return null;

		for (int i = 0; i < cookies.length; i++)
			if (cookies[i].getName().equals(nombre))
				return cookies[i];

		return null;
	}

}
