package dwes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Servlet ejemplo de cabeceras y codigos de estado de respuesta
// Realiza 4 tareas, en funcion de lo que se le pida:
// - Calcular numeros primos y mostrar el ultimo numero encontrado
// - Enviar una cabecera de redireccion
// - Enviar un sendError()
// - Enviar un setStatus

@WebServlet("/servletrespuesta")
public class ServletCabecerasRespuesta extends HttpServlet implements Runnable
{
	private static final long serialVersionUID = -1841719948896523872L;

	long primo = 1;					// Ultimo numero primo descubierto
	Thread t = new Thread(this);	// Hilo para calcular numeros primos
	
	// Metodo de inicializacion
	
	public void init()
	{
		t.start();
	}
	
	// Metodo para GET
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		String accion = request.getParameter("accion");
	
		if (accion.equals("primo"))
		{
			// Buscar el ultimo numero primo y enviarlo

			response.setContentType("text/html");
			response.setHeader("Refresh", "10");
			PrintWriter out = response.getWriter();
			out.println ("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
			out.println ("<HTML><BODY>");
			out.println ("Ultimo primo: " + primo);
			out.println ("</BODY></HTML>");			

		} else if (accion.equals("redirect")) {

			// Redirigir a otra pagina

			String url = request.getParameter("url");						
			if (url == null)
				url = "https://www.google.es";
			response.sendRedirect(url);
			
		} else if (accion.equals("error")) {	
				
			// Enviar una pagina de error con sendError()

			int codigo = HttpServletResponse.SC_NOT_FOUND;
			try
			{
				codigo = Integer.parseInt(request.getParameter("codigoMensaje"));
			} catch (Exception ex) {
				codigo = HttpServletResponse.SC_NOT_FOUND;
			}
			String mensaje = request.getParameter("mensaje");
			if (mensaje == null)
				mensaje = "<H2>Error generado por el usuario</H2>";;
			response.sendError(codigo, mensaje);

		} 
	}	

	// Metodo para POST
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}


	// ========== METODOS AUXILIARES PARA EL HILO ==========

	// Metodo auxiliar para obtener el siguiente numero primo

	public long siguientePrimo(long numero)
	{
		for (long i = numero + 1; i > 0; i++)
			if (esPrimo(i))
				return i;
		return -1;
	}

	// Metodo auxiliar para ver si un numero es primo
	
	public boolean esPrimo(long numero)
	{
		for (long i = 2; i < numero; i++)
		{
			if (numero % i == 0)
				return false;
		}
		return true;
	}

	// Main del hilo
	
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(1000);
				primo = siguientePrimo(primo);
			} catch (Exception ex) {}
		}
	}
	
}
