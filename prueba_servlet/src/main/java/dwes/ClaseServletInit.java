package dwes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
 
@WebServlet(
		name="ejemplo1_3", 
		urlPatterns="/ejemploservletInit",
		initParams = {
				@WebInitParam(name="mensaje", value="Mensaje de prueba"),	//con estos parámetros se monta una colección a la que accedemos con ServletConfig
				@WebInitParam(name="contador", value="10")
		}
)
public class ClaseServletInit extends HttpServlet {
 
    // Mensaje que se va a mostrar en la pagina
    String mensaje = "";        
    // Numero de veces que se va a repetir el mensaje
    int contador = 1;       
 
    // Metodo de inicializacion
     
    public void init(ServletConfig conf) 
                             throws ServletException {
        super.init(conf);   // MUY IMPORTANTE
         
        mensaje = conf.getInitParameter("mensaje");
        if (mensaje == null)
            mensaje = "Hola";
         
        try
        {
            contador = Integer.parseInt(
               conf.getInitParameter("contador"));
        } catch (NumberFormatException e) {
            contador = 1;
        }
    }
 
    // Metodo para procesar una peticion GET
     
    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) 
                 throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println ("<!DOCTYPE HTML PUBLIC \""+
                     "-//W3C//DTD HTML 4.0 " + 
                     "Transitional//EN\">");
        out.println ("<HTML>");
        out.println ("<BODY>");
         
        for (int i = 0; i < contador; i++)       
        {
            out.println (mensaje);
            out.println ("<BR>");
        }
         
        out.println ("</BODY>");
        out.println ("</HTML>");
    }
}

